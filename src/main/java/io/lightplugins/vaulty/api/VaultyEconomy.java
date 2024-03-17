package io.lightplugins.vaulty.api;

import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VaultyEconomy {


    public boolean isEnabled();

    public String getName();

    public boolean hasBankSupport();

    public String format(double amount);

    public String currencyNameSingular();

    public String currencyNamePlural();




    public CompletableFuture<Boolean> hasAccountAsync(UUID player);

    public CompletableFuture<Boolean> hasAccountAsync(OfflinePlayer player);

    @Deprecated
    public CompletableFuture<Boolean> hasAccountAsync(String player);





    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(UUID player, double amountToWithdraw);


    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(OfflinePlayer player, double amountToWithdraw);

    @Deprecated
    public CompletableFuture<VaultyResponse> withdrawPlayerAsync(String player, double amountToWithdraw);





    public CompletableFuture<VaultyResponse> depositPlayerAsync(UUID player, double amountToDeposit);


    public CompletableFuture<VaultyResponse> depositPlayerAsync(OfflinePlayer player, double amountToDeposit);

    @Deprecated
    public CompletableFuture<VaultyResponse> depositPlayerAsync(String player, double amountToDeposit);





    public CompletableFuture<Double> getBalanceAsync(UUID uuid);


    public CompletableFuture<Double> getBalanceAsync(OfflinePlayer offlinePlayer);

    @Deprecated
    public CompletableFuture<Double> getBalanceAsync(String string);




    public CompletableFuture<HashMap<UUID, Double>> getAllBalancesAsyncByUUID();



}
