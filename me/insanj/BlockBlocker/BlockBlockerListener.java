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
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBlockerListener extends BlockListener{

	BlockBlocker plugin;
	
	public BlockBlockerListener(BlockBlocker instance){	
		plugin = instance;
	}
	
	public void onBlockPlace(BlockPlaceEvent event){
		Scanner outdoors;
		
		try {
			outdoors = new Scanner(new File("plugins/BlockBlocker/blocked.txt"));
		} catch (FileNotFoundException e) {
			if( event.getBlockAgainst().getType().equals(Material.SIGN_POST) ){
				event.getPlayer().sendMessage(ChatColor.RED + "You can't place a block against a sign post!");
				event.setCancelled(true);
			}
			
			return;
		}//end try/catch
			
		while( outdoors.hasNextLine() ){
			String material = outdoors.nextLine();
			if( event.getBlockAgainst().getType().equals(Material.getMaterial(material.toUpperCase())) ){
				event.getPlayer().sendMessage(ChatColor.RED + "You can't place a block against material type " + material + "!");
				event.setCancelled(true);
				return;
			}
		}//end while
	}//end onBlockPlace()
	
}//end class BlockBlockerListener
