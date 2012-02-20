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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Windwaker
 */
public class Commands implements CommandExecutor {
	
	private final CitiSkins plugin;
	private final NpcManager npcs = CitiSkins.getNpcManager();
	
	/**
	 * Constructs the 'CitiSkins' CommandExecutor.
	 * 
	 * @param CitiSkins plugin 
	 */
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
	 * Parses the arguments of the command. If there are insufficient arguments, help will be sent. 
	 * 
	 * @param sender of the command
	 * @param arguments of the command
	 */
	public void parseCommand(Player sender, String[] args) {
		String cmd;
		String action;
		String url;
		if (args.length < 3) {
			this.sendHelp(sender);
		}
		
		if (args.length == 2) {
			cmd = args[0];
			action = args[1];
			this.execute(sender, cmd, action);
		}
		
		if (args.length == 3) {
			cmd = args[0];
			action = args[1];
			url = args[2];
			this.execute(sender, cmd, action, url);
		}		
		
		if (args.length > 3) {
			this.sendHelp(sender);
		}
	}
	
	/**
	 * Executes parsed command that does not have a URL given.
	 * 
	 * @param player
	 * @param cmd
	 * @param action 
	 */
	public void execute(Player player, String cmd, String action) {
		if (action.equalsIgnoreCase("remove")) {
			if (cmd.equalsIgnoreCase("skin")) {
				npcs.removeNpcSkin(player);
			}
			
			if (cmd.equalsIgnoreCase("cape")) {
				npcs.removeNpcCape(player);
			}
		} else {
			this.sendHelp(player);
		}
	}
	
	/**
	 * Executes parsed command that does have a URL given.
	 * 
	 * @param player
	 * @param cmd
	 * @param action
	 * @param url 
	 */
	public void execute(Player player, String cmd, String action, String url) {
		if (action.equalsIgnoreCase("apply")) {
			if (cmd.equalsIgnoreCase("skin")) {
				npcs.applyNpcSkin(player, url);
			}
			
			if (cmd.equalsIgnoreCase("cape")) {
				npcs.applyNpcCape(player, url);
			}
		} else {
			this.sendHelp(player);
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