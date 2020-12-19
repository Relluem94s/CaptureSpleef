package de.relluem94.capturespleef.events;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

/**
 *
 * @author rellu
 */
public class SnowBallThrow implements Listener {

    de.relluem94.capturespleef.CaptureSpleef main;

    public SnowBallThrow(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    @EventHandler
    public void Schneeball(PlayerInteractEvent e) {
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 94);
        ItemMeta snowball_meta = snowballs.getItemMeta();
        snowball_meta.setDisplayName(main.hubitem5);
        snowball_meta.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
        snowballs.setItemMeta(snowball_meta);

        Player p = e.getPlayer();
        if (p.hasPermission("rellu.lobby.snowball")) {
            ItemStack itemInHand = p.getInventory().getItemInHand();
            if (itemInHand.getType() == Material.SNOWBALL) {
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {

                    p.getInventory().setItem(2, new ItemStack(snowballs));
                    ItemMeta itemInHandMeta = itemInHand.getItemMeta();
                    String itemInHandMetaDisplayname = itemInHandMeta.getDisplayName();
                    if (itemInHandMetaDisplayname == null) {
                        return;
                    }
                    if (itemInHandMetaDisplayname.contains("Schneeball")) {
                        Vector vec_direction = p.getEyeLocation().multiply(7.8).getDirection();
                        Vector vec = new Vector(vec_direction.getX(), vec_direction.getY(), vec_direction.getZ());
                        Projectile ball = p.launchProjectile(Snowball.class);
                        ball.setVelocity(vec);
                        if (p.hasPermission("rellu.group.vip")
                                || p.hasPermission("rellu.group.builder")
                                || p.hasPermission("rellu.group.sgbuilder")
                                || p.hasPermission("rellu.group.pMod")
                                || p.hasPermission("rellu.group.Mod")
                                || p.hasPermission("rellu.group.sMod")
                                || p.hasPermission("rellu.group.Geek")
                                || p.hasPermission("rellu.group.Admin")) {
                            if (p.getName().equals("Relluem94")) {
                                main.shuffle();
                                ball.setCustomName(main.setColors().get(2) + "R" + main.setColors().get(1) + "e" + main.setColors().get(3) + "l" + main.setColors().get(4) + "l" + main.setColors().get(6) + "u");
                                ball.setCustomNameVisible(true);
                                e.setCancelled(true);
                                ball.setFireTicks(1200);
                                ball.setFallDistance(2000);
                            } else {
                                ball.setCustomName(p.getDisplayName());
                                ball.setCustomNameVisible(true);
                                e.setCancelled(true);
                            }
                        } else {
                            e.setCancelled(true);
                        }

                    }
                } else {
                    p.getInventory().setItem(2, new ItemStack(snowballs));
                }
            }
        }
    }
}
