package org.morey.brumeterre.capture;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.morey.brumeterre.capture.economy.economyTimer;

import java.util.UUID;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;
import static org.morey.brumeterre.main.plugin;

public class capture implements Listener {

    public static void setZoneOwner(String zone, Player player)
    {
        plugin.getConfig().set("data." + zone, player.getUniqueId().toString());
        plugin.saveConfig();
    }

    public static String getZoneOwner(String zone)
    {
        return (String) plugin.getConfig().get("data." + zone);
    }

    public static Logger log = getLogger();
    public static void printZone(Player player, String zone)
    {
        Bukkit.broadcastMessage("\n§c§l ! §e" + player.getName() + " a pris le contrôle de la zone " + zone + "\n");
    }

    public static void runCapture(BlockBreakEvent event, Player player, String regionName)
    {
        if(capture.getZoneOwner(regionName) == null || !capture.getZoneOwner(regionName).toString().equalsIgnoreCase(player.getUniqueId().toString()))
        {
            if(capture.getZoneOwner(regionName) != null)
            {
                economyTimer.removeHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.lostOnCapture);
                log.info("uuid avant: " + UUID.fromString(capture.getZoneOwner(regionName)) + " soustrait: " + economyTimer.lostOnCapture);
                plugin.getConfig().set("data.player." + capture.getZoneOwner(regionName), plugin.getConfig().getInt("data.player." + capture.getZoneOwner(regionName)) - 1);
                plugin.getConfig().set("data.player." + player.getUniqueId(), plugin.getConfig().getInt("data.player." + player.getUniqueId()) + 1);
                plugin.saveConfig();
                log.info("uuid après: " + UUID.fromString(capture.getZoneOwner(regionName)) + " ajoute: " + economyTimer.winOnCapture);
                economyTimer.addHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.winOnCapture);
            }
            else
            {
                plugin.getConfig().set("data.player." + player.getUniqueId(), plugin.getConfig().getInt("data.player." + player.getUniqueId()) + 1);
                plugin.saveConfig();
            }
            if(capture.getZoneOwner(regionName) == null)
            {
                capture.setZoneOwner(regionName, player);
                log.info("uuid après: " + UUID.fromString(capture.getZoneOwner(regionName)) + " ajoute: " + economyTimer.winOnCapture);
                economyTimer.addHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.winOnCapture);
            }
            else
            {
                economyTimer.removeHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.lostOnCapture);
                log.info("uuid avant: " + UUID.fromString(capture.getZoneOwner(regionName)) + " soustrait: " + economyTimer.lostOnCapture);
                capture.setZoneOwner(regionName, player);
                log.info("uuid après: " + UUID.fromString(capture.getZoneOwner(regionName)) + " ajoute: " + economyTimer.winOnCapture);
                economyTimer.addHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.winOnCapture);
            }
            event.setCancelled(true);
            //player.sendMessage("§7Vous avez capturé la zone§e " + regionName + "§7.");
            capture.printZone(player, regionName);
        }
        else
        {
            player.sendMessage("§7Vous ne pouvez pas capturer votre propre zone.");
            event.setCancelled(true);
        }
    }

}
