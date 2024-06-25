package org.morey.brumeterre.capture.regions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static org.morey.brumeterre.main.plugin;

public class resetAllPoint implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(s.equalsIgnoreCase("resetpoint"))
        {
            if(args.length > 0)
            {
                if (isUUID(args[0])) {
                    sender.sendMessage("§7Les points de l'UUID §e" + args[0] + "§7 ont étés supprimés.");
                    plugin.getConfig().set("data.player." + args[0], null);
                } else {
                    sender.sendMessage("§7Votre UUID (" + args[0] + ") n'est pas valide.");
                }
            }
            else
            {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    plugin.getConfig().set("data.player." + player.getUniqueId(), null);

                    plugin.saveConfig();
                }
            }
        }
        return false;
    }

    private boolean isUUID(String str) {
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
