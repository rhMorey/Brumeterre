package org.morey.brumeterre.capture.economy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.morey.brumeterre.main.plugin;

public class honneur implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(s.equalsIgnoreCase("honneur"))
        {
            Player player = (Player) sender;
            player.sendMessage("§6Honneur§7: §e" + plugin.getConfig().getInt("data.money." + player.getUniqueId()));
        }
        return false;
    }
}
