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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Lists;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;

public class CaptureSpleef extends JavaPlugin implements Listener {

    public ScoreboardManager scoreboard;
    public Scoreboard sboard;
    public Scoreboard emptysboard;
    public Team rot, blau, cslobby;
    public Objective obj;
    public Score score;

    public int a, b, teamsize, ts = 2, lives = 4; // Die Team Größe default = 8 

    public ArrayList<Player> cooldown = new ArrayList<Player>();
    public HashMap<Player, Location> teams = new HashMap<Player, Location>();

    public Map<Location, Material> old = new HashMap<>();

    public void reset() {

        for (Location loc : old.keySet()) {
            Material t = old.get(loc);
            loc.getBlock().setType(t);
        }

        teams.clear();

    }

    public void cSM(String type, String message) {
        ConsoleCommandSender console = this.getServer().getConsoleSender();

        console.sendMessage(type + " " + message);
    }

    public Server server = getServer();

    public Server getserver() {
        return server;
    }

    private Random random = new Random();

    public Random getRandom() {
        return random;
    }

    private List<ChatColor> colors = Lists.newArrayList(new ChatColor[]{ChatColor.WHITE, ChatColor.YELLOW, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.AQUA, ChatColor.GREEN, ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.DARK_AQUA, ChatColor.DARK_GREEN, ChatColor.DARK_BLUE});

    public List<ChatColor> setColors() {
        return colors;
    }

    public void shuffle() {
        Collections.shuffle(setColors(), getRandom());
    }

    @Override
    public void onEnable() {
        long start = Calendar.getInstance().getTimeInMillis();
        CmdEv rells = new CmdEv(this);
        rells.registerEvents();
        rells.registerCommands();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        // Plugin Zeugs
        Bukkit.getPluginManager().registerEvents(this, this);

        scoreboard = Bukkit.getScoreboardManager();
        sboard = scoreboard.getNewScoreboard();
        emptysboard = scoreboard.getNewScoreboard();
        rot = sboard.registerNewTeam("rot");
        blau = sboard.registerNewTeam("blau");
        cslobby = sboard.registerNewTeam("cslobby");

        obj = sboard.registerNewObjective(CS_NAME, "Spieler");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§aSpieler");

        cslobby.setPrefix("§d");
        cslobby.setAllowFriendlyFire(true);
        rot.setPrefix("§4");
        rot.setAllowFriendlyFire(false);
        blau.setPrefix("§1");
        blau.setAllowFriendlyFire(false);

        cSM(PLUGIN_PREFIX, "§awurde in " + (Calendar.getInstance().getTimeInMillis() - start) + "ms " + "gestartet!");
    }

    @Override
    public void onDisable() {
        cSM(PLUGIN_PREFIX, "§awurde gestoppt!");
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
