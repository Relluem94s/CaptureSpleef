package de.relluem94.capturespleef.listener;

import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.NO_PERM;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;

public class CSPlayerCommandPreprocess implements Listener {

    de.relluem94.capturespleef.CaptureSpleef main;

    public CSPlayerCommandPreprocess(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if (event.getPlayer().getCustomName().equals(CS_NAME) || event.getPlayer().getCustomName().equals(TEAM_RED_NAME) || event.getPlayer().getCustomName().equals(TEAM_BLUE_NAME)) {
            if (event.getMessage().toLowerCase().startsWith("/casp")) {
                event.setCancelled(false);
            } else {
                event.getPlayer().sendMessage(PLUGIN_PREFIX + NO_PERM);
                event.setCancelled(true);
            }
        }
    }

}
