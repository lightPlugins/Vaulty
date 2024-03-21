package io.lightplugins.vaulty.commands;

import io.lightplugins.vaulty.Vaulty;
import io.lightplugins.vaulty.api.chat.Chat;
import io.lightplugins.vaulty.api.economy.Economy;
import io.lightplugins.vaulty.api.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Collection;

public class VaultInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if(!sender.hasPermission("vaulty.info")) {
                sender.sendMessage(Vaulty.consolePrefix + "You dont have permission for this command!");
                return false;
            }
        }

        if(args.length != 0) {
            sender.sendMessage(Vaulty.consolePrefix + "Usage: /vaulty-info");
            return false;
        }
        StringBuilder registeredEcons = new StringBuilder();
        Collection<RegisteredServiceProvider<Economy>> econs = Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

        for (RegisteredServiceProvider<Economy> econ : econs) {
            Economy e = econ.getProvider();
            registeredEcons.append(e.getName()).append(", ");
        }

        StringBuilder registeredPerms = new StringBuilder();
        Collection<RegisteredServiceProvider<Permission>> perms = Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Permission.class);
        for (RegisteredServiceProvider<Permission> perm : perms) {
            Permission p = perm.getProvider();
            registeredPerms.append(p.getName()).append(", ");
        }

        StringBuilder registeredChats = new StringBuilder();
        Collection<RegisteredServiceProvider<Chat>> chats = Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Chat.class);
        for (RegisteredServiceProvider<Chat> chat : chats) {
            Chat c = chat.getProvider();
            registeredChats.append(c.getName()).append(", ");
        }

        RegisteredServiceProvider<Economy> rsp = Vaulty.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        Economy econ = null;
        if (rsp != null) {
            econ = rsp.getProvider();
        }
        Permission perm = null;
        RegisteredServiceProvider<Permission> rspp = Vaulty.getInstance().getServer().getServicesManager().getRegistration(Permission.class);
        if (rspp != null) {
            perm = rspp.getProvider();
        }

        Chat chat = null;
        RegisteredServiceProvider<Chat> rspc = Vaulty.getInstance().getServer().getServicesManager().getRegistration(Chat.class);
        if (rspc != null) {
            chat = rspc.getProvider();
        }

        sender.sendMessage(Vaulty.consolePrefix + "Version of Vaulty: " + Vaulty.getInstance().getDescription().getVersion());
        sender.sendMessage(Vaulty.consolePrefix + "Economy plugins found: " + registeredEcons);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Economy plugin: " + (econ != null ? econ.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "Permission plugins found: " + registeredPerms);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Permission plugin: " + (perm != null ? perm.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "Chat plugins found: " + registeredChats);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Chat plugin: " + (chat != null ? chat.getName() : "None"));


        return false;
    }
}
