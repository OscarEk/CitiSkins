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
public class NpcManager {
	
	/**
	 * Resets the player's selected NPC's skin. A valid NPC must be a generic LivingEntity or HumanEntity.
	 * 
	 * @param player 
	 */
	public void removeNpcSkin(Player player) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof LivingEntity) {
			if (entity instanceof HumanEntity) {
				HumanEntity human = (HumanEntity) entity;
				SpoutManager.getAppearanceManager().resetGlobalSkin(human);
			} else {
				SpoutManager.getAppearanceManager().resetEntitySkin((LivingEntity) entity);
			}
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to unskin! Please select one and try again!");
		}
	}
	
	/**
	 * Changes the player's selected NPC's skin. A valid NPC must be a generic LivingEntity or HumanEntity. 
	 * 
	 * @param player to get selected NPC from
	 * @param url to the skin to apply.
	 */
	public void applyNpcSkin(Player player, String url) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof LivingEntity) {
			if (entity instanceof HumanEntity) {
				HumanEntity human = (HumanEntity) entity;
				SpoutManager.getAppearanceManager().setGlobalSkin(human, url);
			} else {
				SpoutManager.getAppearanceManager().setGlobalEntitySkin((LivingEntity) entity, url, EntitySkinType
						.DEFAULT);
			}
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to skin! Please select one and try again!");
		}
	}
	
	/**
	 * Resets the player's selected NPC's cape. A valid NPC must be a generic HumanEntity only.
	 * 
	 * @param player 
	 */
	public void removeNpcCape(Player player) {
		NPC npc = CitizensAPI.getNPCManager().getSelectedNPC(player);
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof HumanEntity) {
			HumanEntity human = (HumanEntity) entity;
			SpoutManager.getAppearanceManager().resetGlobalCloak(human);
		} else {
			player.sendMessage(ChatColor.RED 
					+ "Error: Couldn't find a valid NPC to cape! Please select one and try again!");
		}
	}
	
	/**
	 * Changes the player's selected NPC's cape. A valid NPC must be a generic HumanEntity only.
	 * 
	 * @param player
	 * @param url 
	 */
	public void applyNpcCape(Player player, String url) {
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
}