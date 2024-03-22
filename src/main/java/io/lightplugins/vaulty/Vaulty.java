package io.lightplugins.vaulty;

import io.lightplugins.vaulty.commands.VaultConvertCommand;
import io.lightplugins.vaulty.commands.VaultInfoCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class Vaulty extends JavaPlugin {

    // The main class

    private static Vaulty instance;
    public static String consolePrefix = "[Vaulty] ";
    public Logger logger;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance = this;
        logger = Bukkit.getLogger();

        Objects.requireNonNull(getCommand("vaulty-convert")).setExecutor(new VaultConvertCommand());
        Objects.requireNonNull(getCommand("vaulty-convert")).setTabCompleter(new VaultConvertCommand());
        Objects.requireNonNull(getCommand("vaulty-info")).setExecutor(new VaultInfoCommand());

        logger.info(consolePrefix + "Successfully started Vaulty");
    }

    @Override
    public void onDisable() {

    }

    public static Vaulty getInstance() {
        return instance;
    }
}