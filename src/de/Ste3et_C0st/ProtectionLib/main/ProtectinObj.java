package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.plugin.Plugin;

public class ProtectinObj {

	private Plugin pl;
	public ProtectinObj(Plugin plugin){
		this.pl = plugin;
	}
	
	public Plugin getPlugin(){
		return pl;
	}
	
}
