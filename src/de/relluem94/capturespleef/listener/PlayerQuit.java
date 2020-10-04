package de.relluem94.capturespleef.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener{

	
	de.relluem94.capturespleef.rellu main;
	
	 public PlayerQuit(de.relluem94.capturespleef.rellu instance){
		 main = instance;
	 }

		@SuppressWarnings("deprecation")
		@EventHandler
		public void GameIntrupt(PlayerQuitEvent e) {
			Player p = e.getPlayer();
			if (p.getCustomName().equals("TeamRot")) {
				main.teams.get(p).getBlock().setType(Material.AIR);
				main.teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.NETHER_BRICK);
				main.teams.remove(p);
				main.rot.removePlayer(p);
				main.sboard.resetScores(p);
			}
			else if (p.getCustomName().equals("TeamBlau")) {
				main.teams.get(p).getBlock().setType(Material.AIR);
				main.teams.get(p).getBlock().getRelative(0, -1, 0).setType(Material.PRISMARINE);
				main.teams.remove(p);
				main.blau.removePlayer(p);
				main.sboard.resetScores(p);
			}
			else if (p.getCustomName().equals(main.csname)) {
				main.cslobby.removePlayer(p);
				main.sboard.resetScores(p);
			}
			
		}
	 
}