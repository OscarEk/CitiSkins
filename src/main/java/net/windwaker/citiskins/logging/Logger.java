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
package net.windwaker.citiskins.logging;

import java.util.logging.Level;
import org.bukkit.Bukkit;

/**
 *
 * @author Windwaker
 */
public class Logger {
	
	private static final Logger logger = new Logger();
	
	private Logger() {
		
	}
	
	public static Logger getInstance() {
		return logger;
	}
	
	public void log(Level level, Object obj) {
		Bukkit.getLogger().log(level, "[CitiSkins] " + obj);
	}
	
	public void info(Object obj) {
		log(Level.INFO, obj);
	}
	
	public void warn(Object obj) {
		log(Level.WARNING, obj);
	}
	
	public void severe(Object obj) {
		log(Level.SEVERE, obj);
	}
	
	public void debug(Object obj) {
		log(PluginLevel.DEBUG, obj);
	}
	
	public void enable(Object obj) {
		log(PluginLevel.ENABLE, obj);
	}
	
	public void disable(Object obj) {
		log(PluginLevel.DISABLE, obj);
	}
	
	public void config(Object obj) {
		log(PluginLevel.CONFIG, obj);
	}
}