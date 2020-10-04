package de.relluem94.capturespleef.listener;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener{

	
	de.relluem94.capturespleef.rellu main;
	
	 public PlayerInteract(de.relluem94.capturespleef.rellu instance){
		 main = instance;
	 }


	
	 @EventHandler
		public void Schneeball(PlayerInteractEvent e) {
			Player p = e.getPlayer();
			if(p.hasPermission("rellu.lobby.snowball")) {
				ItemStack test = p.getInventory().getItemInHand();
				if(test.getType() == Material.SNOW_BALL){
					if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR) {
						ItemStack d1 = new ItemStack(Material.SNOW_BALL, 94);
		                  ItemMeta d2 = d1.getItemMeta();
		                  d2.setDisplayName(main.hubitem5);
		                  d2.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
		                  d1.setItemMeta(d2);
		      			p.getInventory().setItem(2, new ItemStack(d1)); 
						ItemMeta test2 = test.getItemMeta();
						String test3 = test2.getDisplayName();
						if (test3 == null) {return;}
						if (test3.contains("Schneeball")){
							 Vector test231 = p.getEyeLocation().multiply(7.8).getDirection();
								Vector vec = new Vector(test231.getX(), test231.getY(), test231.getZ());
							Projectile ball = p.launchProjectile(Snowball.class);
							ball.setVelocity(vec);
							if(p.hasPermission("rellu.group.vip") || p.hasPermission("rellu.group.builder") || p.hasPermission("rellu.group.sgbuilder") || p.hasPermission("rellu.group.pMod") || p.hasPermission("rellu.group.Mod") || p.hasPermission("rellu.group.sMod") || p.hasPermission("rellu.group.Geek") || p.hasPermission("rellu.group.Admin")) {
								if (p.getName().equals("Relluem94")) {
									main.shuffle();
									ball.setCustomName(main.setColors().get(2) + "R" + main.setColors().get(1) + "e"+ main.setColors().get(3) + "l" +main.setColors().get(4) + "l" + main.setColors().get(6) + "u");
									ball.setCustomNameVisible(true);
									e.setCancelled(true);
									ball.setFireTicks(1200);
									ball.setFallDistance(2000);
								}
								else if (p.getName().equals("Stellachen")) {
									main.shuffle();
									ball.setCustomName(main.setColors().get(2) + "S" + main.setColors().get(1) + "t"+ main.setColors().get(3) + "e" +main.setColors().get(4) + "l" + main.setColors().get(6) + "l" + main.setColors().get(7) + "a");
									ball.setCustomNameVisible(true);
									e.setCancelled(true);
								}
								else {
								ball.setCustomName(p.getDisplayName());
								ball.setCustomNameVisible(true);
								e.setCancelled(true);
								}
							}
							else {
								e.setCancelled(true);
							}
							
						}
					}
					else {
						ItemStack d1 = new ItemStack(Material.SNOW_BALL, 94);
		                  ItemMeta d2 = d1.getItemMeta();
		                  d2.setDisplayName(main.hubitem5);
		                  d2.setLore(Arrays.asList(main.hubitem5_info_1, main.hubitem5_info_2, main.hubitem5_info_3, main.hubitem5_info_4));
		                  d1.setItemMeta(d2);
		      			p.getInventory().setItem(2, new ItemStack(d1)); 
					}
					}
				else {}
					
			}
			else {}
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 //
	 //
	 //
	 //
	 //
	 //
	 
	 @EventHandler
		public void GameJoinAndLeave(PlayerInteractEvent evo){
			Player player = evo.getPlayer();
			
				
				main.a = main.rot.getSize();
				main.b = main.blau.getSize();
				main.teamsize = main.a + main.b;
			
			Location PosRot = new Location (main.server.getWorld(main.lobby), -141, 138, 272);
			Location PosBlau = new Location (main.server.getWorld(main.lobby), -124, 138, 272);
			
			ItemStack d3 = new ItemStack(Material.SUGAR, 1);
			ItemMeta d4 = d3.getItemMeta();
			d4.setDisplayName("§8Wähle dein Team");
			d4.setLore(Arrays.asList("§8Rechtklicke auf den §cNetherBrick §8oder den §9Prismarin"));
			d3.setItemMeta(d4);
			
			
			
			// Verlassen Team Blau
			
			if (evo.getPlayer().getWorld().getName().equals("lobby")) {
				if(evo.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_SPADE){
					if (!(evo.getAction() == Action.LEFT_CLICK_BLOCK)) return;
						if (evo.getClickedBlock().getType().equals(Material.LAPIS_BLOCK) && evo.getPlayer().getCustomName().equals("TeamBlau")) {
							double x1 = evo.getClickedBlock().getX();
							double y1 = evo.getClickedBlock().getY();
							double z1 = evo.getClickedBlock().getZ();
							//
							double y2 = y1 + 1;
							//
							Location loc = new Location(player.getWorld(), x1, y2, z1);
							
							
							if (loc.getBlock().getType().equals(Material.SKULL)) {
								Skull s = (Skull) loc.getBlock().getState();
								String s1 = s.getOwner();
								String s2 = s1.toLowerCase();
								if (player.getName().toLowerCase().equals(s2)) {
									loc.getBlock().setType(Material.AIR);
									evo.getClickedBlock().setType(Material.PRISMARINE);
									
									
									
									
									
								}
								else {player.sendMessage("§5[RelluAPI] §6Dieser Slot ist vergeben");}
							}
							player.setCustomName(main.csname);
							main.teams.remove(player);
							main.blau.removePlayer(player);
							main.cslobby.addPlayer(player);
							player.getInventory().setItemInHand(new ItemStack(d3)); 
							//
							//
							//
							  for (Player pla : Bukkit.getOnlinePlayers()) {
		            			  if (pla.getCustomName().equals("TeamRot") || pla.getCustomName().equals("TeamBlau") || pla.getCustomName().equals(main.csname)) {
		            				  pla.sendMessage(player.getDisplayName() + "§1 hat TeamBlau verlassen");
		            			  }
							  }
						}
				}
				
				
				// Verlassen Team Rot
				
				else if(evo.getPlayer().getInventory().getItemInHand().getType() == Material.DIAMOND_HOE){
					if (!(evo.getAction() == Action.LEFT_CLICK_BLOCK)) return; 
						if (evo.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK) && evo.getPlayer().getCustomName().equals("TeamRot")) {
							double x1 = evo.getClickedBlock().getX();
							double y1 = evo.getClickedBlock().getY();
							double z1 = evo.getClickedBlock().getZ();
							//
							double y2 = y1 + 1;
							//
							Location loc = new Location(player.getWorld(), x1, y2, z1);
							if (loc.getBlock().getType().equals(Material.SKULL)) {
								Skull s = (Skull) loc.getBlock().getState();
								String s1 = s.getOwner();
								String s2 = s1.toLowerCase();
								if (player.getName().toLowerCase().equals(s2)) {
									loc.getBlock().setType(Material.AIR);
									evo.getClickedBlock().setType(Material.NETHER_BRICK);
								}
								else {player.sendMessage("§5[RelluAPI] §6Dieser Slot ist vergeben");}
							}
							player.setCustomName(main.csname);
							main.teams.remove(player);
							main.rot.removePlayer(player);
							main.cslobby.addPlayer(player);
							player.getInventory().setItemInHand(new ItemStack(d3)); 
							//
							//
							//
							for (Player pla : Bukkit.getOnlinePlayers()) {
		            			  if (pla.getCustomName().equals("TeamRot") || pla.getCustomName().equals("TeamBlau") || pla.getCustomName().equals(main.csname)) {
		            				  pla.sendMessage(player.getDisplayName() + "§4 hat TeamRot verlassen");
		            			  }
							  }
						}
				}  
				
				
				
				
				// Betreten Team Rot
				
				else if(evo.getPlayer().getInventory().getItemInHand().getType() == Material.SUGAR  && evo.getPlayer().getCustomName().equals(main.csname)){
					if (!(evo.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
						if (evo.getClickedBlock().getType().equals(Material.NETHER_BRICK) &! evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.NETHER_BRICK) &! evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
							evo.getClickedBlock().setType(Material.REDSTONE_BLOCK);
							double x1 = evo.getClickedBlock().getX();
							double y1 = evo.getClickedBlock().getY();
							double z1 = evo.getClickedBlock().getZ();
							//
							double y2 = y1 + 1;
							//
							Location loc = new Location(player.getWorld(), x1, y2, z1);
							loc.getBlock().setType(Material.SKULL);
							Skull s = (Skull) loc.getBlock().getState();
							s.setSkullType(SkullType.PLAYER);
							s.setOwner(player.getName());
							s.update(true);
							ItemStack id3 = new ItemStack(Material.DIAMOND_HOE, 1);
							ItemMeta id4 = id3.getItemMeta();
							id4.setDisplayName("§4TeamRot");
							id4.setLore(Arrays.asList("§8Mache hiermit §1Blaue Steine §8zu §4Roten"));
							id3.setItemMeta(id4);
							player.getInventory().setItemInHand(new ItemStack(id3)); 
							
							player.setCustomName("TeamRot");
							//
							//
							//
							main.rot.addPlayer(player);
							main.teams.put(player, loc);
														
							player.setScoreboard(main.sboard);
							
							@SuppressWarnings("deprecation")
							Score score = main.obj.getScore(player);
							score.setScore(main.lives);
							//
							//		Game Start
							//
							
							
							
							main.a = main.rot.getSize();
							main.b = main.blau.getSize();
							main.teamsize = main.a + main.b;
							
							
							if (main.teamsize ==  main.ts || main.a == main.b) {
								for (Player pla : Bukkit.getOnlinePlayers()) {
			            			  if (pla.getCustomName().equals("TeamRot")) {
			            				  pla.teleport(PosRot);
			            			  }
			            			  else if (pla.getCustomName().equals("TeamBlau")) {
			            				  pla.teleport(PosBlau);
			            			  }
								  }
							}
							//
							//		Team Rot Beitrittsnachricht
							//
							for (Player pla : Bukkit.getOnlinePlayers()) {
		            			  if (pla.getCustomName().equals("TeamRot") || pla.getCustomName().equals("TeamBlau") || pla.getCustomName().equals(main.csname)) {
		            				  pla.sendMessage(player.getDisplayName() + "§4 hat TeamRot beigetreten");
		            			  }
							  }
						}
						
						// Betreten Team Blau
						
						else if (evo.getClickedBlock().getType().equals(Material.PRISMARINE) &! evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.PRISMARINE) &! evo.getPlayer().getEyeLocation().getBlock().getRelative(0, -2, 0).getType().equals(Material.AIR)) {
							evo.getClickedBlock().setType(Material.LAPIS_BLOCK);
							double x1 = evo.getClickedBlock().getX();
							double y1 = evo.getClickedBlock().getY();
							double z1 = evo.getClickedBlock().getZ();
							//
							double y2 = y1 + 1;
							//
							Location loc = new Location(player.getWorld(), x1, y2, z1);
							
							loc.getBlock().setType(Material.SKULL);
							Skull s = (Skull) loc.getBlock().getState();
							s.setSkullType(SkullType.PLAYER);
							s.setOwner(player.getName());
							s.update(true);
							ItemStack id3 = new ItemStack(Material.DIAMOND_SPADE, 1);
							ItemMeta id4 = id3.getItemMeta();
							id4.setDisplayName("§1TeamBlau");
							id4.setLore(Arrays.asList("§8Mache hiermit §4Roten Steine §8zu §1Blaue"));
							id3.setItemMeta(id4);
							player.getInventory().setItemInHand(new ItemStack(id3)); 
							
							player.setCustomName("TeamBlau");
							//
							//
							//
							main.blau.addPlayer(player);
							main.teams.put(player, loc);
							
							player.setScoreboard(main.sboard);
							
							@SuppressWarnings("deprecation")
							Score score = main.obj.getScore(player);
							score.setScore(main.lives);
							//
							//		Game Start
							//
							
							
							
							main.a = main.rot.getSize();
							main.b = main.blau.getSize();
							main.teamsize = main.a + main.b;
							
							
							if (main.teamsize == main.ts && main.a == main.b) {
								for (Player pla : Bukkit.getOnlinePlayers()) {
			            			  if (pla.getCustomName().equals("TeamRot")) {
			            				  pla.teleport(PosRot);
			            			  }
			            			  else if (pla.getCustomName().equals("TeamBlau")) {
			            				  pla.teleport(PosBlau);
			            			  }
								  }
							}
							//
							//		Team Blau Beitrittsnachricht
							//
							for (Player pla : Bukkit.getOnlinePlayers()) {
		            			  if (pla.getCustomName().equals("TeamRot") || pla.getCustomName().equals("TeamBlau") || pla.getCustomName().equals(main.csname)) {
		            				  pla.sendMessage(player.getDisplayName() + "§1 hat TeamBlau beigetreten");
		            			  }
							}
						}
					}
				}
			}
		
	 
	 //
	 //
	 //
	 //
	 //
	 //
	 //
	 
	 @EventHandler
	 public void SchildBenutzen(PlayerInteractEvent e)
	  {
		 if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
		 if (e.getClickedBlock().getType().equals(Material.WALL_SIGN) || e.getClickedBlock().getType().equals(Material.SIGN) || 
				 e.getClickedBlock().getType().equals(Material.SIGN_POST) && e.getPlayer().hasPermission("rellu.sign.item.use")){
			 try {
				BlockState schild = e.getClickedBlock().getState();
		
	      final Player p = e.getPlayer();
	      Sign s = (Sign)e.getClickedBlock().getState();

	 if (e.getPlayer().hasPermission("rellu.capturespleef.sign.use")){

    if (((schild instanceof Sign)) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
      if ((s.getLine(0).equalsIgnoreCase("§d[CaptureSpleef]")) && (s.getLine(2).equalsIgnoreCase("§aSpiel verlassen"))) {
    	  p.performCommand("casp leave");
      }
      else if ((s.getLine(0).equalsIgnoreCase("§d[CaptureSpleef]")) && (s.getLine(2).equalsIgnoreCase("§aSpiel betreten"))) {
    	  p.performCommand("casp join"); 
	      }
    	}
	 }
}
catch (Exception localException) {}
}				 
}
}			 
	 
	 
	 
	 
	
	
}
