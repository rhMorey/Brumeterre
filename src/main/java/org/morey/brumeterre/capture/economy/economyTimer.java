package org.morey.brumeterre.capture.economy;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.morey.brumeterre.main;

import java.util.UUID;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.morey.brumeterre.main.plugin;

public class economyTimer implements Listener {

    public static int secondsPassed = 0;
    public static int maxTime = 30;
    public static double moneyFlat = 125;
    public static double rewardMultiplicator = 0.02;
    public static double lostOnCapture = 750;
    public static double winOnCapture = 300;

    public static void addHonorPoint(Player player, double amount)
    {
        plugin.getConfig().set("data.money." + player.getUniqueId(), plugin.getConfig().getDouble("data.money." + player.getUniqueId()) + (amount));
        int amountInteger = (int) amount;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6Marque d'honneur §7§l» §e" + plugin.getConfig().getInt("data.money." + player.getUniqueId()) + "§7(+" + amountInteger + ")"));
        plugin.saveConfig();
    }

    public static void addHonorPointUUID(UUID uuid, double amount)
    {
        plugin.getConfig().set("data.money." + uuid, plugin.getConfig().getDouble("data.money." + uuid) + (amount));
        int amountInteger = (int) amount;
        Player player = Bukkit.getPlayer(uuid);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6Marque d'honneur §7§l» §e" + plugin.getConfig().getInt("data.money." + uuid) + "§7(+" + amountInteger + ")"));
        plugin.saveConfig();
    }

    public static void removeHonorPoint(Player player, double amount)
    {
        plugin.getConfig().set("data.money." + player.getUniqueId(), plugin.getConfig().getDouble("data.money." + player.getUniqueId()) - (amount));
        plugin.saveConfig();
        if(plugin.getConfig().getDouble("data.money." + player.getUniqueId()) <= 0)
        {
            plugin.getConfig().set("data.money." + player.getUniqueId(), 0);
            plugin.saveConfig();
        }
        int amountInteger = (int) amount;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6Marque d'honneur §7§l» §e" + plugin.getConfig().getInt("data.money." + player.getUniqueId()) + "§c(-" + amountInteger + ")"));
    }

    public static void removeHonorPointUUID(UUID uuid, double amount)
    {
        plugin.getConfig().set("data.money." + uuid, plugin.getConfig().getDouble("data.money." + uuid) - (amount));
        plugin.saveConfig();
        if(plugin.getConfig().getDouble("data.money." + uuid) <= 0)
        {
            plugin.getConfig().set("data.money." + uuid, 0);
            plugin.saveConfig();
        }
        int amountInteger = (int) amount;
        Player player = Bukkit.getPlayer(uuid);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6Marque d'honneur §7§l» §e" + plugin.getConfig().getInt("data.money." + uuid) + "§c(-" + amountInteger + ")"));
    }

    public static double getHonorUUID(UUID uuid)
    {
        return plugin.getConfig().getDouble("data.money." + uuid);
    }

    public static int getPlayerZone(UUID uuid)
    {
        return plugin.getConfig().getInt("data.player." + uuid);
    }

    public static void purchaseItem(InventoryClickEvent event, ItemStack item, UUID uuid, int price)
    {
        event.setCancelled(true);
        Player player = Bukkit.getPlayer(uuid);
        if(getHonorUUID(uuid) < price)
        {
            player.sendMessage("§7Fonds insuffisant.");
            player.closeInventory();
        }
        else
        {
            player.getInventory().addItem(item);
            player.closeInventory();
            player.sendMessage("§7Achat effectué: " + item.getItemMeta().getDisplayName());
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 2);
            removeHonorPointUUID(uuid, price);
        }
    }

    public static void startTimer() {
        //Bukkit.getScheduler().cancelTasks(main.getInstance());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(secondsPassed == maxTime)
                {
                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        if(plugin.getConfig().getInt("data.player." + player.getUniqueId()) >= 1)
                        {
                            getLogger().info("ajout à " + player.getUniqueId() + " : " + moneyFlat * plugin.getConfig().getInt("data.player." + player.getUniqueId()));
                            addHonorPoint(player, moneyFlat * plugin.getConfig().getInt("data.player." + player.getUniqueId()));
                        }
                    }
                }
                //getLogger().info("temps: " + secondsPassed);
                if (secondsPassed >= maxTime) { // Si 6 minutes se sont écoulées (6 minutes * 60 secondes)
                    cancel(); // Arrêtez le timer
                    getLogger().info("Timer terminé");
                    secondsPassed = 0;
                    startTimer();
                    return;
                }
                secondsPassed++;
            }
        }.runTaskTimer(main.getInstance(), 20L, 20L); // Démarrer le timer, 20L = 1 seconde
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if(event.getEntity().getKiller() != null) {
            Player killer = (Player) event.getEntity().getKiller();
            killer.sendMessage("§7Vous avez tué §e" + player.getName());
            addHonorPoint(killer, plugin.getConfig().getDouble("data.money." + player.getUniqueId()) * rewardMultiplicator);
            removeHonorPoint(player, plugin.getConfig().getDouble("data.money." + player.getUniqueId()) * rewardMultiplicator);
        }
    }
}
