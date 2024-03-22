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
        Collection<RegisteredServiceProvider<Economy>> econs =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Economy.class);

        Collection<RegisteredServiceProvider<net.milkbowl.vault.economy.Economy>> econsVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(net.milkbowl.vault.economy.Economy.class);

        for (RegisteredServiceProvider<Economy> econ : econs) {
            Economy e = econ.getProvider();
            registeredEcons.append(e.getName()).append(", ");
        }

        for (RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> econ : econsVault) {
            net.milkbowl.vault.economy.Economy e = econ.getProvider();
            registeredEcons.append(e.getName()).append(", ");
        }

        StringBuilder registeredPerms = new StringBuilder();
        Collection<RegisteredServiceProvider<Permission>> perms =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Permission.class);

        Collection<RegisteredServiceProvider<net.milkbowl.vault.permission.Permission>> permsVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(net.milkbowl.vault.permission.Permission.class);

        for (RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> perm : permsVault) {
            net.milkbowl.vault.permission.Permission p = perm.getProvider();
            registeredPerms.append(p.getName()).append(", ");
        }

        for (RegisteredServiceProvider<Permission> perm : perms) {
            Permission p = perm.getProvider();
            registeredPerms.append(p.getName()).append(", ");
        }

        StringBuilder registeredChats = new StringBuilder();
        Collection<RegisteredServiceProvider<Chat>> chats =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(Chat.class);

        Collection<RegisteredServiceProvider<net.milkbowl.vault.chat.Chat>> chatsVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistrations(net.milkbowl.vault.chat.Chat.class);

        for (RegisteredServiceProvider<Chat> chat : chats) {
            Chat c = chat.getProvider();
            registeredChats.append(c.getName()).append(", ");
        }

        for (RegisteredServiceProvider<net.milkbowl.vault.chat.Chat> chat : chatsVault) {
            net.milkbowl.vault.chat.Chat c = chat.getProvider();
            registeredChats.append(c.getName()).append(", ");
        }

        RegisteredServiceProvider<Economy> rsp =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rspVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        Economy econ = null;
        net.milkbowl.vault.economy.Economy econVault = null;
        if (rsp != null) {
            econ = rsp.getProvider();
        }
        if (rspVault != null) {
            econVault = rspVault.getProvider();
        }
        Permission perm = null;
        net.milkbowl.vault.permission.Permission permVault = null;
        RegisteredServiceProvider<Permission> rspp =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(Permission.class);
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> rsppVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (rspp != null) {
            perm = rspp.getProvider();
        }
        if (rsppVault != null) {
            permVault = rsppVault.getProvider();
        }

        Chat chat = null;
        net.milkbowl.vault.chat.Chat chatVault = null;
        RegisteredServiceProvider<Chat> rspc =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(Chat.class);
        RegisteredServiceProvider<net.milkbowl.vault.chat.Chat> rspcVault =
                Vaulty.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (rspc != null) {
            chat = rspc.getProvider();
        }
        if (rspcVault != null) {
            chatVault = rspcVault.getProvider();
        }

        sender.sendMessage(Vaulty.consolePrefix + "--------------------------------------------------");
        sender.sendMessage(Vaulty.consolePrefix + "Version of Vaulty: " + Vaulty.getInstance().getDescription().getVersion());
        sender.sendMessage(Vaulty.consolePrefix + "--------------------------------------------------");
        sender.sendMessage(Vaulty.consolePrefix + "Economy plugins found: " + registeredEcons);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Economy plugin for Vault: " + (econVault != null ? econVault.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "Primary Economy plugin for Vaulty: " + (econ != null ? econ.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "--------------------------------------------------");
        sender.sendMessage(Vaulty.consolePrefix + "Permission plugins found: " + registeredPerms);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Permission plugin for Vault: " + (permVault != null ? permVault.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "Primary Permission plugin for Vault: " + (perm != null ? perm.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "--------------------------------------------------");
        sender.sendMessage(Vaulty.consolePrefix + "Chat plugins found: " + registeredChats);
        sender.sendMessage(Vaulty.consolePrefix + "Primary Chat plugin for Vaulty: " + (chatVault != null ? chatVault.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "Primary Chat plugin for Vault: " + (chat != null ? chat.getName() : "None"));
        sender.sendMessage(Vaulty.consolePrefix + "--------------------------------------------------");

        return false;
    }
}
