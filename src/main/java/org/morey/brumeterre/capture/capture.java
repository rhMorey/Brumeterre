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

public class capture implements Listener {

    @EventHandler
    public void onBreakCopper(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        World w = BukkitAdapter.adapt(player.getWorld());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(w);

        /*if(event.getBlock().getType().equals(Material.OBSIDIAN))
        {
            event.setCancelled(true);
        }*/
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
