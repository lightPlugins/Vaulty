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
                sender.sendMessage(Vaulty.consolePrefix +
                        "You dont have permission for this command!");
                return false;
            }
        }

        if(args.length != 3) {
            sender.sendMessage(Vaulty.consolePrefix +
                    "Wrong command usage. Try /vaulty-convert [vaulty,vault] <from> <to>");
            return false;
        }

        if(args[0].equalsIgnoreCase("vaulty")) {

            Collection<RegisteredServiceProvider<Economy>> econsVaulty =
                    Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

            if(econsVaulty.size() < 2) {
                sender.sendMessage(Vaulty.consolePrefix +
                        "You must have at least 2 economies loaded to convert.");
                return false;
            }

            Economy econVaulty1 = null;
            Economy econVaulty2 = null;
            StringBuilder economiesVaulty = new StringBuilder();

            for (RegisteredServiceProvider<Economy> econ : econsVaulty) {
                String econName = econ.getProvider().getName().replace(" ", "");
                if(econName.equals(args[0])) {
                    econVaulty1 = econ.getProvider();
                } else if (econName.equalsIgnoreCase(args[1])) {
                    econVaulty2 = econ.getProvider();
                }
                economiesVaulty.append(econName).append(", ");
            }

            if (econVaulty1 == null) {
                sender.sendMessage(Vaulty.consolePrefix +
                        "Could not find " + args[0] + " loaded on the server, check your spelling.");
                sender.sendMessage(Vaulty.consolePrefix +
                        "Valid Vaulty economies are: " + economiesVaulty);
                return false;
            } else if (econVaulty2 == null) {
                sender.sendMessage(Vaulty.consolePrefix +
                        "Could not find " + args[1] + " loaded on the server, check your spelling.");
                sender.sendMessage(Vaulty.consolePrefix +
                        "Valid Vaulty economies are: " + economiesVaulty);
                return false;
            }

            sender.sendMessage(Vaulty.consolePrefix +
                    "This may take some time to convert, expect server lag.");

            for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                if (econVaulty1.hasAccount(op)) {
                    if (econVaulty2.hasAccount(op)) {
                        continue;
                    }
                    econVaulty2.createPlayerAccount(op);
                    double diff = econVaulty1.getBalance(op) - econVaulty2.getBalance(op);
                    if (diff > 0) {
                        econVaulty2.depositPlayer(op, diff);
                    } else if (diff < 0) {
                        econVaulty2.withdrawPlayer(op, -diff);
                    }
                }
            }

            sender.sendMessage(Vaulty.consolePrefix +
                    "Converson complete, please verify the data before using it.");

            return false;

        } else if(args[0].equalsIgnoreCase("vault")) {

            Collection<RegisteredServiceProvider<net.milkbowl.vault.economy.Economy>> econsVault =
                    Vaulty.getInstance().getServer().getServicesManager().getRegistrations(net.milkbowl.vault.economy.Economy.class);

            net.milkbowl.vault.economy.Economy econVault1 = null;
            net.milkbowl.vault.economy.Economy econVault2 = null;
            StringBuilder economiesVault = new StringBuilder();

            for (RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> econ : econsVault) {
                String econName = econ.getProvider().getName().replace(" ", "");
                if(econName.equals(args[0])) {
                    econVault1 = econ.getProvider();
                } else if (econName.equalsIgnoreCase(args[1])) {
                    econVault2 = econ.getProvider();
                }
                economiesVault.append(econName).append(", ");
            }

            if (econVault1 == null) {
                sender.sendMessage(Vaulty.consolePrefix +
                        "Could not find " + args[0] + " loaded on the server, check your spelling.");
                sender.sendMessage(Vaulty.consolePrefix +
                        "Valid Vault economies are: " + economiesVault);
                return false;
            } else if (econVault2 == null) {
                sender.sendMessage(Vaulty.consolePrefix +
                        "Could not find " + args[1] + " loaded on the server, check your spelling.");
                sender.sendMessage(Vaulty.consolePrefix +
                        "Valid Vault economies are: " + economiesVault);
                return false;
            }

            sender.sendMessage(Vaulty.consolePrefix +
                    "This may take some time to convert, expect server lag.");

            for (OfflinePlayer op : Bukkit.getServer().getOfflinePlayers()) {
                if (econVault1.hasAccount(op)) {
                    if (econVault2.hasAccount(op)) {
                        continue;
                    }
                    econVault2.createPlayerAccount(op);
                    double diff = econVault1.getBalance(op) - econVault2.getBalance(op);
                    if (diff > 0) {
                        econVault2.depositPlayer(op, diff);
                    } else if (diff < 0) {
                        econVault2.withdrawPlayer(op, -diff);
                    }
                }
            }

            sender.sendMessage(Vaulty.consolePrefix +
                    "Converson complete, please verify the data before using it.");

            return false;
        }
        sender.sendMessage(Vaulty.consolePrefix +
                "Wrong command usage. Try /vaulty-convert [vaulty,vault] <from> <to>");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Collection<RegisteredServiceProvider<Economy>> econsVaulty =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

        Collection<RegisteredServiceProvider<net.milkbowl.vault.economy.Economy>> econsVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(net.milkbowl.vault.economy.Economy.class);

        List<String> tabs = new ArrayList<>();

        if(args.length == 2 || args.length == 3) {

            StringBuilder sb = new StringBuilder();

            if(args[0].equalsIgnoreCase("vaulty")) {
                for(RegisteredServiceProvider<Economy> econ : econsVaulty) {
                    if(econ != null) {
                        sb.append(econ.getProvider().getName().replace(" ", "")).append(", ");
                    }
                }
                tabs.addAll(List.of(sb.toString().split(", ")));

            } else if(args[0].equalsIgnoreCase("vault")) {
                for(RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> econ : econsVault) {
                    if(econ != null) {
                        sb.append(econ.getProvider().getName().replace(" ", "")).append(", ");
                    }
                }
                tabs.addAll(List.of(sb.toString().split(", ")));

            }
        }

        return tabs;
    }
}
