package de.relluem94.capturespleef.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;

public class SnowBallDamage implements Listener {

    @EventHandler
    public void SchneeballSchaden(@NotNull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball snowball) {
            Player p = (Player) e.getEntity();
            ProjectileSource shooter = snowball.getShooter();
            if (shooter instanceof Player ps) {
                if (ps.hasPermission("rellu.lobby.snowball")) {
                    if (ps.getInventory().getItemInHand().getType().equals(Material.SNOWBALL) && p.getInventory().getItemInHand().getType().equals(Material.SNOWBALL)) {
                        if (ps.getCustomName().equals(TEAM_RED_NAME)) {
                            if (p.getCustomName().equals(TEAM_BLUE_NAME)) {
                                p.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (ps.getCustomName().equals(TEAM_BLUE_NAME)) {
                            if (p.getCustomName().equals(TEAM_RED_NAME)) {
                                p.setFallDistance(4);
                            } else {
                                e.setCancelled(true);
                            }
                        }
                    } else {
                        Location ploc = p.getLocation();
                        Location psloc = ps.getLocation();
                        p.teleport(psloc);
                        ps.teleport(ploc);
                    }
                } else if (ps.getInventory().getItemInHand().getType().equals(Material.SNOWBALL) && !(p.getInventory().getItemInHand().getType().equals(Material.SNOWBALL))
                        || p.getInventory().getItemInHand().getType().equals(Material.SNOWBALL) && !(ps.getInventory().getItemInHand().getType().equals(Material.SNOWBALL))) {
                    // Hier Team Rot
                    if (ps.getCustomName().equals(TEAM_RED_NAME)) {

                        if (p.getCustomName().equals(TEAM_BLUE_NAME)) {
                            p.setFireTicks(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } // Hier Team Blau
                    else if (ps.getCustomName().equals(TEAM_BLUE_NAME)) {
                        if (p.getCustomName().equals(TEAM_RED_NAME)) {
                            p.setFallDistance(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (!(ps.getInventory().getItemInHand().getType().equals(Material.SNOWBALL)) && !(p.getInventory().getItemInHand().getType().equals(Material.SNOWBALL))
                            || !(p.getInventory().getItemInHand().getType().equals(Material.SNOWBALL)) && !(ps.getInventory().getItemInHand().getType().equals(Material.SNOWBALL))) {
                        // Hier Team Rot
                        if (ps.getCustomName().equals(TEAM_RED_NAME)) {

                            if (p.getCustomName().equals(TEAM_BLUE_NAME)) {
                                p.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (ps.getCustomName().equals(TEAM_BLUE_NAME)) {
                            if (p.getCustomName().equals(TEAM_RED_NAME)) {
                                p.setFallDistance(4);
                            } else {
                                e.setCancelled(true);
                            }
                        }

                    } else {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
