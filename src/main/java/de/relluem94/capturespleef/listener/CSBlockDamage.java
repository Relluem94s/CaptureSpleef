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

import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.CaptureSpleef.old;
import static de.relluem94.capturespleef.CaptureSpleef.reset;
import static de.relluem94.capturespleef.CaptureSpleef.sboard;
import static de.relluem94.capturespleef.CaptureSpleef.teams;
import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;

import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_SPACER;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Groups;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Permission;

public class CSBlockDamage implements Listener {

    private void teamWin(Player p, String winner) {
        p.teleport(lobby);
        p.getInventory().clear();
        p.setCustomName(CS_NAME);
        reset();
        teams.get(p).getBlock().setType(Material.AIR);
        sboard.resetScores(p);

        if (p.getCustomName().equals(TEAM_RED_NAME) && TEAM_RED_NAME.equals(winner)) {
            teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
            p.sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + "§1Team Blau hat gewonnen");
        } else if(p.getCustomName().equals(TEAM_BLUE_NAME) && TEAM_RED_NAME.equals(winner)) {
            teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
            p.sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + "§4Team Rot hat gewonnen");
        }
    }

    private void teamWins(Player p, String winner) {
        if (p.getCustomName().equals(TEAM_RED_NAME) && TEAM_RED_NAME.equals(winner)) {
            p.teleport(lobby);
            p.getInventory().clear();
            //
            //		Feuerwerk
            //
            double x1 = p.getEyeLocation().getX();
            double y1 = p.getEyeLocation().getY();
            double z1 = p.getEyeLocation().getZ();
            //
            double y2 = y1 + 1;
            Location loc = new Location(p.getWorld(), x1, y2, z1);
            // Firework
            Firework fw = (Firework) p.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            //random
            Random r22 = new Random();
            //sets type
            int rt = r22.nextInt(4) + 1;
            Type type;
            switch (rt) {
                case 1:
                    type = Type.BALL;
                    break;
                case 2:
                    type = Type.BALL_LARGE;
                    break;
                case 3:
                    type = Type.BURST;
                    break;
                case 4:
                    type = Type.STAR;
                    break;
                default:
                    type = Type.BALL;
                    break;
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
            p.setCustomName(CS_NAME);
            teams.get(p).getBlock().setType(Material.AIR);
            teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
            sboard.resetScores(p);
            reset();
            p.sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + "§4Team Rot hat gewonnen");
        } else if (p.getCustomName().equals(TEAM_BLUE_NAME) && TEAM_RED_NAME.equals(winner)) {
            p.teleport(lobby);
            //
            //		Feuerwerk
            //
            double x1 = p.getEyeLocation().getX();
            double y1 = p.getEyeLocation().getY();
            double z1 = p.getEyeLocation().getZ();
            //
            double y2 = y1 + 1;
            Location loc = new Location(p.getWorld(), x1, y2, z1);
            // Firework
            Firework fw = (Firework) p.getWorld().spawnEntity(loc, EntityType.FIREWORK);
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
            reset();
            p.setCustomName(CS_NAME);
            p.getInventory().clear();
            teams.get(p).getBlock().setType(Material.AIR);
            teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
            teams.clear();
            sboard.resetScores(p);
            p.sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + "§1Team Blau hat gewonnen");
        }
    }

    private static Location lobby = new Location(Bukkit.getWorld(ACTIVE_WORLD), -132, 144, 272);

    @EventHandler
    public void gameItSelf(BlockDamageEvent ev) {

        Player player = ev.getPlayer();

        if (Permission.isAuthorized(player, Groups.getGroup("user").getId()) && player.getLocation().equals(lobby)) {
            // Team Blau
            switch (player.getItemInHand().getType()) {
                case DIAMOND_SHOVEL: {
                    Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                    //
                    //
                    if (block.getType() == Material.PRISMARINE) {
                        player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_STEP, 1F, 0F);
                        //
                        //		Team Blau Gewonnen
                        //
                        if (player.getCustomName().equals(TEAM_BLUE_NAME)) {
                            switch (ev.getBlock().getType()) {
                                case GOLD_BLOCK:
                                    Bukkit.getOnlinePlayers().forEach(ops -> {
                                        teamWin(ops, TEAM_BLUE_NAME);
                                        teamWins(ops, TEAM_BLUE_NAME);
                                    });
                                    break;
                                case IRON_BLOCK:
                                    break;
                                case NETHER_BRICK:
                                    old.put(ev.getBlock().getLocation(), ev.getBlock().getType());
                                    ev.getBlock().setType(Material.PRISMARINE);
                                    break;
                                default:
                                    // Keine Anderen Blöcke können zerstört werden
                                    ev.setCancelled(true);
                                    break;
                            }
                        } else {
                            ev.setCancelled(true);
                        }
                    } else {
                        ev.setCancelled(true);
                    }
                    break;
                } // Team Rot
                case DIAMOND_HOE: {
                    Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                    //
                    //
                    if (block.getType().equals(Material.NETHER_BRICK)) {
                        player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 5);
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_STEP, 1F, 0F);
                        //
                        //		Team Rot Gewonnen
                        //
                        if (player.getCustomName().equals(TEAM_RED_NAME)) {
                            switch (ev.getBlock().getType()) {
                                case IRON_BLOCK:
                                    Bukkit.getOnlinePlayers().forEach(ops -> {
                                        teamWin(ops, TEAM_RED_NAME);
                                        teamWins(ops, TEAM_RED_NAME);
                                    });
                                    break;
                                case GOLD_BLOCK:
                                    break;
                                case PRISMARINE:
                                    old.put(ev.getBlock().getLocation(), ev.getBlock().getType());
                                    ev.getBlock().setType(Material.NETHER_BRICK);
                                    break;
                                default:
                                    // Keine Anderen Blöcke können zerstört werden
                                    ev.setCancelled(true);
                                    break;
                            }
                        } else {
                            ev.setCancelled(true);
                        }
                    } else {
                        ev.setCancelled(true);
                    }
                    break;
                }
                default:
                    // Komplett
                    ev.setCancelled(true);
                    break;
            }
        }
    }
}