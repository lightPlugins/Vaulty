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

> [!IMPORTANT]
> Vaulty is 100% compatible with ALL plugins, including all economy systems available.
> It automatically downloads the legacy Vault classes and makes them available for
> other plugins. This has been extensively tested and works flawlessly without any issues.
> **Just plug and play**.


# What is Vaulty?

**Vaulty** is an enhanced version of the super old **VaultAPI** & **Vault**, 
encompassing all the methods that Vault provides by
default. This means that `no changes need` to be made to existing plugins.
Additionally, it introduces new features such as **using BigDecimals
instead of doubles**, employing **UUIDs instead of Strings or OfflinePlayers**, and providing
**asynchronous methods for all new functions**. There are also new methods, for example, to
retrieve all Economy Account holders from the database as a HashMap.

# Why you should replace Vault with Vaulty today?

The original `Vault` plugin is out of date and no longer keeping pace with current standards. 
For three years, the community has been desperately urging the developer to bring it 
up to date, but to no avail. Here comes Vaulty into play. It is based on Vault, 
aiming to make the transition as smooth as possible for other developers and their existing 
plugins. Nothing has changed for such users. Ultimately, only new features have been added 
to cover today's standards and fulfill the community's long-awaited desires.

# Upgrade to Vaulty

As mentioned above, **nothing changes for you**. If, however, you wish to utilize the new methods, 
you must access `Vaulty's economy implementer`. Here's a brief guide on how to do so.
It's exactly the same as the old Vault API does.

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

Now, you just need to retrieve the Vaulty `Economy.class` from Bukkit's ServiceManager. 
Make sure you ask for the plugin **Vault** and not *Vaulty* itself and use the `Economy.class` from Vaulty.

```java
public static Economy vaultyEcon = null;

public void onEnable() {
    // Load the Economy.class not in the onLoad() method.
    hookIntoVaulty();
}

private hookIntoVaulty() {
    // Use 'Vault' because the plugin's name itself is still Vault, preventing other plugins from breaking.
    if (getServer().getPluginManager().getPlugin("Vault") != null) {
        //                                                               This must be the Vaultys Economy.class
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp != null) {
        vaultyEcon = rsp.getProvider();
        return;
        }
    }
}
```

That's it. As you can see, it's just like before. Nothing has changed there. Now you can access 
all the new methods, provided by Vaulty. Here are a few examples how to use the new methods.

**Example #1** - Deposit money through the new Vaulty Methods with UUID´s and BigDecimals

```java
public void depositPlayerSomeMoney(UUID uuid, BigDecimal amount) {

    EconomyResponse response = economy.depositPlayer(uuid, amount);
    if(response.transactionSuccess()) {
        // Successfully deposited some money to the OfflinePlayer
    }
}
```
**Example #2** - Deposit money through the new Vaulty Methods with UUID´s and BigDecimals
```java
public void depositPlayerSomeMoney(UUID uuid, BigDecimal amount) {

    CompletableFuture<EconomyResponse> response = economy.depositPlayerAsync(uuid, amount);

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
    }).join(); // add .join() to make the process synchronous
}
```



