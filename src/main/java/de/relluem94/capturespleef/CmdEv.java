package de.relluem94.capturespleef;

import static de.relluem94.capturespleef.Strings.*;
import de.relluem94.capturespleef.events.GameJoin;
import de.relluem94.capturespleef.events.PlayerQuit;
import de.relluem94.capturespleef.events.SignChange;
import de.relluem94.capturespleef.events.SignUse;
import de.relluem94.capturespleef.events.SnowBallDamage;
import de.relluem94.capturespleef.events.SnowBallThrow;
import de.relluem94.capturespleef.listener.CSBlockDamage;
import de.relluem94.capturespleef.listener.CSPlayerCommandPreprocess;
import de.relluem94.capturespleef.listener.CSPlayerMove;
import static de.relluem94.minecraft.server.spigot.essentials.helpers.ChatHelper.consoleSendMessage;
import java.util.Objects;
import org.bukkit.plugin.PluginManager;

public class CmdEv {

    de.relluem94.capturespleef.CaptureSpleef main;

    public CmdEv(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;
    }

    public void registerCommands() {
        consoleSendMessage(PLUGIN_PREFIX, REGISTER_COMMANDS);
        Objects.requireNonNull(main.getCommand(COMMAND_CASP)).setExecutor(new CMD());
        consoleSendMessage(PLUGIN_PREFIX, REGISTER_COMMANDS_FINISHED);
    }

    public void registerEvents() {
        PluginManager pm = main.getServer().getPluginManager();
        consoleSendMessage(PLUGIN_PREFIX, REGISTER_EVENTS);
        
        pm.registerEvents(new CSBlockDamage(), main);
        pm.registerEvents(new CSPlayerCommandPreprocess(), main);
        pm.registerEvents(new CSPlayerMove(), main);
        pm.registerEvents(new PlayerQuit(), main);
        pm.registerEvents(new GameJoin(), main);
        pm.registerEvents(new SignUse(), main);
        pm.registerEvents(new SignChange(), main);
        pm.registerEvents(new SnowBallThrow(), main);
        pm.registerEvents(new SnowBallDamage(), main);

        consoleSendMessage(PLUGIN_PREFIX, REGISTER_EVENTS_FINISHED);
    }

}
