package org.morey.brumeterre;

import org.bukkit.plugin.java.JavaPlugin;
import org.morey.brumeterre.capture.capture;
import org.morey.brumeterre.capture.regions.test1;
import org.morey.brumeterre.utils.enhanceServer;

import java.util.logging.Logger;

public final class main extends JavaPlugin {

    private static main instance;
    public Logger log = getLogger();
    //
    //
    //
    public String version = "v0.1";
    //
    //
    //

    @Override
    public void onEnable() {

        saveDefaultConfig();

        instance = this;
        log.info("Brumeterre started.");

        getServer().getPluginManager().registerEvents(new enhanceServer(), this);
        getServer().getPluginManager().registerEvents(new capture(), this);
        getServer().getPluginManager().registerEvents(new test1(), this);

    }

    @Override
    public void onDisable() {

        log.info("Brumeterre stopped.");

    }

    public static main getInstance() {
        return instance;
    }
}
