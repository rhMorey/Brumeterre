package org.morey.brumeterre.capture.regions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.morey.brumeterre.main.plugin;

public class resetAllZone implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(s.equalsIgnoreCase("resetzone"))
        {
            Player player = (Player) sender;

            // TOUTES LES ZONES
            plugin.getConfig().set("data.test1", null);
            plugin.getConfig().set("data.test2", null);
            // TOUTES LES ZONES
            plugin.saveConfig();

            player.sendMessage("§7Toutes les zones ont étés reset.");
        }
        return false;
    }
}
