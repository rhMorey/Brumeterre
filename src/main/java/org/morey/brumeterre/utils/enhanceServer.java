package org.morey.brumeterre.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.morey.brumeterre.item.resource.crystalium;
import org.morey.brumeterre.item.weapon.emerald_sword;
import org.morey.brumeterre.main;

import static org.morey.brumeterre.main.plugin;

public class enhanceServer implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event)
    {
        event.setJoinMessage("§a+ §7" + event.getPlayer().getName());

        //
        // TEST GIVE CUSTOM ITEM
        //
        event.getPlayer().getInventory().addItem(crystalium.customItem());

        //
        // TEST GIVE CUSTOM ITEM CUSTOMMODELDATA
        //
        event.getPlayer().getInventory().addItem(emerald_sword.customItem());

        if(plugin.getConfig().get("data.player." + event.getPlayer().getUniqueId()) == null)
        {
            plugin.getConfig().set("data.player." + event.getPlayer().getUniqueId(), 0);
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event)
    {
        event.setQuitMessage("§c- §7" + event.getPlayer().getName());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setFormat(event.getPlayer().getName() + " §7§l» §r" + event.getMessage());
    }
}
