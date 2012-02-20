/*
The CitiSkins project.
Copyright (C) 2012 Walker Crouse

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.windwaker.citiskins;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.EntitySkinType;

/**
 *
 * @author Windwaker
 */
public class Commands implements CommandExecutor {
	
	private final CitiSkins plugin;
	
	public Commands(CitiSkins plugin) {
		this.plugin = plugin;
	}

	/**
	 * Called when a command is issued. Checks if it's the sender is a player and it's using the 
	 * 'citiskins' sub-command. If there are no arguments, it sends the version. If there are arguments, 
	 * it sends to the parser method.
	 * 
	 * @param sender of the command.
	 * @param command being sent.
	 * @param name of the command.
	 * @param arguments of the command.
	 * @return whether or not to send usage of the command.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && label.equalsIgnoreCase("citiskins")) {
			if (args.length > 0) {
				this.parseCommand((Player) sender, args);
				return true;
			} else {
				this.sendVersion((Player) sender);
			}
		}
		return false;
	}
	
	/**
	 * Parses a command with arguments, sends help if there is one argument, 
	 * skins or capes an entity if there are two arguments, and checks to see if the URL ends in '.png'.
	 * 
	 * @param sender of the command
	 * @param arguments of the command
	 */
	public void parseCommand(Player sender, String[] args) {
		if (args.length == 1) {
			this.sendHelp(sender);
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("skin")) {
				if (args[1].endsWith(".png")) {
					this.changeNPCSkin(sender, args[1]);
				} else {
					sender.sendMessage(ChatColor.RED + "Error: Invalid url! A skins URL must end with '.png'!");
				}
			} else if (args[0].equalsIgnoreCase("cape")) {
				if (args[1].endsWith(".png")) {
					this.changeNPCCape(sender, args[1]);
				} else {
					sender.sendMessage(ChatColor.RED + "Error: Invalid url! A skins URL must end with '.png'!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Error: Argument 1 must be either 'skin' or 'cape'!");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Error: Insufficient arguments!");
		}
	}
	
	/**
	 * Changes the player's selected NPC's skin. A valid NPC must be a generic LivingEntity or HumanEntity. 
	 * 
	 * @param player to get selected NPC from
	 * @param url to the skin to apply.
	 */
	public void changeNPCSkin(Player player, String url) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof LivingEntity) {
			if (entity instanceof HumanEntity) {
				HumanEntity human = (HumanEntity) entity;
				SpoutManager.getAppearanceManager().setGlobalSkin(human, url);
			} else {
				SpoutManager.getAppearanceManager().setGlobalEntitySkin((LivingEntity) entity, url, EntitySkinType.DEFAULT);
			}
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to skin! Please select one and try again!");
		}
	}
	
	/**
	 * Changes the player's selected NPC's cape. A valid NPC must be a generic HumanEntity only.
	 * 
	 * @param player
	 * @param url 
	 */
	public void changeNPCCape(Player player, String url) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof HumanEntity) {
			HumanEntity human = (HumanEntity) entity;
			SpoutManager.getAppearanceManager().setGlobalCloak(human, url);
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to cape! Please select one and try again!");
		}
	}
	
	/**
	 * Sends the 'CitiSkins' version to the sender.
	 * 
	 * @param sender of the command.
	 */
	public void sendVersion(Player sender) {
		sender.sendMessage(ChatColor.GREEN + "CitiSkins v" + plugin.getDescription().getVersion() 
				+ " by Windwaker enabled!");
	}
	
	/**
	 * Sends the 'CitiSkins' help menu.
	 * 
	 * @param sender of the command.
	 */
	public void sendHelp(Player sender) {
		sender.sendMessage("-- CitiSkins Help --");
		sender.sendMessage("-- /citiskins <skin|cape> <url> --");
		sender.sendMessage("-- You must have an NPC selected in Citizens. --");
	}
}