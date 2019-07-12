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
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fRedProtect extends protectionObj {

	public fRedProtect(Plugin pl){
		super(pl);
	}
	
	
	
	@EventHandler
	private void onClear(DeleteRegionEvent e){
		Location loc1 = e.getRegion().getMaxLocation();
		Location loc2 = e.getRegion().getMinLocation();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		Region reg = RedProtect.get().getAPI().getRegion(loc);
		if(reg==null)return true;
		return reg.canBuild(player);
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		Region reg = RedProtect.get().getAPI().getRegion(loc);
		if(reg==null) return true;
		return reg.isLeader(player);
	}
}
