package io.lightplugins.vaulty.api.economy;

import org.bukkit.OfflinePlayer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface VaultyEconomy {

    /**
     * Checks if economy method is enabled.
     * @return Success or Failure
     */
     boolean isEnabled();

    /**
     * A description of the entire Java function.
     *
     * @return         	description of return value
     */
    boolean hasAsyncSupport();

    /**
     * Gets name of economy method
     * @return Name of Economy Method
     */
     String getName();

    /**
     * Returns true if the given implementation supports banks.
     * @return true if the implementation supports banks
     */
     boolean hasBankSupport();

    /**
     * Returns true if the given implementation supports banks.
     * @return true if the implementation supports banks
     */
    boolean hasCustomCurrencySupport();

    /**
     * Some economy plugins round off after a certain number of digits.
     * This function returns the number of digits the plugin keeps
     * or -1 if no rounding occurs.
     * @return number of digits after the decimal point kept
     */
     int fractionalDigits();

    /**
     * Format amount into a human-readable String This provides translation into
     * economy-specific formatting to improve consistency between plugins.
     *
     * @param amount to format
     * @return Human-readable string describing amount
     * @deprecated It is not recommended to use double as a value {@link #format(BigDecimal)}
     */
     @Deprecated
     String format(double amount);

    /**
     * Returns the name of the currency in plural form.
     * If the economy being used does not support currency names, then an empty string will be returned.
     *
     * @return name of the currency (plural)
     */
     String currencyNamePlural();


    /**
     * Returns the name of the currency in singular form.
     * If the economy being used does not support currency names, then an empty string will be returned.
     *
     * @return name of the currency (singular)
     */
     String currencyNameSingular();


    /**
     * A description of the entire Java function.
     *
     * @param  amount	description of parameter
     * @return         	description of return value
     */
     String format(BigDecimal amount);

    /**
     * Retrieves all player accounts as a HashMap.
     *
     * @return         	HashMap of accounts where the key is the
     *                  UUID and the value is their account balance
     */
     HashMap<UUID, BigDecimal> getAllAccounts();

    /**
     * Retrieves all player accounts as a HashMap.
     *
     * @return         	CompletableFuture HashMap of player accounts where the
     *                  key is the UUID and the value is their account balance
     */
     CompletableFuture<HashMap<UUID, BigDecimal>> getAllAccountsAsync();

    /**
     * Creates a player account with the specified UUID.
     *
     * @param  playerUuid  the UUID of the player
     * @return             true if the player account is successfully created, false otherwise
     */
     boolean createPlayerAccount(UUID playerUuid);
    /**
     * Creates a player account with the given player UUID and world name.
     *
     * @param  playerUuid  the UUID of the player
     * @param  worldName   the name of the world
     * @return             true if the player account was successfully created, false otherwise
     */
     boolean createPlayerAccount(UUID playerUuid, String worldName);

    /**
     * Check if the player with the given UUID has an account.
     *
     * @param  playerUuid  the UUID of the player to check
     * @return             true if the player has an account, false otherwise
     */
     boolean hasAccount(UUID playerUuid);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid	description of parameter
     * @return         	description of return value
     */
     CompletableFuture<Boolean> hasAccountAsync(UUID playerUuid);

    /**
     * Checks if the player with the given UUID has an account in the specified world.
     *
     * @param  playerUuid  the UUID of the player
     * @param  worldName   the name of the world
     * @return             true if the player has an account in the world, false otherwise
     */
     boolean hasAccount(UUID playerUuid, String worldName);

    /**
     * Checks if the player has an account asynchronously.
     *
     * @param  playerUuid  the UUID of the player
     * @param  worldName   the name of the world
     * @return             a CompletableFuture representing the result of the check
     */
     CompletableFuture<Boolean> hasAccountAsync(UUID playerUuid, String worldName);

    /**
     * Retrieves the balance for the player with the given UUID.
     *
     * @param  playerUuid  the UUID of the player
     * @return             the balance of the player
     */
     BigDecimal getBalance(UUID playerUuid);

    /**
     * Retrieves the balance asynchronously for a given player UUID.
     *
     * @param  playerUuid  the UUID of the player
     * @return             a CompletableFuture containing the player's balance as a BigDecimal
     */
     CompletableFuture<BigDecimal> getBalanceAsync(UUID playerUuid);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid  the UUID of the account holder
     * @param  worldName   the world name of the account holder
     * @return             the balance the account holder has
     */
     BigDecimal getBalance(UUID playerUuid, String worldName);

    /**
     * Checks if the player has the specified amount of money.
     *
     * @param  playerUuid  the UUID of the account holder
     * @param  worldName   the world name of the account holder
     * @return             the balance the account holder has
     */
     CompletableFuture<BigDecimal> getBalanceAsync(UUID playerUuid, String worldName);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid  description of parameter
     * @param  amount      description of parameter
     * @return             description of return value
     */
     BigDecimal has(UUID playerUuid, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid	description of parameter
     * @param  amount	description of parameter
     * @return         	description of return value
     */
     CompletableFuture<BigDecimal> hasAsync(UUID playerUuid, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid	description of parameter
     * @param  world	description of parameter
     * @param  amount	description of parameter
     * @return         	description of return value
     */
     BigDecimal has(UUID playerUuid, String world, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  playerUuid	description of parameter
     * @param  world	    description of parameter
     * @param  amount	    description of parameter
     * @return         	description of return value
     */
     CompletableFuture<BigDecimal> hasAsync(UUID playerUuid, String world, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  uuid     description of parameter
     * @param  amount   description of parameter
     * @return         description of return value
     */
     EconomyResponse withdrawPlayer(UUID uuid, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  uuid     description of parameter
     * @param  amount   description of parameter
     * @return          description of return value
     */
     CompletableFuture<EconomyResponse> withdrawPlayerAsync(UUID uuid, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  uuid       description of parameter
     * @param  worldName  description of parameter
     * @param  amount     description of parameter
     * @return            description of return value
     */
     EconomyResponse withdrawPlayer(UUID uuid, String worldName, BigDecimal amount);

    /**
     * Withdraws a specified amount from a player's account asynchronously.
     *
     * @param  uuid         the UUID of the player
     * @param  worldName    the name of the world
     * @param  amount       the amount to be withdrawn
     * @return              a CompletableFuture that completes with an EconomyResponse object
     */
     CompletableFuture<EconomyResponse> withdrawPlayerAsync(UUID uuid, String worldName, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  uuid    description of parameter
     * @param  amount  description of parameter
     * @return        description of return value
     */
     EconomyResponse depositPlayer(UUID uuid, BigDecimal amount);

    /**
     * Deposits a player's funds asynchronously.
     *
     * @param  uuid    the UUID of the player
     * @param  amount  the amount of funds to deposit
     * @return         a CompletableFuture that will complete with an EconomyResponse
     */
     CompletableFuture<EconomyResponse> depositPlayerAsync(UUID uuid, BigDecimal amount);

    /**
     * Deposit a specified amount of money to the player's account.
     *
     * @param  uuid       the unique identifier of the player
     * @param  worldName  the name of the world where the deposit is made
     * @param  amount     the amount of money to be deposited
     * @return            the response indicating the result of the deposit
     */
     EconomyResponse depositPlayer(UUID uuid, String worldName, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  uuid        description of parameter
     * @param  worldName   description of parameter
     * @param  amount      description of parameter
     * @return             description of return value
     */
     CompletableFuture<EconomyResponse> depositPlayerAsync(UUID uuid, String worldName, BigDecimal amount);

    /**
     *
     * Bank Stuff
     *
     */


    /**
     * Create a bank with the specified name and owner.
     *
     * @param  bankName  the UUID of the bank
     * @param  owner     the UUID of the owner
     * @return          an EconomyResponse object
     */
     EconomyResponse createBank(UUID bankName, UUID owner);

    /**
     * Delete a bank with the specified UUID.
     *
     * @param  bankName  the UUID of the bank to delete
     * @return           the response from the economy system after deleting the bank
     */
     CompletableFuture<EconomyResponse> createBankAsync(UUID bankName, UUID owner);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName	description of parameter
     * @return         	description of return value
     */
     EconomyResponse deleteBank(UUID bankName);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName	description of parameter
     * @return         	description of return value
     */
     CompletableFuture<EconomyResponse> deleteBankAsync(UUID bankName);

    /**
     * Check if the bank has the specified amount for the given bankName.
     *
     * @param  bankName   the UUID of the bank
     * @param  amount     the amount to check for
     * @return         	an EconomyResponse indicating if the bank has the specified amount
     */
     EconomyResponse bankHas(UUID bankName, BigDecimal amount);

    /**
     * Checks if the bank has a certain amount of money.
     *
     * @param  bankName  the name of the bank
     * @param  amount    the amount of money to check
     * @return           a CompletableFuture that will contain the EconomyResponse
     */
     CompletableFuture<EconomyResponse> bankHasAsync(UUID bankName, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName  description of parameter
     * @param  amount    description of parameter
     * @return          description of return value
     */
     EconomyResponse bankWithdraw(UUID bankName, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName   description of parameter
     * @param  amount     description of parameter
     * @return            description of return value
     */
     CompletableFuture<EconomyResponse> bankWithdrawAsync(UUID bankName, BigDecimal amount);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName	description of parameter
     * @param  amount	    description of parameter
     * @return         	description of return value
     */
     EconomyResponse bankDeposit(UUID bankName, BigDecimal amount);

    /**
     * Perform an asynchronous deposit to the specified bank.
     *
     * @param  bankName   the UUID of the bank
     * @param  amount     the amount to deposit
     * @return            a CompletableFuture representing the result of the deposit
     */
     CompletableFuture<EconomyResponse> bankDepositAsync(UUID bankName, BigDecimal amount);

    /**
     * Retrieves the bank balance for a given bank name.
     *
     * @param  bankName  the UUID of the bank
     * @return           the bank balance as a BigDecimal
     */
     BigDecimal bankBalance(UUID bankName);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName	description of parameter
     * @return         	description of return value
     */
     CompletableFuture<BigDecimal> bankBalanceAsync(UUID bankName);

    /**
     * Determines if the specified player is the owner of the specified bank.
     *
     * @param  bankName   the UUID of the bank
     * @param  playerName the UUID of the player
     * @return            true if the player is the owner of the bank, false otherwise
     */
     Boolean isBankOwner(UUID bankName, UUID playerName);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName     description of parameter
     * @param  playerName    description of parameter
     * @return              description of return value
     */
     CompletableFuture<Boolean> isBankOwnerAsync(UUID bankName, UUID playerName);

    /**
     * Checks if a player is a member of a bank.
     *
     * @param  bankName   the UUID of the bank
     * @param  playerName the UUID of the player
     * @return            true if the player is a member of the bank, false otherwise
     */
     Boolean isBankMember(UUID bankName, UUID playerName);

    /**
     * A description of the entire Java function.
     *
     * @param  bankName   description of parameter
     * @param  playerName description of parameter
     * @return           description of return value
     */
     CompletableFuture<Boolean> isBankMemberAsync(UUID bankName, UUID playerName);

    /**
     * A description of the entire Java function.
     *
     * @return         	description of return value
     */
     List<UUID> getBanksAsUUID();

    /**
     * Retrieves the list of UUIDs for all banks asynchronously.
     *
     * @return a CompletableFuture that will contain the list of UUIDs for all banks
     */
     CompletableFuture<List<UUID>> getBanksAsUUIDAsync();


    /**
     * Custom currencies
     */


    List<String> virtualCurrenciesGetList();

    String virtualCurrenciesGetName(String currencyID);

    boolean virtualCurrenciesHas(UUID playerName, String currencyID);

    CompletableFuture<Boolean> virtualCurrenciesHasAsync(UUID playerName, String currencyID);

    EconomyResponse virtualCurrenciesDeposit(UUID playerName, String currencyID, BigDecimal amount);

    CompletableFuture<EconomyResponse> virtualCurrenciesDepositAsync(UUID playerName, String currencyID, BigDecimal amount);

    EconomyResponse virtualCurrenciesWithdraw(UUID playerName, String currencyID, BigDecimal amount);

    CompletableFuture<EconomyResponse> virtualCurrenciesWithdrawAsync(UUID playerName, String currencyID, BigDecimal amount);

}
