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

import java.io.File;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityPlayer;
import net.windwaker.citiskins.configuration.Configuration;
import net.windwaker.citiskins.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Windwaker
 */
public class CitiSkins extends JavaPlugin {
	
	private final Logger logger = Logger.getInstance();
	private static final Configuration npcs = new Configuration(new File("plugins/CitiSkins/npcs.yml"));
	private static final Capes capes = new Capes();
	private static final Skins skins = new Skins();

	@Override
	public void onEnable() {
		npcs.load();
		this.getCommand("citiskins").setExecutor(new Commands(this));
		Bukkit.getPluginManager().registerEvents(new NpcListener(), this);
		logger.enable("CitiSkins v" + this.getDescription().getVersion() + " by Windwaker enabled!");
	}
	
	@Override
	public void onDisable() {
		logger.disable("CitiSkins v" + this.getDescription().getVersion() + " by Windwaker disabled.");
	}
	
	public static Configuration getNPCS() {
		return npcs;
	}
	
	public static Skins getSkins() {
		return skins;
	}
	
	public static Capes getCapes() {
		return capes;
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