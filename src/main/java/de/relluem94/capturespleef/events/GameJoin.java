package de.relluem94.capturespleef.events;

import static de.relluem94.capturespleef.CaptureSpleef.a;
import static de.relluem94.capturespleef.CaptureSpleef.b;
import static de.relluem94.capturespleef.CaptureSpleef.blau;
import static de.relluem94.capturespleef.CaptureSpleef.csLobby;
import static de.relluem94.capturespleef.CaptureSpleef.lives;
import static de.relluem94.capturespleef.CaptureSpleef.obj;
import static de.relluem94.capturespleef.CaptureSpleef.rot;
import static de.relluem94.capturespleef.CaptureSpleef.scoreBoard;
import static de.relluem94.capturespleef.CaptureSpleef.teams;
import static de.relluem94.capturespleef.CaptureSpleef.teamSize;
import static de.relluem94.capturespleef.CaptureSpleef.ts;
import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_FORMS_SPACER_MESSAGE;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Score;
import org.jetbrains.annotations.NotNull;

public class GameJoin implements Listener {

    @EventHandler
    public void GameJoinAndLeave(@NotNull PlayerInteractEvent e) {
        Player p = e.getPlayer();

        a = rot.getSize();
        b = blau.getSize();
        teamSize = a + b;

        Location PosRot = new Location(Bukkit.getWorld(ACTIVE_WORLD), -141, 138, 272);
        Location PosBlau = new Location(Bukkit.getWorld(ACTIVE_WORLD), -124, 138, 272);

        ItemStack is = new ItemStack(Material.SUGAR, 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§8Wähle dein Team");
        im.setLore(List.of("§8Rechtklicke auf den §cNetherBrick §8oder den §9Prismarin"));
        is.setItemMeta(im);

        // Verlassen Team Blau
        if (e.getPlayer().getWorld().getName().equals("lobby")) {
            if (e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SHOVEL) {
                if (!(e.getAction() == Action.LEFT_CLICK_BLOCK)) {
                    return;
                }
                if (e.getClickedBlock().getType().equals(Material.LAPIS_BLOCK) && e.getPlayer().getCustomName().equals(TEAM_BLUE_NAME)) {

                    //
                    Location loc = e.getClickedBlock().getLocation().add(0, 1, 0);

                    if (loc.getBlock().getType().equals(Material.SKELETON_SKULL)) {
                        Skull skull = (Skull) loc.getBlock().getState();
                        String owner = skull.getOwner().toLowerCase();
                        if (p.getName().toLowerCase().equals(owner)) {
                            loc.getBlock().setType(Material.AIR);
                            e.getClickedBlock().setType(Material.PRISMARINE);

                        } else {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + "§6Dieser Slot ist vergeben");
                        }
                    }
                    p.setCustomName(CS_NAME);
                    teams.remove(p);
                    blau.removePlayer(p);
                    csLobby.addPlayer(p);
                    p.getInventory().setItemInHand(new ItemStack(is));
                    //
                    //
                    //
                    Bukkit.getOnlinePlayers().stream().filter(pla -> (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME))).forEachOrdered(pla -> pla.sendMessage(p.getDisplayName() + "§1 hat TeamBlau verlassen"));
                }
            } // Verlassen Team Rot
            else if (e.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_HOE) {
                if (!(e.getAction() == Action.LEFT_CLICK_BLOCK)) {
                    return;
                }
                if (e.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK) && e.getPlayer().getCustomName().equals(TEAM_RED_NAME)) {
                    Block block = e.getClickedBlock().getRelative(0, 1, 0);
                    if (block.getType().equals(Material.SKELETON_SKULL)) {
                        Skull skull = (Skull) block.getState();
                        String owner = skull.getOwner().toLowerCase();
                        if (p.getName().toLowerCase().equals(owner)) {
                            block.setType(Material.AIR);
                            e.getClickedBlock().setType(Material.NETHER_BRICK);
                        } else {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + "§6Dieser Slot ist vergeben");
                        }
                    }
                    p.setCustomName(CS_NAME);
                    teams.remove(p);
                    rot.removePlayer(p);
                    csLobby.addPlayer(p);
                    p.getInventory().setItemInHand(new ItemStack(is));
                    //
                    //
                    //
                    Bukkit.getOnlinePlayers().stream().filter(pla -> (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME))).forEachOrdered(pla -> pla.sendMessage(p.getDisplayName() + "§4 hat TeamRot verlassen"));
                }
            } // Betreten Team Rot
            else if (e.getPlayer().getInventory().getItemInHand().getType() == Material.SUGAR && e.getPlayer().getCustomName().equals(CS_NAME)) {
                if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    return;
                }
                if (e.getClickedBlock().getType().equals(Material.NETHER_BRICK) & !e.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.NETHER_BRICK) & !e.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
                    e.getClickedBlock().setType(Material.REDSTONE_BLOCK);

                    Block block = e.getClickedBlock().getRelative(0, 1, 0);
                    block.setType(Material.SKELETON_SKULL);
                    Skull skull = (Skull) block.getState();
                    skull.setSkullType(SkullType.PLAYER);
                    skull.setOwner(p.getName());
                    skull.update(true);
                    ItemStack isTeamRed = new ItemStack(Material.DIAMOND_HOE, 1);
                    ItemMeta imTeamRed = isTeamRed.getItemMeta();
                    imTeamRed.setDisplayName("§4TeamRot");
                    imTeamRed.setLore(List.of("§8Mache hiermit §1Blaue Steine §8zu §4Roten"));
                    isTeamRed.setItemMeta(imTeamRed);
                    p.getInventory().setItemInHand(new ItemStack(isTeamRed));

                    p.setCustomName(TEAM_RED_NAME);
                    //
                    //
                    //
                    rot.addPlayer(p);
                    teams.put(p, block.getLocation());

                    p.setScoreboard(scoreBoard);

                    Score score = obj.getScore(p);
                    score.setScore(lives);
                    //
                    //		Game Start
                    //

                    a = rot.getSize();
                    b = blau.getSize();
                    teamSize = a + b;

                    if (teamSize == ts || a == b) {
                        Bukkit.getOnlinePlayers().forEach(pla -> {
                            if (pla.getCustomName().equals(TEAM_RED_NAME)) {
                                pla.teleport(PosRot);
                            } else if (pla.getCustomName().equals(TEAM_BLUE_NAME)) {
                                pla.teleport(PosBlau);
                            }
                        });
                    }
                    //
                    //		Team Rot Beitrittsnachricht
                    //
                    Bukkit.getOnlinePlayers().stream().filter(pla -> (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME))).forEachOrdered(pla -> pla.sendMessage(p.getDisplayName() + "§4 ist TeamRot beigetreten"));
                } // Betreten Team Blau
                else if (e.getClickedBlock().getType().equals(Material.PRISMARINE) & !e.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.PRISMARINE) & !e.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
                    e.getClickedBlock().setType(Material.LAPIS_BLOCK);

                    Block block = e.getClickedBlock().getRelative(0, 1, 0);

                    block.setType(Material.SKELETON_SKULL);
                    Skull skull = (Skull) block.getState();
                    skull.setSkullType(SkullType.PLAYER);
                    skull.setOwner(p.getName());
                    skull.update(true);
                    ItemStack isTeamBlue = new ItemStack(Material.DIAMOND_SHOVEL, 1);
                    ItemMeta imTeamBlue = isTeamBlue.getItemMeta();
                    imTeamBlue.setDisplayName("§1TeamBlau");
                    imTeamBlue.setLore(List.of("§8Mache hiermit §4Rote Steine §8zu §1Blauen"));
                    isTeamBlue.setItemMeta(imTeamBlue);
                    p.getInventory().setItemInHand(new ItemStack(isTeamBlue));

                    p.setCustomName(TEAM_BLUE_NAME);
                    //
                    //
                    //
                    blau.addPlayer(p);
                    teams.put(p, block.getLocation());

                    p.setScoreboard(scoreBoard);

                    Score score = obj.getScore(p);
                    score.setScore(lives);
                    //
                    //		Game Start
                    //

                    a = rot.getSize();
                    b = blau.getSize();
                    teamSize = a + b;

                    if (teamSize == ts && a == b) {
                        Bukkit.getOnlinePlayers().forEach(pla -> {
                            if (pla.getCustomName().equals(TEAM_RED_NAME)) {
                                pla.teleport(PosRot);
                            } else if (pla.getCustomName().equals(TEAM_BLUE_NAME)) {
                                pla.teleport(PosBlau);
                            }
                        });
                    }
                    //
                    //		Team Blau Beitrittsnachricht
                    //
                    Bukkit.getOnlinePlayers().stream().filter(pla -> (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME))).forEachOrdered(pla -> pla.sendMessage(p.getDisplayName() + "§1 ist TeamBlau beigetreten"));
                }
            }
        }
    }
}
