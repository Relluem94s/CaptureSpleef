package de.relluem94.capturespleef.events;

import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Score;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;

public class GameJoin implements Listener {

    de.relluem94.capturespleef.CaptureSpleef main;

    public GameJoin(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    @EventHandler
    public void GameJoinAndLeave(PlayerInteractEvent evo) {
        Player player = evo.getPlayer();

        main.a = main.rot.getSize();
        main.b = main.blau.getSize();
        main.teamsize = main.a + main.b;

        Location PosRot = new Location(main.server.getWorld(ACTIVE_WORLD), -141, 138, 272);
        Location PosBlau = new Location(main.server.getWorld(ACTIVE_WORLD), -124, 138, 272);

        ItemStack d3 = new ItemStack(Material.SUGAR, 1);
        ItemMeta d4 = d3.getItemMeta();
        d4.setDisplayName("§8Wähle dein Team");
        d4.setLore(Arrays.asList("§8Rechtklicke auf den §cNetherBrick §8oder den §9Prismarin"));
        d3.setItemMeta(d4);

        // Verlassen Team Blau
        if (evo.getPlayer().getWorld().getName().equals("lobby")) {
            if (evo.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SHOVEL) {
                if (!(evo.getAction() == Action.LEFT_CLICK_BLOCK)) {
                    return;
                }
                if (evo.getClickedBlock().getType().equals(Material.LAPIS_BLOCK) && evo.getPlayer().getCustomName().equals(TEAM_BLUE_NAME)) {
                    double x1 = evo.getClickedBlock().getX();
                    double y1 = evo.getClickedBlock().getY();
                    double z1 = evo.getClickedBlock().getZ();
                    //
                    double y2 = y1 + 1;
                    //
                    Location loc = new Location(player.getWorld(), x1, y2, z1);

                    if (loc.getBlock().getType().equals(Material.SKELETON_SKULL)) {
                        Skull s = (Skull) loc.getBlock().getState();
                        String s1 = s.getOwner();
                        String s2 = s1.toLowerCase();
                        if (player.getName().toLowerCase().equals(s2)) {
                            loc.getBlock().setType(Material.AIR);
                            evo.getClickedBlock().setType(Material.PRISMARINE);

                        } else {
                            player.sendMessage("§5[RelluAPI] §6Dieser Slot ist vergeben");
                        }
                    }
                    player.setCustomName(CS_NAME);
                    main.teams.remove(player);
                    main.blau.removePlayer(player);
                    main.cslobby.addPlayer(player);
                    player.getInventory().setItemInHand(new ItemStack(d3));
                    //
                    //
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            pla.sendMessage(player.getDisplayName() + "§1 hat TeamBlau verlassen");
                        }
                    }
                }
            } // Verlassen Team Rot
            else if (evo.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_HOE) {
                if (!(evo.getAction() == Action.LEFT_CLICK_BLOCK)) {
                    return;
                }
                if (evo.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK) && evo.getPlayer().getCustomName().equals(TEAM_RED_NAME)) {
                    double x1 = evo.getClickedBlock().getX();
                    double y1 = evo.getClickedBlock().getY();
                    double z1 = evo.getClickedBlock().getZ();
                    //
                    double y2 = y1 + 1;
                    //
                    Location loc = new Location(player.getWorld(), x1, y2, z1);
                    if (loc.getBlock().getType().equals(Material.SKELETON_SKULL)) {
                        Skull s = (Skull) loc.getBlock().getState();
                        String s1 = s.getOwner();
                        String s2 = s1.toLowerCase();
                        if (player.getName().toLowerCase().equals(s2)) {
                            loc.getBlock().setType(Material.AIR);
                            evo.getClickedBlock().setType(Material.NETHER_BRICK);
                        } else {
                            player.sendMessage("§5[RelluAPI] §6Dieser Slot ist vergeben");
                        }
                    }
                    player.setCustomName(CS_NAME);
                    main.teams.remove(player);
                    main.rot.removePlayer(player);
                    main.cslobby.addPlayer(player);
                    player.getInventory().setItemInHand(new ItemStack(d3));
                    //
                    //
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            pla.sendMessage(player.getDisplayName() + "§4 hat TeamRot verlassen");
                        }
                    }
                }
            } // Betreten Team Rot
            else if (evo.getPlayer().getInventory().getItemInHand().getType() == Material.SUGAR && evo.getPlayer().getCustomName().equals(CS_NAME)) {
                if (!(evo.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                    return;
                }
                if (evo.getClickedBlock().getType().equals(Material.NETHER_BRICK) & !evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.NETHER_BRICK) & !evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
                    evo.getClickedBlock().setType(Material.REDSTONE_BLOCK);
                    double x1 = evo.getClickedBlock().getX();
                    double y1 = evo.getClickedBlock().getY();
                    double z1 = evo.getClickedBlock().getZ();
                    //
                    double y2 = y1 + 1;
                    //
                    Location loc = new Location(player.getWorld(), x1, y2, z1);
                    loc.getBlock().setType(Material.SKELETON_SKULL);
                    Skull s = (Skull) loc.getBlock().getState();
                    s.setSkullType(SkullType.PLAYER);
                    s.setOwner(player.getName());
                    s.update(true);
                    ItemStack id3 = new ItemStack(Material.DIAMOND_HOE, 1);
                    ItemMeta id4 = id3.getItemMeta();
                    id4.setDisplayName("§4TeamRot");
                    id4.setLore(Arrays.asList("§8Mache hiermit §1Blaue Steine §8zu §4Roten"));
                    id3.setItemMeta(id4);
                    player.getInventory().setItemInHand(new ItemStack(id3));

                    player.setCustomName(TEAM_RED_NAME);
                    //
                    //
                    //
                    main.rot.addPlayer(player);
                    main.teams.put(player, loc);

                    player.setScoreboard(main.sboard);

                    @SuppressWarnings("deprecation")
                    Score score = main.obj.getScore(player);
                    score.setScore(main.lives);
                    //
                    //		Game Start
                    //

                    main.a = main.rot.getSize();
                    main.b = main.blau.getSize();
                    main.teamsize = main.a + main.b;

                    if (main.teamsize == main.ts || main.a == main.b) {
                        for (Player pla : Bukkit.getOnlinePlayers()) {
                            if (pla.getCustomName().equals(TEAM_RED_NAME)) {
                                pla.teleport(PosRot);
                            } else if (pla.getCustomName().equals(TEAM_BLUE_NAME)) {
                                pla.teleport(PosBlau);
                            }
                        }
                    }
                    //
                    //		Team Rot Beitrittsnachricht
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            pla.sendMessage(player.getDisplayName() + "§4 hat TeamRot beigetreten");
                        }
                    }
                } // Betreten Team Blau
                else if (evo.getClickedBlock().getType().equals(Material.PRISMARINE) & !evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.PRISMARINE) & !evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
                    evo.getClickedBlock().setType(Material.LAPIS_BLOCK);
                    double x1 = evo.getClickedBlock().getX();
                    double y1 = evo.getClickedBlock().getY();
                    double z1 = evo.getClickedBlock().getZ();
                    //
                    double y2 = y1 + 1;
                    //
                    Location loc = new Location(player.getWorld(), x1, y2, z1);

                    loc.getBlock().setType(Material.SKELETON_SKULL);
                    Skull s = (Skull) loc.getBlock().getState();
                    s.setSkullType(SkullType.PLAYER);
                    s.setOwner(player.getName());
                    s.update(true);
                    ItemStack id3 = new ItemStack(Material.DIAMOND_SHOVEL, 1);
                    ItemMeta id4 = id3.getItemMeta();
                    id4.setDisplayName("§1TeamBlau");
                    id4.setLore(Arrays.asList("§8Mache hiermit §4Roten Steine §8zu §1Blaue"));
                    id3.setItemMeta(id4);
                    player.getInventory().setItemInHand(new ItemStack(id3));

                    player.setCustomName(TEAM_BLUE_NAME);
                    //
                    //
                    //
                    main.blau.addPlayer(player);
                    main.teams.put(player, loc);

                    player.setScoreboard(main.sboard);

                    @SuppressWarnings("deprecation")
                    Score score = main.obj.getScore(player);
                    score.setScore(main.lives);
                    //
                    //		Game Start
                    //

                    main.a = main.rot.getSize();
                    main.b = main.blau.getSize();
                    main.teamsize = main.a + main.b;

                    if (main.teamsize == main.ts && main.a == main.b) {
                        for (Player pla : Bukkit.getOnlinePlayers()) {
                            if (pla.getCustomName().equals(TEAM_RED_NAME)) {
                                pla.teleport(PosRot);
                            } else if (pla.getCustomName().equals(TEAM_BLUE_NAME)) {
                                pla.teleport(PosBlau);
                            }
                        }
                    }
                    //
                    //		Team Blau Beitrittsnachricht
                    //
                    for (Player pla : Bukkit.getOnlinePlayers()) {
                        if (pla.getCustomName().equals(TEAM_RED_NAME) || pla.getCustomName().equals(TEAM_BLUE_NAME) || pla.getCustomName().equals(CS_NAME)) {
                            pla.sendMessage(player.getDisplayName() + "§1 hat TeamBlau beigetreten");
                        }
                    }
                }
            }
        }
    }
}
