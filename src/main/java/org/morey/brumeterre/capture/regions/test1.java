package org.morey.brumeterre.capture.regions;

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

public class test1 implements Listener {

    private String regionName = "test1";
    @EventHandler
    public void onBreakCopper(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Location playerLoc = player.getLocation();
        World w = BukkitAdapter.adapt(player.getWorld());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(w);

        if(event.getBlock().getType().equals(Material.OBSIDIAN))
        {
            if(regions.getRegion(regionName).contains(playerLoc.getBlockX(), playerLoc.getBlockY(), playerLoc.getBlockZ()))
            {
                event.setCancelled(true);
                player.sendMessage("Vous avez cassé le bloc dans la région " + regionName);
            }
        }
    }

}
