package de.relluem94.capturespleef;

import java.util.Arrays;

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

public class CMD implements CommandExecutor {

    public de.relluem94.capturespleef.rellu main;

    public CMD(de.relluem94.capturespleef.rellu instance) {
        main = instance;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

        Location arena = new Location(main.server.getWorld(main.lobby), -104, 137, 272);
        Location lobby = new Location(main.server.getWorld(main.lobby), -132, 144, 272);

        main.a = main.rot.getSize();
        main.b = main.blau.getSize();
        main.teamsize = main.a + main.b;

        if (!(sender instanceof Player)) {
            main.cSM(main.prefix, main.noUser);
            return true;
        }

        Player p = (Player) sender;

        ItemStack d3 = new ItemStack(Material.SUGAR, 1);
        ItemMeta d4 = d3.getItemMeta();
        d4.setDisplayName("§8Wähle dein Team");
        d4.setLore(Arrays.asList("§8Rechtklicke auf den §cNetherBrick §8oder den §9Prismarin"));
        d3.setItemMeta(d4);

        ItemStack e1 = new ItemStack(Material.SNOWBALL, 94);
        ItemMeta e2 = e1.getItemMeta();
        e2.setDisplayName(main.hubitem5);
        e2.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
        e1.setItemMeta(e2);

        //
        // CASP
        //
        if (cmd.getName().equalsIgnoreCase("casp")) {

            if (args.length == 0) {
                p.sendMessage(main.prefix + " §aNutze §c/casp <info>, <version>, <join> oder <leave>");
            } else {
                // Join
                if (args[0].equalsIgnoreCase("join")) {
                    if (p.hasPermission("rellu.capturespleef.join")) {
                        if (main.cooldown.contains(p)) {
                            p.sendMessage("§d[CaptureSpleef] §aDu bist bereits in der CaptureSpleef Lobby");
                        } else if (main.sboard.getTeams().size() == main.ts) {
                            p.sendMessage("§d[CaptureSpleef] §aDie Lobby ist voll bitte warte auf die n§chste Runde");
                        } else {
                            if (main.a == main.b && main.teamsize == main.ts) {
                                p.sendMessage("§d[CaptureSpleef] §aDie Lobby ist Voll");
                            } else {
                                main.cooldown.add(p);
                                p.sendMessage("§d[CaptureSpleef] §aDu hast die CaptureSpleef Lobby betreten");
                                p.sendMessage("§6" + main.trenn);
                                p.sendMessage("§aRechstklicke mit dem Zucker auf den Prismarin oder den Netherbrick um in dein gewünchtes Team zukommen.");
                                p.sendMessage("§a");
                                p.sendMessage("§aLinksklicke mit dem erhaltenen Werkzeug auf den Lapis oder Redstone Block um das Team wieder zu verlassen.");
                                p.sendMessage("§a");
                                p.sendMessage("§aNutze §d/casp info §a um Informationen über das Spiel und dessen Funktionen zu erhalten.");
                                p.sendMessage("§6" + main.trenn);

                                p.teleport(lobby);
                                p.getInventory().clear();
                                p.getInventory().setItem(1, new ItemStack(d3));
                                p.getInventory().setItem(2, new ItemStack(e1));
                                p.setGameMode(GameMode.SURVIVAL);
                                p.setCustomName(main.csname);
                                p.setAllowFlight(false);
                                main.cslobby.addPlayer(p);
                            }

                        }
                    }
                } // Leave
                else if (args[0].equalsIgnoreCase("leave")) {
                    if (p.hasPermission("rellu.capturespleef.leave")) {
                        if (p.getCustomName().equals(main.csname)) {
                            main.cooldown.remove(p);
                            p.getInventory().clear();
                            p.sendMessage("§d[CaptureSpleef] §aDu hast die CaptureSpleef Lobby verlassen");
                            p.teleport(arena);
                            p.setCustomName(p.getDisplayName());
                            p.setScoreboard(main.emptysboard);
                            main.sboard.resetScores(p);
                            if (p.hasPermission("rellu.fly")) {
                                p.setAllowFlight(true);
                            }
                        } else if (p.getCustomName().equals("TeamRot") || p.getCustomName().equals("TeamBlau")) {
                            p.sendMessage("§d[CaptureSpleef] §aDu musst das Team erst verlassen!");
                        } else {
                            p.sendMessage("§d[CaptureSpleef] §aEs ist ein Fehler aufgetreten!");
                        }
                    }
                } // Info
                else if (args[0].equalsIgnoreCase("info")) {
                    p.sendMessage("§d[CaptureSpleef] §aSpiele Erklärung und Ziel");
                    p.sendMessage("§6" + main.trenn);
                    p.sendMessage("§aDu kannst nur auf der selben Block-Farbe laufen wie deine Team-Farbe ist");
                    p.sendMessage("§1          Team Blau \u00BB Prismarin Block");
                    p.sendMessage("§4          Team Rot \u00BB Netherbrick Block");
                    p.sendMessage("§a");
                    p.sendMessage("§aWandle mit einem Linksklick gegnerriche Bl§cke zu deiner eigenen Farbe um");
                    p.sendMessage("§a");
                    p.sendMessage("§dZiel \u00BB §aErobere mit einem Linksklick die Metall-Säule in der gegnerischen Basis");
                    p.sendMessage("§6" + main.trenn);
                } // Test
                else if (args[0].equalsIgnoreCase("test")) {
                    if (p.hasPermission("rellu.capturespleef.test")) {
                        p.sendMessage("§6" + main.trenn);
                        p.sendMessage("§d" + main.teamsize);
                        p.setAllowFlight(true);
                        p.sendMessage("§6" + main.trenn);
                    }
                } // Version
                else if (args[0].equals("version")) {
                    p.sendMessage("§6" + main.trenn);
                    p.sendMessage(main.prefix + " §aVersion " + Bukkit.getServer().getPluginManager().getPlugin("CaptureSpleef").getDescription().getVersion() + "!");
                    p.sendMessage("§6" + main.trenn);
                } // Reload
                else if (args[0].equalsIgnoreCase("reload")) {
                    if (!(p.hasPermission("rellu.capturespleef.reload"))) {
                        p.sendMessage("§6" + main.trenn);
                        p.sendMessage(main.prefix + " §aDir Fehlen die entsprechenden Rechte!");
                        p.sendMessage("§6" + main.trenn);
                    } else {
                        p.sendMessage("§6" + main.trenn);
                        p.sendMessage(main.prefix + " §aPlugin wird neugeladen");
                        main.cSM(main.prefix, "§aPlugin wird neugeladen");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "plm reload RelluMG");
                        main.cSM(main.prefix, "§aPlugin wurde neugeladen");
                        p.sendMessage(main.prefix + " §aPlugin wurde neugeladen");
                        p.sendMessage("§6" + main.trenn);
                        // rellu.admin.reload
                    }
                }

            }
            return true;
        } else {

            p.sendMessage(main.noPerm);
            return true;

        }
    }

}
