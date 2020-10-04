package de.relluem94.capturespleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;


public class SignChange implements Listener{

	
	de.relluem94.capturespleef.rellu main;
	
	 public SignChange(de.relluem94.capturespleef.rellu instance){
		 main = instance;
	 }
	 
	 
		@EventHandler
		public void SignCreate(SignChangeEvent e)  {
			
			 if ((e.getPlayer().hasPermission("rellu.capturespleef.sign.create")) && (e.getLine(0).equalsIgnoreCase("")) && 
				(e.getLine(3).equalsIgnoreCase("[cs01]")) && (e.getLine(2).equalsIgnoreCase("")) && (e.getLine(1).equalsIgnoreCase(""))) {
				 e.setLine(0, "§d[CaptureSpleef]");
			     e.setLine(1, "");
			     e.setLine(2, "§aSpiel betreten");
			     e.setLine(3, "");
			     e.getPlayer().sendMessage(main.prefix + main.schild);
			     }
			 else if ((e.getPlayer().hasPermission("rellu.capturespleef.sign.create")) && (e.getLine(0).equalsIgnoreCase("")) &&
					 (e.getLine(3).equalsIgnoreCase("[cs02]")) && (e.getLine(2).equalsIgnoreCase("")) && (e.getLine(1).equalsIgnoreCase(""))) {
				 e.setLine(0, "§d[CaptureSpleef]");
				 e.setLine(1, "");
			     e.setLine(2, "§aSpiel verlassen");
			     e.setLine(3, "");
			     e.getPlayer().sendMessage(main.prefix + main.schild);
			     }
		}
	 
	 
}