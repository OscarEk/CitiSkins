/*
The GuildCraft project.
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

import net.citizensnpcs.api.npc.NPC;
import net.windwaker.citiskins.configuration.Configuration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Windwaker
 */
public class Capes {
	
	private final Configuration npcs = CitiSkins.getNPCS();	
	/**
	 * Resets the player's selected NPC's cape. A valid NPC must be a generic HumanEntity only.
	 * 
	 * @param player 
	 */
	public void remove(NPC npc) {
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof HumanEntity) {
			SpoutPlayer human = CitiSkins.toSpoutPlayer(npc);
			human.resetCape();
			npcs.set("npcs." + npc.getId() + ".cape", "default");
			npcs.save();
		}
	}
	
	/**
	 * Changes the player's selected NPC's cape. A valid NPC must be a generic HumanEntity only.
	 * 
	 * @param player
	 * @param url 
	 */
	public void apply(NPC npc, String url) {
		Entity entity = npc.getBukkitEntity();
		if (npc != null && entity instanceof HumanEntity) {
			SpoutPlayer human = CitiSkins.toSpoutPlayer(npc);
			human.setCape(url);
			npcs.set("npcs." + npc.getId() + ".cape", url);
			npcs.save();
		}
	}
}