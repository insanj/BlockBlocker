/*
 Created by Julian Weiss (insanj), updates frequent on Google+ (and sometimes Twitter)!

 Please do not modify or decompile at any date, but feel free to distribute with credit.
 Designed and created entirely on Wednesday, August 10th, 2011.
 Last edited on: 8/12/11

 BlockBlocker version 1.1!
 Special thanks to: 
 		powback, for the idea and design!
 		RugRats, for more ideas in the 1.1 update.
 		
 Works with the current CraftBukkit Build (#1000).
 All other information should be available at bukkit.org under BlockBlocker.

 THIS VERSION CURRENT HAS TWO CLASSES:
			BlockBlocker.java
			BlockBlockerListener.java

*/

package me.insanj.BlockBlocker;
import java.io.File; 
import java.io.FileWriter;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBlocker extends JavaPlugin{

	BlockBlockerListener blockListener = new BlockBlockerListener(this);
	Logger log = Logger.getLogger("Minecraft");
	String version = "1.1";
	
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		log.info("{BlockBlocker} version " + version + " (by insanj) has been enabled!");
	}
	
	public void onDisable(){
		log.info("{BlockBlocker} version " + version + " (by insanj) has been disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
			
		if(commandLabel.equalsIgnoreCase("bb")){
			generateConfig();
			
			if(sender instanceof Player)
				sender.sendMessage(ChatColor.GREEN + "Config generated successfully.");
			else
				log.info("Config generated successfully.");
		}
		
		return true;
		
	}//end onCommand()
	
	public void generateConfig(){
		try{
			new File("plugins/BlockBlocker").mkdir();
			File file = new File("plugins/BlockBlocker/blocked.txt");
			FileWriter output = new FileWriter(file, false);
			output.write("Use this config file to list material names to have them blocked.\n");
			output.write("Applicable materials can be found in the Bukkit javadocs. A few are already set below.\n");
			output.write("sign\n");
			output.write("sign_post\n");
			output.write("torch\n");
			output.write("redstone_torch\n");
			output.write("button\n");
			output.write("lever\n");
			output.close();
		} catch(Exception e){
			log.severe("{BlockBlocker} couldn't generate a config file: ");
			e.printStackTrace();
		}
		
	}//end generateConfig()
	
}//end class BlockBlocker


/***********************************Contents of "plugin.yml":*******************************
name: BlockBlocker
version: 1.1
author: insanj
main: me.insanj.BlockBlocker.BlockBlocker
description: Doesn't allow any user to place a block against a sign.

commands:
   bb:
     description: Generates the config file, to allow multiple blocked items.
     usage: /<command>

******************************************************************************************/