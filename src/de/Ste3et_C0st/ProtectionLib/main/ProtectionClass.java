package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ProtectionClass {

	private boolean isEnable = true, isLoaded = false;
	private Plugin plugin;
	private String name;
	
	public ProtectionClass(String pluginName){this.name = pluginName;checkLoaded();}
	public Plugin getPlugin(){return this.plugin;}
	public String getName(){return this.name;}
	public boolean isLoaded(){return this.isLoaded;}
	
	private void checkLoaded(){
		this.isLoaded = Bukkit.getPluginManager().isPluginEnabled(this.name);
		if(isLoaded && isEnable){
			this.plugin = Bukkit.getPluginManager().getPlugin(this.name);
			ProtectionLib.getInstance().getLogger().info("Protection lib hook into: " + plugin.getName() + " " + plugin.getDescription().getVersion());
		}
	}
}
