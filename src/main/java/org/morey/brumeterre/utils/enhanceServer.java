package org.morey.brumeterre.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class enhanceServer implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent event)
    {
        event.setJoinMessage("§a+ §7" + event.getPlayer().getName());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event)
    {
        event.setQuitMessage("§c- §7" + event.getPlayer().getName());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setFormat("" + event.getPlayer().getName() + " §7§l» §r" + event.getMessage());
    }

}
