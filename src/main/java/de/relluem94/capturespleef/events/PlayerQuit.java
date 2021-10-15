package de.relluem94.capturespleef.events;

import static de.relluem94.capturespleef.Strings.CS_NAME;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;

public class PlayerQuit implements Listener {

    de.relluem94.capturespleef.CaptureSpleef main;

    public PlayerQuit(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void GameIntrupt(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.getCustomName().equals(TEAM_RED_NAME)) {
            main.teams.get(p).getBlock().setType(Material.AIR);
            main.teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
            main.teams.remove(p);
            main.rot.removePlayer(p);
            main.sboard.resetScores(p);
        } else if (p.getCustomName().equals(TEAM_BLUE_NAME)) {
            main.teams.get(p).getBlock().setType(Material.AIR);
            main.teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
            main.teams.remove(p);
            main.blau.removePlayer(p);
            main.sboard.resetScores(p);
        } else if (p.getCustomName().equals(CS_NAME)) {
            main.cslobby.removePlayer(p);
            main.sboard.resetScores(p);
        }

    }

}
