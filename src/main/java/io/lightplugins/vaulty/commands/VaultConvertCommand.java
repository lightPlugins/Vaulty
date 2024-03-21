package io.lightplugins.vaulty.commands;

import io.lightplugins.vaulty.Vaulty;
import io.lightplugins.vaulty.api.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VaultConvertCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if(!sender.hasPermission("vaulty.convert")) {
                sender.sendMessage(Vaulty.consolePrefix + "You dont have permission for this command!");
                return false;
            }
        }

        Collection<RegisteredServiceProvider<Economy>> econs =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

        if(econs.size() < 2) {
            sender.sendMessage(Vaulty.consolePrefix + "You must have at least 2 economies loaded to convert.");
            return false;
        } else if (args.length != 2) {
            sender.sendMessage(Vaulty.consolePrefix + "You must specify only the economy to convert from and the economy to convert to. (names should not contain spaces)");
            return false;
        }

        Economy econ1 = null;
        Economy econ2 = null;
        StringBuilder economies = new StringBuilder();

        for (RegisteredServiceProvider<Economy> econ : econs) {
            String econName = econ.getProvider().getName().replace(" ", "");
            if(econName.equals(args[0])) {
                econ1 = econ.getProvider();
            } else if (econName.equalsIgnoreCase(args[1])) {
                econ2 = econ.getProvider();
            }
            economies.append(econName).append(", ");
        }

        if (econ1 == null) {
            sender.sendMessage(Vaulty.consolePrefix + "Could not find " + args[0] + " loaded on the server, check your spelling.");
            sender.sendMessage(Vaulty.consolePrefix + "Valid economies are: " + economies);
            return false;
        } else if (econ2 == null) {
            sender.sendMessage(Vaulty.consolePrefix + "Could not find " + args[1] + " loaded on the server, check your spelling.");
            sender.sendMessage(Vaulty.consolePrefix + "Valid economies are: " + economies);
            return false;
        }

        sender.sendMessage(Vaulty.consolePrefix + "This may take some time to convert, expect server lag.");

        for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
            if (econ1.hasAccount(op)) {
                if (econ2.hasAccount(op)) {
                    continue;
                }
                econ2.createPlayerAccount(op);
                double diff = econ1.getBalance(op) - econ2.getBalance(op);
                if (diff > 0) {
                    econ2.depositPlayer(op, diff);
                } else if (diff < 0) {
                    econ2.withdrawPlayer(op, -diff);
                }
            }
        }

        sender.sendMessage(Vaulty.consolePrefix + "Converson complete, please verify the data before using it.");

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        Collection<RegisteredServiceProvider<Economy>> econs =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

        if(args.length == 1) {
            StringBuilder sb = new StringBuilder();
            for(RegisteredServiceProvider<Economy> econ : econs) {
                sb.append(econ.getProvider().getName().replace(" ", "")).append(", ");
            }
            return List.of(sb.toString().split(", "));
        }
        return new ArrayList<>();
    }
}
