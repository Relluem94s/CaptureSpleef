package de.relluem94.capturespleef;

import static de.relluem94.capturespleef.Strings.*;
import de.relluem94.capturespleef.events.GameJoin;
import de.relluem94.capturespleef.events.PlayerQuit;
import de.relluem94.capturespleef.events.SignChange;
import de.relluem94.capturespleef.events.SignUse;
import de.relluem94.capturespleef.events.SnowBallDamage;
import de.relluem94.capturespleef.events.SnowBallThrow;
import org.bukkit.plugin.PluginManager;

public class CmdEv {

    de.relluem94.capturespleef.CaptureSpleef main;

    public CmdEv(de.relluem94.capturespleef.CaptureSpleef instance) {
        main = instance;

    }

    public void registerCommands() {
        main.cSM(PLUGIN_PREFIX, REGISTER_COMMANDS);
        main.getCommand("casp").setExecutor(new CMD(main));
        main.cSM(PLUGIN_PREFIX, REGISTER_COMMANDS_FINISHED);
    }

    public void registerEvents() {
        PluginManager pm = main.getServer().getPluginManager();
        main.cSM(PLUGIN_PREFIX, REGISTER_EVENTS);
        //TODO Remove mains from constructor
        pm.registerEvents(new de.relluem94.capturespleef.listener.BlockDamage(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.listener.PlayerCommandPreprocess(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.listener.PlayerMove(main), main);
        pm.registerEvents(new PlayerQuit(main), main);
        pm.registerEvents(new GameJoin(main), main);
        pm.registerEvents(new SignUse(), main);
        pm.registerEvents(new SignChange(main), main);
        pm.registerEvents(new SnowBallThrow(main), main);
        pm.registerEvents(new SnowBallDamage(main), main);

        main.cSM(PLUGIN_PREFIX, REGISTER_EVENTS_FINISHED);
    }

}
