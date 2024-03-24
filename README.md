<p align="center">
  <img src="https://i.ibb.co/Z85T9Jr/the-modern-vault-2.png"/>
</p>

<h1 align="center">Vaulty - The active Vault alternative</h1>
<div align="center">Modern up to date abstract library with UUID´s & BigDeciamls and CompletableFutures</div>

<div align="center">

![Discord](https://img.shields.io/discord/772495950127038465?style=flat&logo=discord&label=Discord)
![Spiget Downloads](https://img.shields.io/spiget/downloads/83862)
[![Jitpack](https://img.shields.io/jitpack/version/com.github.lightplugins/Vaulty)](https://jitpack.io/#lightPlugins/Vaulty)

</div>

> [!IMPORTANT]
> Vaulty incorporates all standard methods from the original Vault, ensuring that 
> plugin developers who have been utilizing Vault thus far do not need to update 
> their plugins. Updates are only necessary if they wish to utilize the new UUID, 
> BigDecimals, and Async functions, provided by Vaulty.
> **Just plug and play**.

# What is Vaulty?

**Vaulty** is an enhanced version of the super old **VaultAPI** & **Vault**, 
encompassing all the methods that Vault provides by
default. This means that `no changes need` to be made to existing plugins.
Additionally, it introduces new features such as **using BigDecimals
instead of doubles**, employing **UUIDs instead of Strings or OfflinePlayers**, and providing
**asynchronous methods for all new functions**. There are also new methods, for example, to
retrieve all Economy Account holders from the database as a HashMap.

> [!IMPORTANT]
> Vaulty is 100% compatible with ALL plugins, including all economy systems available.
> It automatically downloads the legacy Vault classes and makes them available for
> other plugins. This has been extensively tested and works flawlessly without any issues.
> **Just plug and play**.

# Why you should replace Vault with Vaulty today?

The original `Vault` plugin is out of date and no longer keeping pace with current standards. 
For three years, the community has been desperately urging the developer to bring it 
up to date, but to no avail. Here comes Vaulty into play. It is based on Vault, 
aiming to make the transition as smooth as possible for other developers and their existing 
plugins. Nothing has changed for such users. Ultimately, only new features have been added 
to cover today's standards and fulfill the community's long-awaited desires.

# Vaulty and MultiCurrency

Vaulty also comes with a MultiCurrency interface, which allows other plugins to inquire about 
specific currencies and modify their values. However, a condition for this is an economy plugin 
that supports both Vaulty and multi-currency functionality.

```java
// a complete List of the available currencies provided by an economy plugin.
    List<String> virtualCurrenciesGetList();
// get the displayname of the target currencie
    String virtualCurrenciesGetName(String currencyID);
// check, if the target player has an account for this currency
    boolean virtualCurrenciesHas(UUID playerName, String currencyID);
// Same as above, but async
    CompletableFuture<Boolean> virtualCurrenciesHasAsync(UUID playerName, String currencyID);
// deposit values to the target currency account
    VaultyResponse virtualCurrenciesDeposit(UUID playerName, String currencyID, BigDecimal amount);
// Same as above, but async
    CompletableFuture<VaultyResponse> virtualCurrenciesDepositAsync(UUID playerName, String currencyID, BigDecimal amount);
// withdraw values from the target currency account
    VaultyResponse virtualCurrenciesWithdraw(UUID playerName, String currencyID, BigDecimal amount);
// Same as above, but async
    CompletableFuture<VaultyResponse> virtualCurrenciesWithdrawAsync(UUID playerName, String currencyID, BigDecimal amount);
```

# Upgrade to Vaulty

As mentioned above, **nothing changes for you**. Just continue using your `VaultAPI`. **But** if, however, you wish to utilize the new methods, 
you must access `Vaulty's economy implementer`. Here's a brief guide on how to do so.
It's exactly the same as the old Vault API does.

> [!CAUTION]
> To be able to utilize Vaulty's new methods, an **economy plugin supporting 
> Vaulty must be installed**. Are you an economy plugin developer? If so, 
> take a look at this guide <comming soon> on how to integrate Vaulty into your economy plugin. Its super easy, i swear !

Let's Begin. First, import **Vaulty** to your project using JitPack

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.lightPlugins</groupId>
    <artifactId>Vaulty</artifactId>
    <version>1.0.0</version>
</dependency>
```

Now, you just need to retrieve the Vaulty `VaultyEconomy.class` from Bukkit's ServiceManager. 
Make sure you ask for the plugin **Vault** and not *Vaulty* itself and use the `VaultyEconomy.class` from Vaulty.

```java
public static VaultyEconomy vaultyEcon = null;  // The new Vaulty Economy implementer
public static Economy econ = null; // Your old VaultAPI implementer. You can still use them with Vaulty

public void onEnable() {
    hookIntoVault();
}

private hookIntoVault() {
    // Use 'Vault' because the plugin's name itself is still Vault, preventing other plugins from breaking.
    if (getServer().getPluginManager().getPlugin("Vault") != null) {
        
        // This is the new Vaultys Economy Implementer. You can add this to your vaultHook method.
        RegisteredServiceProvider<VaultyEconomy> rspVaulty = 
            getServer().getServicesManager().getRegistration(VaultyEconomy.class);
        
        if(rspVaulty != null) {
        vaultyEcon = rspVaulty.getProvider();
        return;
        } else {
            // There is no economy plugin currently installed that supports Vaulty. 
            // You should ask the developer if they can add Vaulty methods to their plugin.
        }

        // This is the old method you're accustomed to for accessing the Vault API, as you've been doing in the past years.
        RegisteredServiceProvider<Economy> rsp = 
            getServer().getServicesManager().getRegistration(Economy.class);
        
        if(rsp != null) {
        econ = rsp.getProvider();
        return;
        }
    }
}
```

That's it. As you can see, it's just like before. Nothing has changed there. Now you can access 
all the new methods, provided by Vaulty. Here are a few examples how to use the new methods with `vaultyEcon`.

### **Examples for `synchronous`**


```java
public void depositPlayerSomeMoney(UUID uuid, double amount) {
    
    BigDecimal depositAmount = new BigDecimal(amount);
    //  Here you can do some roundings or else.

    VaultyResponse response = vaultyEcon.depositPlayer(uuid, depositAmount);
    if(response.transactionSuccess()) {
        // Successfully deposited some money to the OfflinePlayer
    }
}

public void withdrawPlayerSomeMoney(UUID uuid, BigDecimal amount) {

    VaultyResponse response = vaultyEcon.withdrawPlayer(uuid, amount);
    if(response.transactionSuccess()) {
        // Successfully deposited some money to the OfflinePlayer
    }
}

public double getBalance(UUID uuid) {
    
    BigDecimal bd = vaultyEcon.getBalance(uuid);
    return bd != null ? bd.doubleValue : 0.00;
}

// and so on ...
```
### **Examples for `asynchronous`**

```java
public void depositPlayerSomeMoney(UUID uuid, BigDecimal amount) {

    CompletableFuture<VaultyResponse> response = vaultyEcon.depositPlayerAsync(uuid, amount);

    response.thenApplyAsync(result -> {
        // you can modify the result if you wish here
        BigDecimal resultAmount = result.amount();
        String errorMessage = result.errorMessage();
        return result;
    }).thenAcceptAsync(result -> {
        // And then make final actions such as sending a message to the player.
        if(result.transactionSuccess()) {
            // Successfully deposited some money to the OfflinePlayer
        }
    }) // add .join() to make the process synchronous later
}

public CompletableFuture<Double> getBalanceFromPlayerForExample(UUID uuid) {

    CompletableFuture<BigDecimal> response = vaultyEcon.getBalanceAsync(uuid);

    // Mapping the BigDecimal result to Double.
    // You can use lambda expression here -> (BigDecimal::doubleValue)
    return response.thenApplyAsync(result -> result.doubleValue())
            .exceptionally(ex -> {
                return 0.0; // Default value in case of failure.
            });
}
```





