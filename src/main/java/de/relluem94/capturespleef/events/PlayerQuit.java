package de.relluem94.capturespleef.events;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.CaptureSpleef.blau;
import static de.relluem94.capturespleef.CaptureSpleef.cslobby;
import static de.relluem94.capturespleef.CaptureSpleef.rot;
import static de.relluem94.capturespleef.CaptureSpleef.sboard;
import static de.relluem94.capturespleef.CaptureSpleef.teams;
import static de.relluem94.capturespleef.Strings.CS_NAME;

public class PlayerQuit implements Listener {

    @EventHandler
    public void GameIntrupt(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = Objects.requireNonNull(p.getCustomName());
        
        switch (name) {
            case TEAM_RED_NAME:
                teams.get(p).getBlock().setType(Material.AIR);
                teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                teams.remove(p);
                rot.removePlayer(p);
                sboard.resetScores(p);
                break;
            case TEAM_BLUE_NAME:
                teams.get(p).getBlock().setType(Material.AIR);
                teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                teams.remove(p);
                blau.removePlayer(p);
                sboard.resetScores(p);
                break;
            case CS_NAME:
                cslobby.removePlayer(p);
                sboard.resetScores(p);
                break;
            default:
                break;
        }
    }
}