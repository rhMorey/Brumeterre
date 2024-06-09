package org.morey.brumeterre.capture.regions;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.event.block.BreakBlockEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.morey.brumeterre.capture.capture;
import org.morey.brumeterre.main;

import java.util.ArrayList;
import java.util.Set;

public class regionsSpecifications implements Listener {

    public void respawnTimer(Material oldBlock, Block block)
    {
        block.setType(Material.STONE);
        Bukkit.getScheduler().runTaskLater(main.plugin, () -> {
            block.setType(oldBlock);

        }, 100L);
        //}, 36000L); 30 minutes
    }

    @EventHandler
    public void onBreakResources(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        World w = BukkitAdapter.adapt(player.getWorld());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(w);
        Block block = event.getBlock();
        Location blockloc = block.getLocation();
        Material blocktype = event.getBlock().getType();

            if (regions.getRegion("test1").contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())
                    &&
                    capture.getZoneOwner("test1").equalsIgnoreCase(player.getUniqueId().toString()))
            {
                if (block.getType().equals(Material.DIAMOND_ORE))
                {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    event.setCancelled(true);
                }
                else
                {
                    event.setCancelled(true);
                }
            }
            if (regions.getRegion("test2").contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())
                    &&
                    capture.getZoneOwner("test1").equalsIgnoreCase(player.getUniqueId().toString()))
            {
                if (block.getType().equals(Material.IRON_ORE))
                {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_IRON, 1));
                    event.setCancelled(true);
                }
                else
                {
                    event.setCancelled(true);
                }
            }
            else
            {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
    }
