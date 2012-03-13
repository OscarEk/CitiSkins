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

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Windwaker
 */
public class CitiSkins extends JavaPlugin {
	
	private final Logger logger = getLogger();
	private static CitiSkins instance;
	private final NpcManager npcs = new NpcManager();

	/**
	 * Constructs a new CitiSkins plugin
	 */
	public CitiSkins() {
		instance = this;
	}

	/**
	 * Called when the plugin is enabled; initialization.
	 */
	@Override
	public void onEnable() {
		
		// Load config
		npcs.load();

		// Register commands
		this.getCommand("citiskins").setExecutor(new Commands(this));

		// Register events
		Bukkit.getPluginManager().registerEvents(new NpcListener(), this);

		logger.info("CitiSkins v" + this.getDescription().getVersion() + " by Windwaker enabled!");
	}

	/**
	 * Called when the plugin is disabled.
	 */
	@Override
	public void onDisable() {
		logger.info("CitiSkins v" + this.getDescription().getVersion() + " by Windwaker disabled.");
	}

	/**
	 * Gets a singleton instance of the CitiSkins plugin created.
	 * 
	 * @return CitiSkins instance
	 */
	public static CitiSkins getInstance() {
		return instance;
	}

	/**
	 * Gets the {@link NpcManager}
	 * 
	 * @return NpcManager
	 */
	public NpcManager getNpcManager() {
		return npcs;
	}
}
