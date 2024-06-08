package org.morey.brumeterre;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class main extends JavaPlugin {

    public Logger log = getLogger();

    @Override
    public void onEnable() {

        log.info("Brumeterre started.");
        //ttest

    }

    @Override
    public void onDisable() {

        log.info("Brumeterre stopped.");

    }
}
