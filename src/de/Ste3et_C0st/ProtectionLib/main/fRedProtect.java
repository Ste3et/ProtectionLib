package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

import br.net.fabiozumbi12.RedProtect.RedProtect;
import br.net.fabiozumbi12.RedProtect.Region;
import br.net.fabiozumbi12.RedProtect.events.DeleteRegionEvent;

public class fRedProtect {

	Player p;
	Location loc;
	public fRedProtect(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	@EventHandler
	private void onClear(DeleteRegionEvent e){
		Location loc1 = e.getRegion().getMaxLocation();
		Location loc2 = e.getRegion().getMinLocation();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		Region reg = RedProtect.rm.getTopRegion(this.loc);
		if(reg==null)return true;
		return reg.canBuild(this.p);
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		Region reg = RedProtect.rm.getTopRegion(this.loc);
		if(reg==null) return true;
		return reg.isLeader(this.p);
	}
}
