package io.lightplugins.vaulty;

import io.lightplugins.vaulty.api.economy.Economy;
import io.lightplugins.vaulty.commands.VaultConvertCommand;
import io.lightplugins.vaulty.commands.VaultInfoCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class Vaulty extends JavaPlugin {

    // The main class

    private static Vaulty instance;
    private static ServicesManager servicesManager;
    public static String consolePrefix = "[Vaulty] ";
    public Logger logger;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance = this;
        logger = Bukkit.getLogger();
        servicesManager = getServer().getServicesManager();
        RegisteredServiceProvider<Economy> economy = servicesManager.getRegistration(Economy.class);

        Objects.requireNonNull(getCommand("vaulty-convert")).setExecutor(new VaultConvertCommand());
        Objects.requireNonNull(getCommand("vaulty-convert")).setTabCompleter(new VaultConvertCommand());
        Objects.requireNonNull(getCommand("vaulty-info")).setExecutor(new VaultInfoCommand());


        if(economy != null) {
            logger.info(consolePrefix +
                    "Found economy implementation: " + economy.getProvider().getName());
        } else {
            logger.warning(consolePrefix +
                    "Could not found any economy implementation. Did you installed an Economy plugin?");
        }
        logger.info(consolePrefix + "Successfully started Vaulty");
    }

    @Override
    public void onDisable() {

    }


    public static Vaulty getInstance() {
        return instance;
    }

    public static ServicesManager getServicesManager() {
        return getInstance().servicesManager;
    }
}