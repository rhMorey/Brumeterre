package org.morey.brumeterre.capture;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

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
}
