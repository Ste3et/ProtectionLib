package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import br.net.fabiozumbi12.RedProtect.Bukkit.events.DeleteRegionEvent;
import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fRedProtect extends ProtectinObj {

	public fRedProtect(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		this.setPlayer(player);
		this.setLocation(loc);
		return canBuild();
	}
	
	public boolean isOwner(Player player, Location loc){
		this.setPlayer(player);
		this.setLocation(loc);
		return isOwner();
	}
	
	
	
	@EventHandler
	private void onClear(DeleteRegionEvent e){
		Location loc1 = e.getRegion().getMaxLocation();
		Location loc2 = e.getRegion().getMinLocation();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		Region reg = RedProtect.get().getAPI().getRegion(this.getLocation());
		if(reg==null)return true;
		return reg.canBuild(this.getPlayer());
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		Region reg = RedProtect.get().getAPI().getRegion(this.getLocation());
		if(reg==null) return true;
		return reg.isLeader(this.getPlayer());
	}
}
