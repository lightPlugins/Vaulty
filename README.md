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
> Vaulty includes all standard methods from Vault itself, so all 
> plugin developers who have been using Vault thus far only need 
> to import the new Economy class from Vaulty.
> **Just plug and play**.


# What is Vaulty?

**Vaulty** is an enhanced version of the super old **VaultAPI** & **Vault**, encompassing all the methods that Vault provides by
default. This means that no changes need to be made to existing plugins (exceptions being
all Economy plugins). Additionally, it introduces new features such as **using BigDecimals
instead of doubles**, employing **UUIDs instead of Strings or OfflinePlayers**, and providing
**asynchronous methods for all new functions**. There are also new methods, for example, to
retrieve all Economy Account holders from the database as a HashMap.

# Why you should start using Vaulty today?

The original Vault plugin is out of date and no longer keeping pace with current standards. 
For three years, the community has been desperately urging the developer to bring it 
up to date, but to no avail. Here comes Vaulty into play. It is based on Vault, 
aiming to make the transition as smooth as possible for other developers and their existing 
plugins. Nothing has changed for such users. Ultimately, only new features have been added 
to cover today's standards and fulfill the community's long-awaited desires.

# Upgrade to Vaulty

As mentioned above, nothing changes for you. You simply need to register the new `Economy.class` 
from **Vaulty** instead of the one from Vault. Also, generate **the new methods** from the 
`Economy interface`. Here's a quick guide on how to upgrade to Vaulty.

> [!WARNING]
> This guide is not intended for economy plugins like lightEconomy or TheNewEconomy. 
> Please refer to the specific wiki for those.

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
Now you just need to retrieve the `Economy.class` from Bukkit's ServiceManager.

```java
public static Economy econ = null;

public void onEnable() {
    // Load the Economy.class not in the onLoad() method.
    hookIntoVaulty();
}

private hookIntoVaulty() {
    // Use 'Vault' because the plugin's name itself is still Vault, preventing other plugins from breaking.
    if (getServer().getPluginManager().getPlugin("Vault") != null) { 
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp != null) {
        econ = rsp.getProvider();
        return;
        } else {
          // Vaulty was found, but it does not appear to be an Economy plugin that uses the Economy.class.
        }
    }
}
```

That's it. As you can see, it's just like before. Nothing has changed there. Now you can access 
all the old methods, including the new ones, using econ again. Here are a few examples.

> [!NOTE]
> I've deprecated all the standard methods, so you can see what's old and what's new. So don't 
> be surprised if your IDE complains about it. Deprecated methods won't be removed from the API for now.
## Some Examples
**Old Method** - Deposit money through the old Vault methods with OfflinePlayer. `@Deprecated`


```java
public void depositPlayerSomeMoney(OfflinePlayer offlinePlayer, double amount) {

    EconomyResponse response = economy.depositPlayer(offlinePlayer, amount);
    if(response.transactionSuccess()) {
        // Successfully deposited some money to the OfflinePlayer
    }
}
```
**New Method** - Deposit money through the new Vaulty Methods with UUID´s and BigDecimals

```java
public void depositPlayerSomeMoney(UUID uuid, BigDecimal amount) {

    EconomyResponse response = economy.depositPlayer(uuid, amount);
    if(response.transactionSuccess()) {
        // Successfully deposited some money to the OfflinePlayer
    }
}
```
**New Method** - Deposit money through the new Vaulty Methods with UUID´s and BigDecimals in Async
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



