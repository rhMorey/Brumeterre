package org.morey.brumeterre.capture.regions;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

import java.util.Random;

public class regionsSpecifications implements Listener {

    public void respawnTimer(Material oldBlock, Block block) {
        /*int max = 72000;
        int min = 36000;
        int test = (int) (Math.random() * (max - min));*/
        block.setType(Material.STONE);
        Bukkit.getScheduler().runTaskLater(main.plugin, () -> {
            block.setType(oldBlock);

        }, 70L);
        //}, 36000L);
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
        /// DIAMANT
        ///
        if (event.getBlock().getType().equals(Material.OBSIDIAN)) return;
        if (regions.getRegion(diamant.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(diamant.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.DIAMOND_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
        ///
        /// FER
        ///
        else if (regions.getRegion(fer.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(fer.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.IRON_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_IRON, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(lapis.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(lapis.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.LAPIS_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.LAPIS_LAZULI, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(cuivre.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(cuivre.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.COPPER_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_COPPER, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(emeraude.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(emeraude.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.EMERALD_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(netherite.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(netherite.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.ANCIENT_DEBRIS)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.ANCIENT_DEBRIS, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(or.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(or.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.GOLD_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.RAW_GOLD, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(redstone.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(redstone.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.REDSTONE_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.REDSTONE, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(argile.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(argile.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.CLAY)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.CLAY_BALL, 3));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(sable.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(sable.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.SAND)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.SAND, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(poudre.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(poudre.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.TUFF)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(charbon.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(charbon.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.COAL_ORE)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    player.getInventory().addItem(new ItemStack(Material.COAL, 1));
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
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
        else if (regions.getRegion(blazerod.regionName).contains(blockloc.getBlockX(), blockloc.getBlockY(), blockloc.getBlockZ())) {
            if (capture.getZoneOwner(blazerod.regionName).toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                if (block.getType().equals(Material.MAGMA_BLOCK)) {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    respawnTimer(blocktype, block);
                    Random random = new Random();
                    int randomNumber = random.nextInt(100);
                    //30% de drop une blazerod
                    if(randomNumber <= 15)
                    {
                        player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    }
                    event.setCancelled(true);
                } else {
                    if(player.getGameMode().equals(GameMode.CREATIVE)) return;
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage("§cCette zone ne vous appartient pas.");
            }
        }
    }
}