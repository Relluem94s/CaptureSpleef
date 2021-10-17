package de.relluem94.capturespleef.events;

import de.relluem94.rellulib.utils.LogUtils;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author rellu
 */
public class SignUse implements Listener {

    @EventHandler
    public void useSign(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType().equals(Material.OAK_WALL_SIGN)
                    || e.getClickedBlock().getType().equals(Material.OAK_SIGN)
                    || e.getPlayer().hasPermission("rellu.sign.item.use")) {
                try {
                    BlockState schild = e.getClickedBlock().getState();

                    final Player p = e.getPlayer();
                   
                    if (p.hasPermission("rellu.capturespleef.sign.use")) {

                        if (((schild instanceof Sign)) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                            Sign s = (Sign) e.getClickedBlock().getState();
                            if ((s.getLine(0).equalsIgnoreCase("§d[CaptureSpleef]")) && (s.getLine(2).equalsIgnoreCase("§aSpiel verlassen"))) {
                                p.performCommand("casp leave");
                            } else if ((s.getLine(0).equalsIgnoreCase("§d[CaptureSpleef]")) && (s.getLine(2).equalsIgnoreCase("§aSpiel betreten"))) {
                                p.performCommand("casp join");
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException ex) {
                    LogUtils.error(ex.getMessage());
                }
            }
        }
    }
}
