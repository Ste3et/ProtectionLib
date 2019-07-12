package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.wiefferink.areashop.events.ask.UnrentingRegionEvent;
import me.wiefferink.areashop.regions.RentRegion;

public class fAreaShop extends protectionObj implements Listener{

	public fAreaShop(Plugin pl) {
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	public void onClear(UnrentingRegionEvent e){
		RentRegion r = e.getRegion();
		if(r == null) return;
		//test
		Location l1 = r.getMinimumPoint().toLocation(r.getWorld());
		Location l2 = r.getMaximumPoint().toLocation(r.getWorld());
		RegionClearEvent event = new RegionClearEvent(l1, l2);
		Bukkit.getPluginManager().callEvent(event);
	}

	public boolean canBuild(Player player, Location loc) {
		return true;
	}
	
	public boolean isOwner(Player player, Location loc){
		return true;
	}
	
}
