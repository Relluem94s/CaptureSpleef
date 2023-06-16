package de.relluem94.capturespleef.listener;

import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.NO_PERM;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_FORMS_SPACER_MESSAGE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CSPlayerCommandPreprocess implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if (event.getPlayer().getCustomName().equals(CS_NAME) || event.getPlayer().getCustomName().equals(TEAM_RED_NAME) || event.getPlayer().getCustomName().equals(TEAM_BLUE_NAME)) {
            if (event.getMessage().toLowerCase().startsWith("/casp")) {
                event.setCancelled(false);
            } else {
                event.getPlayer().sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + NO_PERM);
                event.setCancelled(true);
            }
        }
    }
}
