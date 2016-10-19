package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

import br.net.fabiozumbi12.RedProtect.RedProtect;
import br.net.fabiozumbi12.RedProtect.Region;
import br.net.fabiozumbi12.RedProtect.events.DeleteRegionEvent;

public class fRedProtect extends ProtectinObj {
	Player p;
	Location loc;
	
	public fRedProtect(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return canBuild(p);
	}
	
	public boolean isOwner(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return isOwner(p);
	}
	
	@EventHandler
	private void onClear(DeleteRegionEvent e){
		Location loc1 = e.getRegion().getMaxLocation();
		Location loc2 = e.getRegion().getMinLocation();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	private boolean canBuild(Plugin p){
		if(p==null){return true;}
		Region reg = RedProtect.rm.getTopRegion(this.loc);
		if(reg==null)return true;
		return reg.canBuild(this.p);
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		Region reg = RedProtect.rm.getTopRegion(this.loc);
		if(reg==null) return true;
		return reg.isLeader(this.p);
	}
}
