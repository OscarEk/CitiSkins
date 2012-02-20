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
import org.bukkit.entity.Player;

/**
 *
 * @author Windwaker
 */
public class Commands implements CommandExecutor {
	
	private final CitiSkins plugin;
	
	public Commands(CitiSkins plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && label.equalsIgnoreCase("citiskins")) {
			if (args.length > 0) {
				this.parseCommand((Player) sender, args);
				return true;
			} else {
				// TODO: Send help command
			}
		}
		return false;
	}
	
	public void parseCommand(Player sender, String[] args) {
		if (args.length == 1) {
			sender.sendMessage(ChatColor.RED + "Error: Not enough arguments!");
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("skin")) {
				if (args[1].endsWith(".png")) {
					this.changeNPCSkin(sender);
				} else {
					sender.sendMessage(ChatColor.RED + "Error: Invalid url! A skins URL must end with '.png'!");
				}
			} else if (args[0].equalsIgnoreCase("cape")) {
				if (args[1].endsWith(".png")) {
					this.changeNPCCape(sender);
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
	
	public void changeNPCSkin(Player player) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		if (npc != null && npc.getBukkitEntity() instanceof Player) {
			// TODO: Skin
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to skin! Please select one and try again!");
		}
	}
	
	public void changeNPCCape(Player player) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		if (npc != null && npc.getBukkitEntity() instanceof Player) {
			// TODO: Cape
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to cape! Please select one and try again!");
		}
	}
}