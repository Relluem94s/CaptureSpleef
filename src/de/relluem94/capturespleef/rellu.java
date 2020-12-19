package de.relluem94.capturespleef;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.relluem94.capturespleef.CmdEv;

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

public class rellu extends JavaPlugin implements Listener {

    public String prefix = "§d[RelluMG]";
    public String lobby = "lobby";
    public String csname = "CaptureSpleef";
    public String noUser = "§aDu bist kein Spieler";
    public String noPerm = "§aDu hast darauf keine Rechte";

    public String schild = " §aSchild erstellt";
    public String hubitem5 = "§fSchneeball";
    public String hubitem5_info_1 = "Dieser Schneeball dient dir als Hilfsmittel";
    public String hubitem5_info_2 = "";
    public String hubitem5_info_3 = "";
    public String hubitem5_info_4 = "";

    public String trenn = "\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0\u25B6\u25C0";

    public ScoreboardManager scoreboard;
    public Scoreboard sboard;
    public Scoreboard emptysboard;
    public Team rot;
    public Team blau;
    public Team cslobby;
    public Objective obj;
    public Score score;

    public int a;
    public int b;
    public int teamsize;
    // Die Team Größe default = 8 
    public int ts = 2;
    public int lives = 4;

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

        obj = sboard.registerNewObjective("CaptureSpleef", "Spieler");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§aSpieler");

        cslobby.setPrefix("§d");
        cslobby.setAllowFriendlyFire(true);
        rot.setPrefix("§4");
        rot.setAllowFriendlyFire(false);
        blau.setPrefix("§1");
        blau.setAllowFriendlyFire(false);

        cSM(prefix, "§awurde in " + (Calendar.getInstance().getTimeInMillis() - start) + "ms " + "gestartet!");
    }

    @Override
    public void onDisable() {
        cSM(prefix, "§awurde gestoppt!");
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
    //	if (event.getEntity().getCustomName().equals("TeamRot") || event.getEntity().getCustomName().equals("TeamBlau")) {  }
    //
}
