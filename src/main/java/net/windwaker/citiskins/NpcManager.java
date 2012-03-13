package net.windwaker.citiskins; /**
 * The CitiSkins project.
 * Copyright (C) 2012 Walker Crouse
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityPlayer;
import net.windwaker.citiskins.configuration.Configuration;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.player.SpoutPlayer;

import java.io.File;
import java.util.Set;

/**
 * @author Windwaker
 */
public class NpcManager extends Configuration {
	
	public NpcManager() {
		super(new File("plugins/CitiSkins/config.yml"));
	}

	/**
	 * Saves a cape url to memory and disk.
	 *
	 * @param npc
	 * @param state
	 */
	public void saveCape(NPC npc, String state) {
		this.set("npcs." + npc.getId() + ".cape", state);
		this.save();
	}

	/**
	 * Whether or not an NPC is saved on CitiSkins disk space.
	 *
	 * @param npc
	 * @return true if the npc has been saved before
	 */
	public boolean npcExists(NPC npc) {
		return getIds().contains(npc);
	}

	/**
	 * Gets a set of Ids in String
	 *
	 * @return String set of Ids
	 */
	public Set<String> getIds() {
		return getConfigurationSection("npcs").getKeys(false);
	}

	/**
	 * Gets the saved cape of an NPC
	 *
	 * @param npc
	 * @return cape url
	 */
	public String getCape(NPC npc) {
		return getString("npcs." + npc.getId() + ".cape");
	}

	/**
	 * Gets the saved skin of an NPC
	 *
	 * @param npc
	 * @return skin url
	 */
	public String getSkin(NPC npc) {
		return getString("npcs." + npc.getId() + ".cape");
	}

	/**
	 * Whether or not the NPCs skin is set to default
	 *
	 * @param npc
	 * @return true if the NPC uses the default skin
	 */
	public boolean hasDefaultSkin(NPC npc) {
		return getString("npcs." + npc.getId() + ".skin").equalsIgnoreCase("default");
	}

	/**
	 * Whether or not the NPCs cape is set to default
	 *
	 * @param npc
	 * @return true if the NPC uses the default cape.
	 */
	public boolean hasDefaultCape(NPC npc) {
		return getString("npcs." + npc.getId() + ".cape").equalsIgnoreCase("default");
	}

	/**
	 * Returns the NPC as a SpoutPlayer
	 *
	 * @author Top_Cat
	 * @param npc
	 * @return SpoutPlayer
	 */
	public static SpoutPlayer toSpoutPlayer(NPC npc) {
		try {
			Class.forName("org.getspout.spout.Spout");
			Entity entity = ((CraftHumanEntity) npc.getBukkitEntity()).getHandle();
			SpoutCraftPlayer player = new SpoutCraftPlayer((CraftServer) Bukkit.getServer(), (EntityPlayer) entity);
			return player;
		} catch (ClassNotFoundException e) {
			Bukkit.getServer().getLogger().warning("Cannot get spout player without spout installed");
		}

		return null;
	}
}

