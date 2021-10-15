package de.relluem94.capturespleef.listener;

import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scoreboard.Score;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;

public class PlayerMove implements Listener {

    de.relluem94.capturespleef.CaptureSpleef main;

    public PlayerMove(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void PlayerDeath(PlayerMoveEvent evi) {

        Location lobby = new Location(main.server.getWorld(ACTIVE_WORLD), -132, 144, 272);
        Location PosRot = new Location(main.server.getWorld(ACTIVE_WORLD), -141, 138, 272);
        Location PosBlau = new Location(main.server.getWorld(ACTIVE_WORLD), -124, 138, 272);
        Player player = evi.getPlayer();

        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (player.hasPermission("rellu.lobby.spleef")) {
            if (player.getCustomName().equals(TEAM_RED_NAME)) {
                if (block.getType() == Material.PRISMARINE) {

                    Score score = main.obj.getScore(player);
                    score.setScore(score.getScore() - 1);

                    if (score.getScore() == 0) {
                        player.setCustomName(CS_NAME);

                        main.a = main.rot.getSize();
                        main.b = main.blau.getSize();

                        // player.performCommand("casp leave");
                        if (main.a < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_BLUE_NAME)) {
                                    ops.teleport(lobby);
                                    ops.setCustomName(CS_NAME);
                                    ops.getInventory().clear();
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    main.reset();
                                    ops.sendMessage(PLUGIN_PREFIX + " �4Team Rot hat gewonnen");
                                }
                            }
                        } else if (main.b < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_RED_NAME)) {
                                    ops.teleport(lobby);
                                    ops.getInventory().clear();
                                    //
                                    // Feuerwerk
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
                                    // Feuerwerk
                                    //
                                    ops.setCustomName(CS_NAME);
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    main.reset();
                                    main.teams.clear();
                                    ops.sendMessage(PLUGIN_PREFIX + " �4Team Rot hat gewonnen");
                                }
                            }
                        }

                        main.teams.get(player).getBlock().setType(Material.AIR);
                        main.teams.get(player).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                        main.teams.remove(player);
                        main.sboard.resetScores(player);

                        // player.performCommand("casp leave");
                    } else {
                        if (player.getCustomName().equals(TEAM_RED_NAME)) {
                            player.teleport(PosRot);
                        } else if (player.getCustomName().equals(TEAM_BLUE_NAME)) {
                            player.teleport(PosBlau);
                        }
                    }

                    //
                    //		Totesnachricht Team Rot
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            if (score.getScore() == 0) {
                                pla.sendMessage(PLUGIN_PREFIX + " �4" + TEAM_RED_NAME + " " + player.getDisplayName() + "�4 ist ausgeschieden");
                            } else {
                                pla.sendMessage(PLUGIN_PREFIX + " �4" + player.getCustomName() + " " + player.getDisplayName() + "�4 starb");
                            }
                        }
                    }
                } else {
                }
            }
            if (player.getCustomName().equals(TEAM_BLUE_NAME)) {
                if (block.getType() == Material.NETHER_BRICK) {

                    Score score = main.obj.getScore(player);
                    score.setScore(score.getScore() - 1);

                    if (score.getScore() == 0) {
                        player.setCustomName(CS_NAME);

                        main.a = main.rot.getSize();
                        main.b = main.blau.getSize();

                        // player.performCommand("casp leave");
                        if (main.a < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_BLUE_NAME)) {
                                    ops.teleport(lobby);
                                    ops.setCustomName(CS_NAME);
                                    ops.getInventory().clear();
//	 							ops.sendMessage("Test22");
//	 							player.sendMessage("Test22");
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.reset();
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    ops.sendMessage(PLUGIN_PREFIX + " �4Team Rot hat gewonnen");
                                    main.cSM("�5[Test]", "�4Rot!");
                                }
                            }
                        } else if (main.b < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_RED_NAME)) {
                                    ops.teleport(lobby);
                                    ops.getInventory().clear();
//	 							ops.sendMessage("Test22");
//	 							player.sendMessage("Test22");
                                    //
                                    // Feuerwerk
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
                                    // Feuerwerk
                                    //
                                    ops.setCustomName(CS_NAME);
                                    main.teams.get(ops).getBlock().setType(Material.AIR);
                                    main.teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    main.teams.clear();
                                    main.reset();
                                    ops.sendMessage(PLUGIN_PREFIX + " �4Team Rot hat gewonnen");
                                    main.cSM("�5[Test]", "�1Blau!");
                                }
                            }
                        }

                        main.teams.get(player).getBlock().setType(Material.AIR);
                        main.teams.get(player).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                        main.teams.remove(player);
                        main.sboard.resetScores(player);
                    } else {
                        if (player.getCustomName().equals(TEAM_RED_NAME)) {
                            player.teleport(PosRot);
                        } else if (player.getCustomName().equals(TEAM_BLUE_NAME)) {
                            player.teleport(PosBlau);
                        }
                    }

                    //
                    // Totesnachricht Team Blau
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            if (score.getScore() == 0) {
                                pla.sendMessage(PLUGIN_PREFIX + " �1" + TEAM_BLUE_NAME + " " + player.getDisplayName() + "�1 ist ausgeschieden");
                            } else {
                                pla.sendMessage(PLUGIN_PREFIX + " �1" + player.getCustomName() + " " + player.getDisplayName() + "�1 starb");
                            }

                        }
                    }
                } else {
                }
            } else {
            }
        } else {
        }
    }
}
