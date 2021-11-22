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

import de.relluem94.minecraft.server.spigot.essentials.permissions.Groups;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Permission;

import static de.relluem94.capturespleef.CaptureSpleef.setColors;
import static de.relluem94.capturespleef.CaptureSpleef.shuffle;
import static de.relluem94.capturespleef.Strings.SNOWBALL_DISPLAYNAME;
import static de.relluem94.capturespleef.Strings.SNOWBALL_META_INFO;

/**
 *
 * @author rellu
 */
public class SnowBallThrow implements Listener {

    @EventHandler
    public void Schneeball(PlayerInteractEvent e) {
        ItemStack snowballs = new ItemStack(Material.SNOWBALL, 94);
        ItemMeta snowball_meta = snowballs.getItemMeta();
        snowball_meta.setDisplayName(SNOWBALL_DISPLAYNAME);
        snowball_meta.setLore(Arrays.asList(SNOWBALL_META_INFO));
        snowballs.setItemMeta(snowball_meta);

        Player p = e.getPlayer();
        if (Permission.isAuthorized(p, Groups.getGroup("user").getId())) {
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
                        if (Permission.isAuthorized(p, Groups.getGroup("user").getId())) {
                            if (p.getName().equals("Relluem94")) {
                                shuffle();
                                ball.setCustomName(setColors().get(2) + "R" + setColors().get(1) + "e" + setColors().get(3) + "l" + setColors().get(4) + "l" + setColors().get(6) + "u");
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
