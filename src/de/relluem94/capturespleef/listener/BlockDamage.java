package de.relluem94.capturespleef.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class BlockDamage implements Listener {

    de.relluem94.capturespleef.rellu main;

    public BlockDamage(de.relluem94.capturespleef.rellu instance) {
        main = instance;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void GameItSelf(BlockDamageEvent ev) {

        Player player = ev.getPlayer();
        Location lobby = new Location(main.server.getWorld(main.lobby), -132, 144, 272);

        if (player.hasPermission("rellu.lobby.spleef")) {
            // Team Blau
            if (player.getItemInHand().getType() == Material.DIAMOND_SHOVEL) {
                Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                //
                //
                if (block.getType() == Material.PRISMARINE) {
                    player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_STEP, 1F, 0F);
                    //
                    //		Team Blau Gewonnen
                    //
                    if (player.getCustomName().equals("TeamBlau")) {
                        if (ev.getBlock().getType() == Material.GOLD_BLOCK) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals("TeamRot")) {
                                    ops.teleport(lobby);
                                    ops.getInventory().clear();
                                    ops.setCustomName(main.csname);
                                    main.reset();
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    main.sboard.resetScores(ops);
                                    ops.sendMessage("§d[CaptureSpleef] §1Team Blau hat gewonnen");
                                }
                            }

                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals("TeamBlau")) {
                                    ops.teleport(lobby);
                                    //
                                    //		Feuerwerk
                                    //
                                    double x1 = ops.getEyeLocation().getX();
                                    double y1 = ops.getEyeLocation().getY();
                                    double z1 = ops.getEyeLocation().getZ();
                                    //
                                    double y2 = y1 + 1;
                                    Location loc = new Location(ops.getWorld(), x1, y2, z1);
                                    // Firework
                                    Firework fw = (Firework) ops.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                                    FireworkMeta fwm = fw.getFireworkMeta();
                                    //random
                                    Random r22 = new Random();
                                    //sets type
                                    int rt = r22.nextInt(4) + 1;
                                    Type type = Type.BALL;
                                    if (rt == 1) {
                                        type = Type.BALL;
                                    }
                                    if (rt == 2) {
                                        type = Type.BALL_LARGE;
                                    }
                                    if (rt == 3) {
                                        type = Type.BURST;
                                    }
                                    if (rt == 4) {
                                        type = Type.STAR;
                                    }
                                    //colors
                                    int r = r22.nextInt(256);
                                    int b = r22.nextInt(256);
                                    int g = r22.nextInt(256);
                                    Color c11 = Color.fromRGB(r, g, b);
                                    r = r22.nextInt(256);
                                    b = r22.nextInt(256);
                                    g = r22.nextInt(256);
                                    Color c21 = Color.fromRGB(r, g, b);
                                    //effect
                                    FireworkEffect effect = FireworkEffect.builder().flicker(r22.nextBoolean()).withColor(c11).withFade(c21).with(type).trail(r22.nextBoolean()).build();
                                    //applied effects
                                    fwm.addEffect(effect);
                                    //random power! moar sulphur!
                                    int rp = r22.nextInt(2) + 1;
                                    fwm.setPower(rp);
                                    fw.setFireworkMeta(fwm);
                                    //
                                    //		Feuerwerk
                                    //
                                    main.reset();
                                    ops.setCustomName(main.csname);
                                    ops.getInventory().clear();
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    main.teams.clear();
                                    main.sboard.resetScores(ops);
                                    ops.sendMessage("§d[CaptureSpleef] §1Team Blau hat gewonnen");
                                }
                            }
                        } //
                        //
                        //
                        else if (ev.getBlock().getType() == Material.IRON_BLOCK) {
                        } else if (ev.getBlock().getType() == Material.NETHER_BRICK) {
                            main.old.put(ev.getBlock().getLocation(), ev.getBlock().getType());
                            ev.getBlock().setType(Material.PRISMARINE);

                        } else {
                            // Keine Anderen Blöcke können zerstört werden
                            ev.setCancelled(true);
                        }
                    } else {
                        ev.setCancelled(true);
                    }
                } else {
                    ev.setCancelled(true);
                }
            } // Team Rot
            else if (player.getItemInHand().getType() == Material.DIAMOND_HOE) {
                Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                //
                //
                if (block.getType() == Material.NETHER_BRICK) {
                    player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_STEP, 1F, 0F);
                    //
                    //		Team Rot Gewonnen
                    //
                    if (player.getCustomName().equals("TeamRot")) {
                        if (ev.getBlock().getType() == Material.IRON_BLOCK) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals("TeamBlau")) {
                                    ops.teleport(lobby);
                                    ops.setCustomName(main.csname);
                                    ops.getInventory().clear();
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    main.sboard.resetScores(ops);
                                    main.reset();
                                    ops.sendMessage("§d[CaptureSpleef] §4Team Rot hat gewonnen");
                                }
                            }

                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals("TeamRot")) {
                                    ops.teleport(lobby);
                                    ops.getInventory().clear();
                                    //
                                    //		Feuerwerk
                                    //
                                    double x1 = ops.getEyeLocation().getX();
                                    double y1 = ops.getEyeLocation().getY();
                                    double z1 = ops.getEyeLocation().getZ();
                                    //
                                    double y2 = y1 + 1;
                                    Location loc = new Location(ops.getWorld(), x1, y2, z1);
                                    // Firework
                                    Firework fw = (Firework) ops.getWorld().spawnEntity(loc, EntityType.FIREWORK);
                                    FireworkMeta fwm = fw.getFireworkMeta();
                                    //random
                                    Random r22 = new Random();
                                    //sets type
                                    int rt = r22.nextInt(4) + 1;
                                    Type type = Type.BALL;
                                    if (rt == 1) {
                                        type = Type.BALL;
                                    }
                                    if (rt == 2) {
                                        type = Type.BALL_LARGE;
                                    }
                                    if (rt == 3) {
                                        type = Type.BURST;
                                    }
                                    if (rt == 4) {
                                        type = Type.STAR;
                                    }
                                    //colors
                                    int r = r22.nextInt(256);
                                    int b = r22.nextInt(256);
                                    int g = r22.nextInt(256);
                                    Color c11 = Color.fromRGB(r, g, b);
                                    r = r22.nextInt(256);
                                    b = r22.nextInt(256);
                                    g = r22.nextInt(256);
                                    Color c21 = Color.fromRGB(r, g, b);
                                    //effect
                                    FireworkEffect effect = FireworkEffect.builder().flicker(r22.nextBoolean()).withColor(c11).withFade(c21).with(type).trail(r22.nextBoolean()).build();
                                    //applied effects
                                    fwm.addEffect(effect);
                                    //random power! moar sulphur!
                                    int rp = r22.nextInt(2) + 1;
                                    fwm.setPower(rp);
                                    fw.setFireworkMeta(fwm);
                                    //
                                    //		Feuerwerk
                                    //
                                    ops.setCustomName(main.csname);
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    main.sboard.resetScores(ops);
                                    main.reset();
                                    ops.sendMessage("§d[CaptureSpleef] §4Team Rot hat gewonnen");
                                }
                            }
                        } //
                        //
                        //
                        else if (ev.getBlock().getType() == Material.GOLD_BLOCK) {
                        } else if (ev.getBlock().getType() == Material.PRISMARINE) {
                            main.old.put(ev.getBlock().getLocation(), ev.getBlock().getType());
                            ev.getBlock().setType(Material.NETHER_BRICK);
                        } else {
                            // Keine Anderen Blöcke können zerstört werden
                            ev.setCancelled(true);
                        }
                    } else {
                        ev.setCancelled(true);
                    }
                } else {
                    ev.setCancelled(true);
                }
            } else {
                // Komplett
                ev.setCancelled(true);
            }
        } else {
            // Komplett
            ev.setCancelled(true);
        }
    }

}
