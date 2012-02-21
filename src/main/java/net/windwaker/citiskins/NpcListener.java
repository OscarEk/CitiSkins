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

import java.util.Set;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.windwaker.citiskins.configuration.Configuration;
import org.bukkit.event.Listener;

/**
 *
 * @author Windwaker
 */
public class NpcListener implements Listener {
	
	private final Configuration npcs = CitiSkins.getNPCS();
	
	public void onNpcSpawn(NPCSpawnEvent event) {
		NPC npc = event.getNPC();
		Set<String> ids = npcs.getConfigurationSection("npcs").getKeys(false);
		String path = "npcs." + npc.getId();
		if (ids.contains(npc.getId())) {
			if (!npcs.getString(path + ".skin").equalsIgnoreCase("default")) {
				CitiSkins.getSkins().apply(npc, npcs.getString(path + ".skin"));
			}
			
			if (!npcs.getString(path + ".cape").equalsIgnoreCase("default")) {
				CitiSkins.getCapes().apply(npc, npcs.getString(path + ".cape"));
			}
		}
	}
}