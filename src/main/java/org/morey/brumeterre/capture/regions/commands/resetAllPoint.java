package org.morey.brumeterre.capture.regions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.morey.brumeterre.main.plugin;

public class resetAllPoint implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(s.equalsIgnoreCase("resetpoint"))
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                plugin.getConfig().set("data.player." + player.getUniqueId(), null);

                plugin.saveConfig();
            }
        }
        return false;
    }
}
