package org.morey.brumeterre.capture.regions;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.morey.brumeterre.capture.capture;

import static org.morey.brumeterre.main.plugin;

public class cuivre implements Listener {

    public static String regionName = "cuivre";
    @EventHandler
    public void onBreakObsidianInZone(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Location blockLoc = event.getBlock().getLocation();
        World w = BukkitAdapter.adapt(player.getWorld());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(w);

        if(event.getBlock().getType().equals(Material.OBSIDIAN) && regions.getRegion(regionName).contains(blockLoc.getBlockX(), blockLoc.getBlockY(), blockLoc.getBlockZ()))
        {
            if(capture.getZoneOwner(regionName) == null || !capture.getZoneOwner(regionName).equalsIgnoreCase(player.getUniqueId().toString()))
            {
                if(capture.getZoneOwner(regionName) != null)
                {
                    plugin.getConfig().set("data.player." + capture.getZoneOwner(regionName), plugin.getConfig().getInt("data.player." + capture.getZoneOwner(regionName)) - 1);
                    plugin.getConfig().set("data.player." + player.getUniqueId(), plugin.getConfig().getInt("data.player." + player.getUniqueId()) + 1);
                }
                else
                {
                    plugin.getConfig().set("data.player." + player.getUniqueId(), plugin.getConfig().getInt("data.player." + player.getUniqueId()) + 1);
                }
                capture.setZoneOwner(regionName, player);
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

}
