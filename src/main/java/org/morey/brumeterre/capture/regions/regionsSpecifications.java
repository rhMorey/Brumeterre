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
import java.util.Random;
import java.util.Set;

public class regionsSpecifications implements Listener {

    public void respawnTimer(Material oldBlock, Block block) {
        /*int max = 72000;
        int min = 36000;
        int test = (int) (Math.random() * (max - min));*/
        block.setType(Material.STONE);
        Bukkit.getScheduler().runTaskLater(main.plugin, () -> {
            block.setType(oldBlock);

        }, 70L);
        //}, 36000L); 30 minutes
    }

    @EventHandler
    public void onBreakResources(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World w = BukkitAdapter.adapt(player.getWorld());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(w);
        Block block = event.getBlock();
        Location blockloc = block.getLocation();
        Material blocktype = event.getBlock().getType();

        ///
        /// TEST ZONE
        ///
        if (event.getBlock().getType().equals(Material.OBSIDIAN)) return;
        if (regions.getRegion(diamant.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(diamant.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.DIAMOND_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
        if (regions.getRegion(fer.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(fer.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.IRON_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_IRON, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// LAPIS LAZULI
        ///
        if (regions.getRegion(lapis.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(lapis.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.LAPIS_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// CUIVRE
        ///
        if (regions.getRegion(cuivre.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(cuivre.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.COPPER_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_COPPER, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// EMERAUDE
        ///
        if (regions.getRegion(emeraude.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(emeraude.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.EMERALD_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// NETHERITE
        ///
        if (regions.getRegion(netherite.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(netherite.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.ANCIENT_DEBRIS)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.ANCIENT_DEBRIS, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// OR
        ///
        if (regions.getRegion(or.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(or.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.GOLD_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_GOLD, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// REDSTONE
        ///
        if (regions.getRegion(redstone.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(redstone.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.REDSTONE_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.REDSTONE, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// ARGILE
        ///
        if (regions.getRegion(argile.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(argile.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.CLAY)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.CLAY_BALL, 3));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// SABLE
        ///
        if (regions.getRegion(sable.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(sable.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.SAND)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.SAND, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// POUDRE
        ///
        if (regions.getRegion(poudre.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(poudre.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.TUFF)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// CHARBON
        ///
        if (regions.getRegion(charbon.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(charbon.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.COAL_ORE)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.COAL, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
        ///
        /// BLAZEROD
        ///
        if (regions.getRegion(blazerod.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.blockZ())) {
            if (capture.getZoneOwner(blazerod.regionName).equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.MAGMA_BLOCK)) {
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
    }
}
