package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class ProtectinObj {

	private Player p;
	private Location loc;
	
	private Plugin pl;
	public ProtectinObj(Plugin plugin){
		this.pl = plugin;
	}
	
	public Plugin getPlugin(){
		return pl;
	}
	
	public abstract boolean canBuild(Player player, Location loc);
	public abstract boolean isOwner(Player player, Location loc);
	
	public Player getPlayer() {
		return p;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	
}
