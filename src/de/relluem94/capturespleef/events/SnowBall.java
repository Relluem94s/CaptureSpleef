
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
import static org.graalvm.compiler.hotspot.NodeCostDumpUtil.main;

/**
 *
 * @author rellu
 */
public class SnowBall implements Listener {
    
    de.relluem94.capturespleef.rellu main;

    public SnowBall(de.relluem94.capturespleef.rellu instance) {
        main = instance;
    }
    
    @EventHandler
    public void Schneeball(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("rellu.lobby.snowball")) {
            ItemStack test = p.getInventory().getItemInHand();
            if (test.getType() == Material.SNOWBALL) {
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {
                    ItemStack d1 = new ItemStack(Material.SNOWBALL, 94);
                    ItemMeta d2 = d1.getItemMeta();
                    d2.setDisplayName(main.hubitem5);
                    d2.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
                    d1.setItemMeta(d2);
                    p.getInventory().setItem(2, new ItemStack(d1));
                    ItemMeta test2 = test.getItemMeta();
                    String test3 = test2.getDisplayName();
                    if (test3 == null) {
                        return;
                    }
                    if (test3.contains("Schneeball")) {
                        Vector test231 = p.getEyeLocation().multiply(7.8).getDirection();
                        Vector vec = new Vector(test231.getX(), test231.getY(), test231.getZ());
                        Projectile ball = p.launchProjectile(Snowball.class);
                        ball.setVelocity(vec);
                        if (p.hasPermission("rellu.group.vip") || p.hasPermission("rellu.group.builder") || p.hasPermission("rellu.group.sgbuilder") || p.hasPermission("rellu.group.pMod") || p.hasPermission("rellu.group.Mod") || p.hasPermission("rellu.group.sMod") || p.hasPermission("rellu.group.Geek") || p.hasPermission("rellu.group.Admin")) {
                            if (p.getName().equals("Relluem94")) {
                                main.shuffle();
                                ball.setCustomName(main.setColors().get(2) + "R" + main.setColors().get(1) + "e" + main.setColors().get(3) + "l" + main.setColors().get(4) + "l" + main.setColors().get(6) + "u");
                                ball.setCustomNameVisible(true);
                                e.setCancelled(true);
                                ball.setFireTicks(1200);
                                ball.setFallDistance(2000);
                            } else if (p.getName().equals("Stellachen")) {
                                main.shuffle();
                                ball.setCustomName(main.setColors().get(2) + "S" + main.setColors().get(1) + "t" + main.setColors().get(3) + "e" + main.setColors().get(4) + "l" + main.setColors().get(6) + "l" + main.setColors().get(7) + "a");
                                ball.setCustomNameVisible(true);
                                e.setCancelled(true);
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
                    ItemStack d1 = new ItemStack(Material.SNOWBALL, 94);
                    ItemMeta d2 = d1.getItemMeta();
                    d2.setDisplayName(main.hubitem5);
                    d2.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
                    d1.setItemMeta(d2);
                    p.getInventory().setItem(2, new ItemStack(d1));
                }
            } else {
            }

        } else {
        }
    }   
}
