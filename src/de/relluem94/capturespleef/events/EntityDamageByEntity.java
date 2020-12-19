package de.relluem94.capturespleef.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EntityDamageByEntity implements Listener {

    de.relluem94.capturespleef.rellu main;

    public EntityDamageByEntity(de.relluem94.capturespleef.rellu instance) {
        main = instance;
    }

    @EventHandler
    public void SchneeballSchaden(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            Player p = (Player) e.getEntity();
            Snowball snowball = (Snowball) e.getDamager();
            ProjectileSource shooter = snowball.getShooter();
            if (shooter instanceof Player) {
                Player ps = (Player) shooter;
                if (ps.hasPermission("rellu.lobby.snowball")) {
                    if (ps.getInventory().getItemInHand().getType() == Material.SNOWBALL && p.getInventory().getItemInHand().getType() == Material.SNOWBALL) {

                        if (ps.getCustomName().equals("TeamRot")) {

                            if (p.getCustomName().equals("TeamBlau")) {
                                p.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (ps.getCustomName().equals("TeamBlau")) {
                            if (p.getCustomName().equals("TeamRot")) {
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

                } else if (ps.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(p.getInventory().getItemInHand().getType() == Material.SNOWBALL)
                        || p.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(ps.getInventory().getItemInHand().getType() == Material.SNOWBALL)) {
                    // Hier Team Rot
                    if (ps.getCustomName().equals("TeamRot")) {

                        if (p.getCustomName().equals("TeamBlau")) {
                            p.setFireTicks(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } // Hier Team Blau
                    else if (ps.getCustomName().equals("TeamBlau")) {
                        if (p.getCustomName().equals("TeamRot")) {
                            p.setFallDistance(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (!(ps.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(p.getInventory().getItemInHand().getType() == Material.SNOWBALL)
                            || !(p.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(ps.getInventory().getItemInHand().getType() == Material.SNOWBALL)) {
                        // Hier Team Rot
                        if (ps.getCustomName().equals("TeamRot")) {

                            if (p.getCustomName().equals("TeamBlau")) {
                                p.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (ps.getCustomName().equals("TeamBlau")) {
                            if (p.getCustomName().equals("TeamRot")) {
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
