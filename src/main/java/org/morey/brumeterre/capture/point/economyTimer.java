package org.morey.brumeterre.capture.point;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.morey.brumeterre.main;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.morey.brumeterre.main.plugin;

public class economyTimer {

    public static int secondsPassed = 0;
    public static int maxTime = 30;
    public static int moneyFlat = 125;
    public static void startTimer() {
        //Bukkit.getScheduler().cancelTasks(main.getInstance());
        new BukkitRunnable() {
            @Override
            public void run() {
                if(secondsPassed == maxTime)
                {
                    for(Player player : Bukkit.getOnlinePlayers())
                    {
                        if(plugin.getConfig().getInt("data.player." + player.getUniqueId()) >= 1)
                        {
                            int moneyGain = moneyFlat * plugin.getConfig().getInt("data.player." + player.getUniqueId());
                            plugin.getConfig().set("data.money." + player.getUniqueId(), plugin.getConfig().getInt("data.money." + player.getUniqueId()) + (moneyGain));
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6Marque d'honneur §7§l» §e" + plugin.getConfig().getInt("data.money." + player.getUniqueId()) + "§7(+" + moneyGain + ")"));
                            plugin.saveConfig();
                        }
                    }
                }
                getLogger().info("temps: " + secondsPassed);
                if (secondsPassed >= maxTime) { // Si 6 minutes se sont écoulées (6 minutes * 60 secondes)
                    cancel(); // Arrêtez le timer
                    getLogger().info("Timer terminé");
                    secondsPassed = 0;
                    startTimer();
                    return;
                }
                secondsPassed++;
            }
        }.runTaskTimer(main.getInstance(), 20L, 20L); // Démarrer le timer, 20L = 1 seconde
    }
}
