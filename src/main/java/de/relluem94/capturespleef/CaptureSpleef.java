package de.relluem94.capturespleef;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Lists;
import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_NAME_CONSOLE;
import static de.relluem94.capturespleef.Strings.PLUGIN_SECONDARY_COLOR;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_BORDER;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_STARTTIME;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_START_MESSAGE;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_STOP_MESSAGE;
import de.relluem94.minecraft.server.spigot.essentials.exceptions.WorldNotLoadedException;
import static de.relluem94.minecraft.server.spigot.essentials.helpers.ChatHelper.consoleSendMessage;
import de.relluem94.minecraft.server.spigot.essentials.helpers.WorldHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;

public class CaptureSpleef extends JavaPlugin {

    public static ScoreboardManager scoreboard;
    public static Scoreboard sboard;
    public static Scoreboard emptysboard;
    public static Team rot, blau, cslobby;
    public static Objective obj;
    public static Score score;

    public static int a, b, teamsize, ts = 2, lives = 4; // Die Team Größe default = 8 

    public static ArrayList<Player> cooldown = new ArrayList<>();
    public static HashMap<Player, Location> teams = new HashMap<>();

    public static Map<Location, Material> old = new HashMap<>();

    public static void reset() {
        old.keySet().forEach(loc -> {
            Material t = old.get(loc);
            loc.getBlock().setType(t);
        });

        teams.clear();

    }

    public Server server = getServer();

    public Server getserver() {
        return server;
    }

    private static final List<ChatColor> colors = Lists.newArrayList(new ChatColor[]{ChatColor.WHITE, ChatColor.YELLOW, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.AQUA, ChatColor.GREEN, ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.DARK_AQUA, ChatColor.DARK_GREEN, ChatColor.DARK_BLUE});

    public static List<ChatColor> setColors() {
        return colors;
    }

    public static void shuffle() {
        Collections.shuffle(setColors(), new Random());
    }

    @Override
    public void onEnable() {
        WorldHelper.loadWorld(ACTIVE_WORLD);     
        long start = Calendar.getInstance().getTimeInMillis();
        consoleSendMessage(PLUGIN_SECONDARY_COLOR, PLUGIN_BORDER);
        consoleSendMessage(PLUGIN_NAME_CONSOLE, "");
        consoleSendMessage(PLUGIN_NAME_CONSOLE, "");
        consoleSendMessage(PLUGIN_NAME_CONSOLE, PLUGIN_SECONDARY_COLOR + PLUGIN_START_MESSAGE);
        consoleSendMessage(PLUGIN_NAME_CONSOLE, "");
        
        CmdEv rells = new CmdEv(this);
        rells.registerEvents();
        rells.registerCommands();

        scoreboard = Bukkit.getScoreboardManager();
        sboard = scoreboard.getNewScoreboard();
        emptysboard = scoreboard.getNewScoreboard();
        rot = sboard.registerNewTeam("rot");
        blau = sboard.registerNewTeam("blau");
        cslobby = sboard.registerNewTeam("cslobby");

        obj = sboard.registerNewObjective(CS_NAME, "Spieler");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(PLUGIN_SECONDARY_COLOR + "Spieler");

        cslobby.setPrefix("§d");
        cslobby.setAllowFriendlyFire(true);
        rot.setPrefix("§4");
        rot.setAllowFriendlyFire(false);
        blau.setPrefix("§1");
        blau.setAllowFriendlyFire(false);
        
        try {
            checkWorld();
        } catch (WorldNotLoadedException ex) {
            Logger.getLogger(CaptureSpleef.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        consoleSendMessage(PLUGIN_NAME_CONSOLE, "");
        consoleSendMessage(PLUGIN_NAME_CONSOLE, PLUGIN_SECONDARY_COLOR + String.format(PLUGIN_STARTTIME, Calendar.getInstance().getTimeInMillis() - start));
        consoleSendMessage(PLUGIN_NAME_CONSOLE, "");
        consoleSendMessage(PLUGIN_SECONDARY_COLOR + PLUGIN_BORDER, "");
    }

    @Override
    public void onDisable() {
        try {
            WorldHelper.unloadWorld(ACTIVE_WORLD, true);
        } catch (WorldNotLoadedException ex) {
            Logger.getLogger(CaptureSpleef.class.getName()).log(Level.SEVERE, null, ex);
        }
        consoleSendMessage(PLUGIN_NAME_CONSOLE, PLUGIN_SECONDARY_COLOR + PLUGIN_STOP_MESSAGE);
    }
    
    private static void checkWorld() throws WorldNotLoadedException {
        World world = Bukkit.getWorld(ACTIVE_WORLD);
        if(world != null){
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_FIRE_TICK, false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_INSOMNIA, false);
            world.setGameRule(GameRule.DROWNING_DAMAGE, false);
            world.setGameRule(GameRule.FALL_DAMAGE, false);
            world.setGameRule(GameRule.KEEP_INVENTORY, false);
            world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.DISABLE_RAIDS, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setDifficulty(Difficulty.PEACEFUL);
        }
        else{
            throw new WorldNotLoadedException(ACTIVE_WORLD + " was not loaded, try to reload or check if world exists.");
        }
    }

    //
    // Team Rot Team Blau
    //	Team Rot läuft auf Netherrack Team Blau auf Prismarin
    //	Auf feindlichem Gebiet stirbt der Spieler
    //	Ziel ist an einen bestimmten Punkt im Gegenerischen Feld zu kommen
    //	Hier für gibt es Sparten (und FeldHacke)
    //	Um Gegenrische Gebiete zu erobern
    //	Das Spiel ist rum wenn kein Spieler im Gegnerteam übrig ist oder der "Schatz" erbeutet wurde
    //  Maximale Spiel zeit sind 15 Minuten Danach wird resetet
    //
    //  onClickClack3 EntityDamageByEntityEvent hat noch einen Zusatz. Schneebälle mit Effekten
    //
    //	if (event.getEntity().getCustomName().equals(team_red) || event.getEntity().getCustomName().equals(team_blue)) {  }
    //
}
