package de.relluem94.capturespleef;

import org.bukkit.plugin.PluginManager;

public class CmdEv {

    de.relluem94.capturespleef.rellu main;

    public CmdEv(de.relluem94.capturespleef.rellu instance) {
        main = instance;

    }

    public void registerCommands() {
        main.cSM(main.prefix, "§aBefehle werden regestriert!");
        main.getCommand("casp").setExecutor(new de.relluem94.capturespleef.CMD(main));
        main.cSM(main.prefix, "§aBefehle erfolgreich regestriert!");
    }

    public void registerEvents() {
        PluginManager pm = main.getServer().getPluginManager();
        main.cSM(main.prefix, "§aRegistriere Event!");
        //TODO Remove mains from constructor
        pm.registerEvents(new de.relluem94.capturespleef.listener.BlockDamage(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.listener.EntityDamageByEntity(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.listener.PlayerCommandPreprocess(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.listener.PlayerMove(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.events.PlayerQuit(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.events.SignChange(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.events.GameJoin(main), main);
        pm.registerEvents(new de.relluem94.capturespleef.events.SignUse(), main);
        pm.registerEvents(new de.relluem94.capturespleef.events.SnowBall(main), main);
        main.cSM(main.prefix, "§aEvents erfolgreich regestriert!");
    }

}
