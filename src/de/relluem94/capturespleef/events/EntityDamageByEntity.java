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
            Player pla = (Player) e.getEntity();
            Snowball snowball = (Snowball) e.getDamager();
            ProjectileSource shooter = snowball.getShooter();
            if (shooter instanceof Player) {
                Player plo = (Player) shooter;
                if (plo.hasPermission("rellu.lobby.snowball")) {
                    if (plo.getInventory().getItemInHand().getType() == Material.SNOWBALL && pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) {

                        if (plo.getCustomName().equals("TeamRot")) {

                            if (pla.getCustomName().equals("TeamBlau")) {
                                pla.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (plo.getCustomName().equals("TeamBlau")) {
                            if (pla.getCustomName().equals("TeamRot")) {
                                pla.setFallDistance(4);
                            } else {
                                e.setCancelled(true);
                            }
                        }
                    } else {
                        Location lolo = pla.getLocation();
                        Location polo = plo.getLocation();
                        pla.teleport(polo);
                        plo.teleport(lolo);
                    }

                } else if (plo.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL)
                        || pla.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL)) {
                    // Hier Team Rot
                    if (plo.getCustomName().equals("TeamRot")) {

                        if (pla.getCustomName().equals("TeamBlau")) {
                            pla.setFireTicks(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } // Hier Team Blau
                    else if (plo.getCustomName().equals("TeamBlau")) {
                        if (pla.getCustomName().equals("TeamRot")) {
                            pla.setFallDistance(4);
                        } else {
                            e.setCancelled(true);
                        }
                    } else if (!(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL)
                            || !(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL)) {
                        // Hier Team Rot
                        if (plo.getCustomName().equals("TeamRot")) {

                            if (pla.getCustomName().equals("TeamBlau")) {
                                pla.setFireTicks(4);
                            } else {
                                e.setCancelled(true);
                            }
                        } // Hier Team Blau
                        else if (plo.getCustomName().equals("TeamBlau")) {
                            if (pla.getCustomName().equals("TeamRot")) {
                                pla.setFallDistance(4);
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
