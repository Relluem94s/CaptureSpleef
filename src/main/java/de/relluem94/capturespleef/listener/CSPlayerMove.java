package de.relluem94.capturespleef.listener;

import static de.relluem94.capturespleef.CaptureSpleef.a;
import static de.relluem94.capturespleef.CaptureSpleef.b;
import static de.relluem94.capturespleef.CaptureSpleef.blau;
import static de.relluem94.capturespleef.CaptureSpleef.obj;
import static de.relluem94.capturespleef.CaptureSpleef.reset;
import static de.relluem94.capturespleef.CaptureSpleef.rot;
import static de.relluem94.capturespleef.CaptureSpleef.scoreBoard;
import static de.relluem94.capturespleef.CaptureSpleef.teams;
import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_FORMS_SPACER_MESSAGE;
import static de.relluem94.minecraft.server.spigot.essentials.helpers.ChatHelper.consoleSendMessage;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
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
import org.jetbrains.annotations.NotNull;

public class CSPlayerMove implements Listener {

    @EventHandler
    public void PlayerDeath(@NotNull PlayerMoveEvent evi) {
        Location lobby = new Location(Bukkit.getWorld(ACTIVE_WORLD), -132, 144, 272);
        Location PosRot = new Location(Bukkit.getWorld(ACTIVE_WORLD), -141, 138, 272);
        Location PosBlau = new Location(Bukkit.getWorld(ACTIVE_WORLD), -124, 138, 272);
        Player player = evi.getPlayer();

        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (player.hasPermission("rellu.lobby.spleef")) {
            if (player.getCustomName().equals(TEAM_RED_NAME)) {
                if (block.getType() == Material.PRISMARINE) {

                    Score score = obj.getScore(player);
                    score.setScore(score.getScore() - 1);

                    if (score.getScore() == 0) {
                        player.setCustomName(CS_NAME);

                        a = rot.getSize();
                        b = blau.getSize();

                        // player.performCommand("casp leave");
                        if (a < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_BLUE_NAME)) {
                                    ops.teleport(lobby);
                                    ops.setCustomName(CS_NAME);
                                    ops.getInventory().clear();
                                    teams.get(ops).getBlock().setType(Material.AIR);
                                    teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    reset();
                                    ops.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4Team Rot hat gewonnen");
                                }
                            }
                        } else if (b < 1) {
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
                                    teams.get(ops).getBlock().setType(Material.AIR);
                                    teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    reset();
                                    teams.clear();
                                    ops.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4Team Rot hat gewonnen");
                                }
                            }
                        }

                        teams.get(player).getBlock().setType(Material.AIR);
                        teams.get(player).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                        teams.remove(player);
                        scoreBoard.resetScores(player);

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
                                pla.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4" + TEAM_RED_NAME + " " + player.getDisplayName() + "§4 ist ausgeschieden");
                            } else {
                                pla.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4" + player.getCustomName() + " " + player.getDisplayName() + "§4 starb");
                            }
                        }
                    }
                }
            }
            if (player.getCustomName().equals(TEAM_BLUE_NAME)) {
                if (block.getType() == Material.NETHER_BRICK) {

                    Score score = obj.getScore(player);
                    score.setScore(score.getScore() - 1);

                    if (score.getScore() == 0) {
                        player.setCustomName(CS_NAME);

                        a = rot.getSize();
                        b = blau.getSize();

                        // player.performCommand("casp leave");
                        if (a < 1) {
                            for (Player ops : Bukkit.getOnlinePlayers()) {
                                if (ops.getCustomName().equals(TEAM_BLUE_NAME)) {
                                    ops.teleport(lobby);
                                    ops.setCustomName(CS_NAME);
                                    ops.getInventory().clear();
                                    teams.get(ops).getBlock().setType(Material.AIR);
                                    reset();
                                    teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                                    ops.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4Team Rot hat gewonnen");
                                    consoleSendMessage("§5[Test]", "§4Rot!");
                                }
                            }
                        } else if (b < 1) {
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
                                    teams.get(ops).getBlock().setType(Material.AIR);
                                    teams.get(ops).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
                                    teams.clear();
                                    reset();
                                    ops.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §4Team Rot hat gewonnen");
                                    consoleSendMessage("§5[Test]", "§1Blau!");
                                }
                            }
                        }

                        teams.get(player).getBlock().setType(Material.AIR);
                        teams.get(player).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
                        teams.remove(player);
                        scoreBoard.resetScores(player);
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
                                pla.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §1" + TEAM_BLUE_NAME + " " + player.getDisplayName() + "§1 ist ausgeschieden");
                            } else {
                                pla.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + " §1" + player.getCustomName() + " " + player.getDisplayName() + "§1 starb");
                            }

                        }
                    }
                }
            }
        }
    }
}
