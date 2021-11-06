package de.relluem94.capturespleef.events;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.relluem94.rellulib.utils.LogUtils;

import de.relluem94.minecraft.server.spigot.essentials.permissions.Groups;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Permission;

import static de.relluem94.capturespleef.Strings.COMMAND_CASP;
import static de.relluem94.capturespleef.Strings.COMMAND_CASP_JOIN;
import static de.relluem94.capturespleef.Strings.COMMAND_CASP_LEAVE;
import static de.relluem94.capturespleef.Strings.JOIN_GAME;
import static de.relluem94.capturespleef.Strings.LEFT_GAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_NAME_CONSOLE;

/**
 *
 * @author rellu
 */
public class SignUse implements Listener {

    @EventHandler
    public void useSign(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block block = Objects.requireNonNull(e.getClickedBlock());
            Material material = block.getType();
            if (material.equals(Material.OAK_WALL_SIGN) || material.equals(Material.OAK_SIGN) || Permission.isAuthorized(p, Groups.getGroup("user").getId())) {
                try {
                    BlockState bs = block.getState();                   
                     if (((bs instanceof Sign)) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                            Sign s = (Sign) bs;
                            if ((s.getLine(0).equalsIgnoreCase(PLUGIN_NAME_CONSOLE)) && (s.getLine(2).equalsIgnoreCase(LEFT_GAME))) {
                                p.performCommand(COMMAND_CASP + " " + COMMAND_CASP_LEAVE);
                            } else if ((s.getLine(0).equalsIgnoreCase(PLUGIN_NAME_CONSOLE)) && (s.getLine(2).equalsIgnoreCase(JOIN_GAME))) {
                                p.performCommand(COMMAND_CASP + " " + COMMAND_CASP_JOIN);
                            }
                        }
                } catch (IndexOutOfBoundsException ex) {
                    LogUtils.error(ex.getMessage());
                }
            }
        }
    }
}