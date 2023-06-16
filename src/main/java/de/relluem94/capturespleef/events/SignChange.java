package de.relluem94.capturespleef.events;

import static de.relluem94.capturespleef.CaptureSpleef.joinCommand;
import static de.relluem94.capturespleef.CaptureSpleef.leaveCommand;
import static de.relluem94.capturespleef.Strings.JOIN_GAME;
import static de.relluem94.capturespleef.Strings.LEFT_GAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.SIGN_CREATE;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_FORMS_SPACER_MESSAGE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import de.relluem94.minecraft.server.spigot.essentials.helpers.SignHelper;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Groups;
import de.relluem94.minecraft.server.spigot.essentials.permissions.Permission;

public class SignChange implements Listener {

    @EventHandler
    public void SignCreate(SignChangeEvent e) {
        if (Permission.isAuthorized(e.getPlayer(), Groups.getGroup("admin").getId())) {
            if (SignHelper.isSign(joinCommand, JOIN_GAME)) {
                e.setLine(0, joinCommand.getLine0());
                e.setLine(1, joinCommand.getLine1());

                e.setLine(3, joinCommand.getLine3());
                e.getPlayer().sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + SIGN_CREATE);
            } else if (SignHelper.isSign(leaveCommand, LEFT_GAME)) {
                e.setLine(0, leaveCommand.getLine0());
                e.setLine(1, leaveCommand.getLine1());

                e.setLine(3, leaveCommand.getLine3());
                e.getPlayer().sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + SIGN_CREATE);
            }
        }
    }
}
