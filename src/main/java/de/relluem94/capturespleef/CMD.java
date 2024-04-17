package de.relluem94.capturespleef;

import static de.relluem94.capturespleef.CaptureSpleef.a;
import static de.relluem94.capturespleef.CaptureSpleef.b;
import static de.relluem94.capturespleef.CaptureSpleef.blau;
import static de.relluem94.capturespleef.CaptureSpleef.cooldown;
import static de.relluem94.capturespleef.CaptureSpleef.csLobby;
import static de.relluem94.capturespleef.CaptureSpleef.emptyScoreBoard;
import static de.relluem94.capturespleef.CaptureSpleef.rot;
import static de.relluem94.capturespleef.CaptureSpleef.scoreBoard;
import static de.relluem94.capturespleef.CaptureSpleef.teamSize;
import static de.relluem94.capturespleef.CaptureSpleef.ts;
import static de.relluem94.capturespleef.Strings.ACTIVE_WORLD;
import static de.relluem94.capturespleef.Strings.CS_NAME;
import static de.relluem94.capturespleef.Strings.NO_PERM;
import static de.relluem94.capturespleef.Strings.NO_USER;
import static de.relluem94.capturespleef.Strings.PLUGIN_CHAT_SPACER;
import static de.relluem94.capturespleef.Strings.PLUGIN_PREFIX;
import static de.relluem94.capturespleef.Strings.PLUGIN_SECONDARY_COLOR;
import static de.relluem94.capturespleef.Strings.SNOWBALL_DISPLAYNAME;
import static de.relluem94.capturespleef.Strings.SNOWBALL_META_INFO;
import static de.relluem94.capturespleef.Strings.TEAM_BLUE_NAME;
import static de.relluem94.capturespleef.Strings.TEAM_RED_NAME;
import static de.relluem94.minecraft.server.spigot.essentials.Strings.PLUGIN_FORMS_SPACER_MESSAGE;
import static de.relluem94.minecraft.server.spigot.essentials.helpers.ChatHelper.consoleSendMessage;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        Location arena = new Location(Bukkit.getWorld(ACTIVE_WORLD), 142, 142, 142);
        Location lobby = new Location(Bukkit.getWorld(ACTIVE_WORLD), 140, 141, 188);

        a = rot.getSize();
        b = blau.getSize();
        teamSize = a + b;

        if (!(sender instanceof Player p)) {
            consoleSendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE, NO_USER);
            return true;
        }

        ItemStack d3 = new ItemStack(Material.SUGAR, 1);
        ItemMeta d4 = d3.getItemMeta();
        d4.setDisplayName("§8Wähle dein Team");
        d4.setLore(List.of("§8Rechtklicke auf den §cNetherBrick §8oder den §9Prismarin"));
        d3.setItemMeta(d4);

        ItemStack e1 = new ItemStack(Material.SNOWBALL, 94);
        ItemMeta e2 = e1.getItemMeta();
        e2.setDisplayName(SNOWBALL_DISPLAYNAME);
        e2.setLore(Arrays.asList(SNOWBALL_META_INFO));
        e1.setItemMeta(e2);

        //
        // CASP
        //
        if (cmd.getName().equalsIgnoreCase("casp")) {
            if (args.length == 0) {
                p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Nutze §c/casp <info>, <version>, <join> oder <leave>");
            } else {
                // Join
                if (args[0].equalsIgnoreCase("join")) {

                        if (cooldown.contains(p)) {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Du bist bereits in der CaptureSpleef Lobby");
                        } else if (scoreBoard.getTeams().size() == ts) {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Die Lobby ist voll bitte warte auf die nächste Runde");
                        } else {
                            if (a == b && teamSize == ts) {
                                p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Die Lobby ist Voll");
                            } else {
                                cooldown.add(p);
                                p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Du hast die CaptureSpleef Lobby betreten");
                                p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                                p.sendMessage(PLUGIN_SECONDARY_COLOR + "Rechstklicke mit dem Zucker auf den Prismarin oder den Netherbrick um in dein gewünchtes Team zukommen.");
                                p.sendMessage("");
                                p.sendMessage(PLUGIN_SECONDARY_COLOR + "Linksklicke mit dem erhaltenen Werkzeug auf den Lapis oder Redstone Block um das Team wieder zu verlassen.");
                                p.sendMessage("");
                                p.sendMessage(PLUGIN_SECONDARY_COLOR + "Nutze §d/casp info §a um Informationen über das Spiel und dessen Funktionen zu erhalten.");
                                p.sendMessage("§6" + PLUGIN_CHAT_SPACER);

                                p.teleport(lobby);
                                p.getInventory().clear();
                                p.getInventory().setItem(1, new ItemStack(d3));
                                p.getInventory().setItem(2, new ItemStack(e1));
                                p.setGameMode(GameMode.SURVIVAL);
                                p.setCustomName(CS_NAME);
                                p.setAllowFlight(false);
                                csLobby.addPlayer(p);
                            }


                    }
                } // Leave
                else if (args[0].equalsIgnoreCase("leave")) {

                        if (p.getCustomName().equals(CS_NAME)) {
                            cooldown.remove(p);
                            p.getInventory().clear();
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Du hast die CaptureSpleef Lobby verlassen");
                            p.teleport(arena);
                            p.setCustomName(p.getDisplayName());
                            p.setScoreboard(emptyScoreBoard);
                            scoreBoard.resetScores(p);
                            if (p.hasPermission("rellu.fly")) {
                                p.setAllowFlight(true);
                            }
                        } else if (p.getCustomName().equals(TEAM_RED_NAME) || p.getCustomName().equals(TEAM_BLUE_NAME)) {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Du musst das Team erst verlassen!");
                        } else {
                            p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Es ist ein Fehler aufgetreten!");
                        }

                } // Info
                else if (args[0].equalsIgnoreCase("info")) {
                    p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Spiele Erklärung und Ziel");
                    p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                    p.sendMessage(PLUGIN_SECONDARY_COLOR + "Du kannst nur auf der selben Block-Farbe laufen wie deine Team-Farbe ist");
                    p.sendMessage("§1          Team Blau » Prismarin Block");
                    p.sendMessage("§4          Team Rot » Netherbrick Block");
                    p.sendMessage("");
                    p.sendMessage(PLUGIN_SECONDARY_COLOR + "Wandle mit einem Linksklick gegnerische Blöcke zu deiner eigenen Farbe um");
                    p.sendMessage("");
                    p.sendMessage("§dZiel » §aErobere mit einem Linksklick die Metall-Säule in der gegnerischen Basis");
                    p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                } // Test
                else if (args[0].equalsIgnoreCase("test")) {
                    if (p.hasPermission("rellu.capturespleef.test")) {
                        p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                        p.sendMessage("§d" + teamSize);
                        p.setAllowFlight(true);
                        p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                    }
                } // Version
                else if (args[0].equals("version")) {
                    p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                    p.sendMessage(PLUGIN_PREFIX + PLUGIN_FORMS_SPACER_MESSAGE + PLUGIN_SECONDARY_COLOR + "Version " + Bukkit.getServer().getPluginManager().getPlugin("CaptureSpleef").getDescription().getVersion() + "!");
                    p.sendMessage("§6" + PLUGIN_CHAT_SPACER);
                }
            }
            return true;
        } else {
            p.sendMessage(NO_PERM);
            return true;
        }
    }
}
