package org.morey.brumeterre.capture;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.morey.brumeterre.capture.point.economyTimer;

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

    /*public String getZone(Player player, RegionManager regions, String region)
    {
        Location loc = player.getLocation();
        ProtectedRegion pregion = regions.getRegion(region);
        if(regions.getRegion(region).contains(loc.getBlockX(), loc.blockY(), loc.blockZ()))
        {
            return pregion.toString();
        }
        return null;
    }*/

    public static Logger log = getLogger();
    public static void printZone(Player player, String zone)
    {
        Bukkit.broadcastMessage("\n§c§l ! §e" + player.getName() + " a pris le contrôle de la zone " + zone + "\n\n");
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
            economyTimer.removeHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.lostOnCapture);
            log.info("uuid avant: " + UUID.fromString(capture.getZoneOwner(regionName)) + " soustrait: " + economyTimer.lostOnCapture);
            capture.setZoneOwner(regionName, player);
            log.info("uuid après: " + UUID.fromString(capture.getZoneOwner(regionName)) + " ajoute: " + economyTimer.winOnCapture);
            economyTimer.addHonorPointUUID(UUID.fromString(capture.getZoneOwner(regionName)), economyTimer.winOnCapture);
            event.setCancelled(true);
            //player.sendMessage("§7Vous avez capturé la zone§e " + regionName + "§7.");jhgf
            capture.printZone(player, regionName);
        }
        else
        {
            player.sendMessage("§7Vous ne pouvez pas capturer votre propre zone.");
            event.setCancelled(true);
        }
    }

}
