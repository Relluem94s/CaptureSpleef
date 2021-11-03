package de.relluem94.capturespleef.events;

import static de.relluem94.capturespleef.Strings.PLUGIN_NAME_CONSOLE;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.SIGN_CREATE;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_SPACER;

public class SignChange implements Listener {

    @EventHandler
    public void SignCreate(SignChangeEvent e) {

        if ((e.getPlayer().hasPermission("rellu.capturespleef.sign.create"))
                && ((e.getLine(0) + "").equalsIgnoreCase(""))
                && ((e.getLine(3) + "").equalsIgnoreCase("[cs01]"))
                && ((e.getLine(2) + "").equalsIgnoreCase(""))
                && ((e.getLine(1) + "").equalsIgnoreCase(""))) {
            e.setLine(0, "§d[CaptureSpleef]");
            e.setLine(1, "");
            e.setLine(2, "§aSpiel betreten");
            e.setLine(3, "");
            e.getPlayer().sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + SIGN_CREATE);
        } else if ((e.getPlayer().hasPermission("rellu.capturespleef.sign.create"))
                && ((e.getLine(0) + "").equalsIgnoreCase(""))
                && ((e.getLine(3) + "").equalsIgnoreCase("[cs02]"))
                && ((e.getLine(2) + "").equalsIgnoreCase(""))
                && ((e.getLine(1) + "").equalsIgnoreCase(""))) {
            e.setLine(0, PLUGIN_NAME_CONSOLE);
            e.setLine(1, "");
            e.setLine(2, "§aSpiel verlassen");
            e.setLine(3, "");
            e.getPlayer().sendMessage(PLUGIN_PREFIX + PLUGIN_SPACER + SIGN_CREATE);
        }
    }

}
