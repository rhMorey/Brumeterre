package org.morey.brumeterre.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.morey.brumeterre.capture.capture;
import org.morey.brumeterre.capture.economy.economyTimer;

import static org.morey.brumeterre.main.plugin;

public class whoisCmds implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(s.equalsIgnoreCase("whois"))
        {
            if(args.length == 0)
            {
                sender.sendMessage("§cVeuillez indiquer un joueur.");
            }
            if(args.length == 1)
            {
                if(Bukkit.getPlayer(args[0]) == null) sender.sendMessage("§cCe joueur n'existe pas.");
                if(Bukkit.getPlayer(args[0]).getName().equalsIgnoreCase(args[0]))
                {
                    Player player = Bukkit.getPlayer(args[0]);
                    sender.sendMessage("§7/// INFO JOUEUR: §e" + args[0] + "§7 ///\n §7Les informations doivent rester privées." +
                            "\n§fUUID: §e" + player.getUniqueId() +
                            "\n§fIP: §e" + player.getAddress() +
                            "\n§fHonneur: §e" + economyTimer.getHonorUUID(player.getUniqueId()) +
                            "\n§fZones possédés: §e" + economyTimer.getPlayerZone(player.getUniqueId()));
                }
            }
        }

        return false;
    }
}
