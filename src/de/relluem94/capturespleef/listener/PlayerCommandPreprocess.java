package de.relluem94.capturespleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener {

    de.relluem94.capturespleef.rellu main;

    public PlayerCommandPreprocess(de.relluem94.capturespleef.rellu instance) {
        main = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if (event.getPlayer().getCustomName().equals(main.csname) || event.getPlayer().getCustomName().equals("TeamRot") || event.getPlayer().getCustomName().equals("TeamBlau")) {
            if (event.getMessage().toLowerCase().startsWith("/casp")) {
                event.setCancelled(false);
            } else {
                event.getPlayer().sendMessage(main.prefix + " �aDu hast darauf keine Rechte!");
                event.setCancelled(true);
            }
        }
    }

}
