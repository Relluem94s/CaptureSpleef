package de.relluem94.capturespleef.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;



public class EntityDamageByEntity implements Listener{
	
	de.relluem94.capturespleef.rellu main;
	
	public EntityDamageByEntity(de.relluem94.capturespleef.rellu instance){
		main = instance;
		}

	
	 @EventHandler
	 public void SchneeballSchaden(EntityDamageByEntityEvent e) {
	 if (e.getDamager() instanceof Snowball) {
					Player pla = (Player) e.getEntity();
					Snowball snowball = (Snowball) e.getDamager();
					ProjectileSource shooter = snowball.getShooter();
					if (shooter instanceof Player) {
						Player plo = (Player) shooter;
						
						
//						pla.sendMessage(e.getCause() + "");
//						plo.sendMessage(e.getCause() + "");
						if(plo.hasPermission("rellu.lobby.snowball")) {
							if(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL && pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) {
								
								if (plo.getCustomName().equals("TeamRot")) 
								{
									
									if (pla.getCustomName().equals("TeamBlau"))
									{
										pla.setFireTicks(4);
//										pla.sendMessage(e.getCause() + "1");
//										plo.sendMessage(e.getCause() + "2");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "3");
//										plo.sendMessage(e.getCause() + "4");
									}
								}
								// Hier Team Blau
								else if (plo.getCustomName().equals("TeamBlau")) 
								{
									if (pla.getCustomName().equals("TeamRot"))
									{
										pla.setFallDistance(4);
//										pla.sendMessage(e.getCause() + "5");
//										plo.sendMessage(e.getCause() + "6");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "7");
//										plo.sendMessage(e.getCause() + "8");
									}
								}
							}
								else 
								{
									Location lolo = pla.getLocation();
									Location polo = plo.getLocation();
									pla.teleport(polo);
									plo.teleport(lolo);
//									pla.sendMessage(e.getCause() + "9");
//									plo.sendMessage(e.getCause() + "0");
									// e.getEntity().sendMessage(e.getCause() + "");
								}
								
										
								
							}
							else if(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) ||
									pla.getInventory().getItemInHand().getType() == Material.SNOWBALL && !(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL)){
								// Hier Team Rot
								if (plo.getCustomName().equals("TeamRot")) 
								{
									
									if (pla.getCustomName().equals("TeamBlau"))
									{
										pla.setFireTicks(4);
//										pla.sendMessage(e.getCause() + "1");
//										plo.sendMessage(e.getCause() + "2");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "3");
//										plo.sendMessage(e.getCause() + "4");
									}
								}
								// Hier Team Blau
								else if (plo.getCustomName().equals("TeamBlau")) 
								{
									if (pla.getCustomName().equals("TeamRot"))
									{
										pla.setFallDistance(4);
//										pla.sendMessage(e.getCause() + "5");
//										plo.sendMessage(e.getCause() + "6");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "7");
//										plo.sendMessage(e.getCause() + "8");
									}
								}
							
							else if(!(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) ||
									!(pla.getInventory().getItemInHand().getType() == Material.SNOWBALL) && !(plo.getInventory().getItemInHand().getType() == Material.SNOWBALL)){
								// Hier Team Rot
								if (plo.getCustomName().equals("TeamRot")) 
								{
									
									if (pla.getCustomName().equals("TeamBlau"))
									{
										pla.setFireTicks(4);
//										pla.sendMessage(e.getCause() + "1");
//										plo.sendMessage(e.getCause() + "2");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "3");
//										plo.sendMessage(e.getCause() + "4");
									}
								}
								// Hier Team Blau
								else if (plo.getCustomName().equals("TeamBlau")) 
								{
									if (pla.getCustomName().equals("TeamRot"))
									{
										pla.setFallDistance(4);
//										pla.sendMessage(e.getCause() + "5");
//										plo.sendMessage(e.getCause() + "6");
									}
									else 
									{
										e.setCancelled(true);
//										pla.sendMessage(e.getCause() + "7");
//										plo.sendMessage(e.getCause() + "8");
									}
								}
							
							}
							else {
								 e.setCancelled(true);
								}
					}
					else {
						 e.setCancelled(true);
						}
				}
						
						
						
					}

	 }
}