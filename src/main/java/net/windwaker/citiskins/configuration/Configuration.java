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
package net.windwaker.citiskins.configuration;

import java.io.File;
import java.io.IOException;

import net.windwaker.citiskins.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Windwaker
 */
public abstract class Configuration extends YamlConfiguration {
	
	private final Logger logger = Logger.getInstance();
	private File file;

	/**
	 * Constructs a new configuration to memory.
	 *
	 * @param file
	 */
	public Configuration(File file) {
		this.file = file;
	}

	/**
	 * Gets the file in which the configuration is hosted.
	 *
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the configuration in which the configuration is hosted.
	 *
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Loads the configuration to memory, if the file doesn't exist, it will be created.
	 */
	public void load() {
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
				this.load(file);
			} else {
				this.load(file);
			}
			logger.info("Loaded configuration file at " + file.getAbsolutePath());
		} catch (IOException e) {
			logger.severe("Failed to create configuration file at " + file.getAbsolutePath() + ": " + e.getMessage());
		} catch (InvalidConfigurationException ce) {
			logger.severe("Failed to load configuration file at " + file.getAbsolutePath() + ": " + ce.getMessage());
		}
	}

	/**
	 * Saves the configuration to the configuration's file.
	 */
	public void save() {
		try {
			this.save(file);
		} catch (IOException e) {
			logger.severe("Failed to save configuration file at " + file.getAbsolutePath() + ": " + e.getMessage());
		}
	}
}
