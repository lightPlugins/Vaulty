package io.lightplugins.vaulty.api;

import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Economy implements VaultyEconomy {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public String format(double amount) {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> hasAccountAsync(UUID player) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> hasAccountAsync(OfflinePlayer player) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> hasAccountAsync(String player) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(UUID player, double amountToWithdraw) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(OfflinePlayer player, double amountToWithdraw) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(String player, double amountToWithdraw) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> depositPlayerAsync(UUID player, double amountToDeposit) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> depositPlayerAsync(OfflinePlayer player, double amountToDeposit) {
        return null;
    }

    @Override
    public CompletableFuture<VaultyResponse> depositPlayerAsync(String player, double amountToDeposit) {
        return null;
    }

    @Override
    public CompletableFuture<Double> getBalanceAsync(UUID uuid) {
        return null;
    }

    @Override
    public CompletableFuture<Double> getBalanceAsync(OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public CompletableFuture<Double> getBalanceAsync(String string) {
        return null;
    }

    @Override
    public CompletableFuture<HashMap<UUID, Double>> getAllBalancesAsyncByUUID() {
        return null;
    }
}
