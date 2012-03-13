/**
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
package net.windwaker.citiskins;

import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author Windwaker
 */
public class NpcListener implements Listener {
	
	private final CitiSkins plugin = CitiSkins.getInstance();
	private final NpcManager npcs = plugin.getNpcManager();

	/**
	 * Called when an NPC spawn in the world; setting saved skins.
	 *
	 * @param event
	 */
	@EventHandler
	public void onNpcSpawn(NPCSpawnEvent event) {
		NPC npc = event.getNPC();
		if (npcs.npcExists(npc)) {
			NpcManager npcs = plugin.getNpcManager();
			if (!npcs.hasDefaultSkin(npc)) {
				npcs.applySkin(npc, npcs.getSkin(npc));
			}
			
			if (!npcs.hasDefaultCape(npc)) {
				npcs.applyCape(npc, npcs.getCape(npc));
			}
		}
	}
}
