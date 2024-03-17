package io.lightplugins.vaulty;

import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Vaulty extends JavaPlugin {

    // The main class

    public Vaulty plugin;
    private ServicesManager servicesManager;

    @Override
    public void onLoad() {
        System.out.println("Hello world!");
    }

    @Override
    public void onEnable() {
        plugin = this;
        servicesManager = getServer().getServicesManager();
    }

    @Override
    public void onDisable() {
        System.out.println("Hello world!");
    }




    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}