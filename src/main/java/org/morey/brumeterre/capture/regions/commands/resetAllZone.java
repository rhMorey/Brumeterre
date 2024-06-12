package org.morey.brumeterre.capture.regions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.morey.brumeterre.capture.capture;

import static org.morey.brumeterre.main.plugin;

public class resetAllZone implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(s.equalsIgnoreCase("resetzone"))
        {
            Player player = (Player) sender;

            // TOUTES LES ZONES
            plugin.getConfig().set("data.diamant", null);
            plugin.getConfig().set("data.or", null);
            plugin.getConfig().set("data.fer", null);
            plugin.getConfig().set("data.lapis", null);
            plugin.getConfig().set("data.argile", null);
            plugin.getConfig().set("data.sable", null);
            plugin.getConfig().set("data.redstone", null);
            plugin.getConfig().set("data.netherite", null);
            plugin.getConfig().set("data.cuivre", null);
            plugin.getConfig().set("data.emeraude", null);
            plugin.getConfig().set("data.blazerod", null);
            plugin.getConfig().set("data.charbon", null);
            plugin.getConfig().set("data.poudre", null);
            // TOUTES LES ZONES
            plugin.saveConfig();

            player.sendMessage("§7Toutes les zones ont étés reset.");
        }
        return false;
    }
}
