package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import br.net.fabiozumbi12.RedProtect.Bukkit.API.events.DeleteRegionEvent;
import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;

public class fRedProtect extends ProtectionConfig {
	
	public fRedProtect(Plugin pl){
		super(pl);
	}
	
	@EventHandler
	private void onClear(DeleteRegionEvent e){
		if(getObject("RegionClearEvent") == false) return;
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
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		Region region = RedProtect.get().getAPI().getRegion(location);
		return Objects.nonNull(region);
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
