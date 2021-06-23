package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import me.wiefferink.areashop.events.ask.UnrentingRegionEvent;
import me.wiefferink.areashop.regions.RentRegion;

public class fAreaShop extends ProtectionConfig implements Listener{

	public static final String pluginName = "AreaShop";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fAreaShop(Plugin pl) {
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	public void onClear(UnrentingRegionEvent e){
		if(getObject("RegionClearEvent") == false) return;
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
	
	public boolean isProtectedRegion(Location location) {
		return false;
	}

	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
