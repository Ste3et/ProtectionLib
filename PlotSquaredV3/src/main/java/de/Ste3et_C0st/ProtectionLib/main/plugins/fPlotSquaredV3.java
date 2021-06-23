package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.events.PlotClearEvent;
import com.plotsquared.bukkit.events.PlotDeleteEvent;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fPlotSquaredV3 extends ProtectionConfig implements Listener{

	public static final String pluginName = "PlotSquared";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getVersion().startsWith("3");
	};
	
	public fPlotSquaredV3(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	private void onClear(PlotClearEvent e){
		if(getObject("RegionClearEvent") == false) return;
		World world = Bukkit.getWorld(e.getWorld());
		Location sqloc1 = e.getPlot().getAllCorners().get(0);
		Location  sqloc2 = e.getPlot().getAllCorners().get(2);
		org.bukkit.Location loc1 = new org.bukkit.Location(world, sqloc1.getX(), sqloc1.getY(), sqloc1.getZ());
		org.bukkit.Location  loc2 = new org.bukkit.Location(world, sqloc2.getX(), sqloc2.getY(), sqloc2.getZ());
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@EventHandler
	private void onClear(PlotDeleteEvent e){
		if(getObject("RegionClearEvent") == false) return;
		World world = Bukkit.getWorld(e.getWorld());
		Location sqloc1 = e.getPlot().getAllCorners().get(0);
		Location  sqloc2 = e.getPlot().getAllCorners().get(2);
		org.bukkit.Location loc1 = new org.bukkit.Location(world, sqloc1.getX(), sqloc1.getY(), sqloc1.getZ());
		org.bukkit.Location  loc2 = new org.bukkit.Location(world, sqloc2.getX(), sqloc2.getY(), sqloc2.getZ());
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	
	
	public boolean canBuild(Player player, org.bukkit.Location loc){
		if(getPlugin()==null){return true;}
		Location plotLocation = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		Plot plot = plotLocation.getPlot();
		
		if(Objects.nonNull(plot)) {
			if(plot.isAdded(player.getUniqueId())) return true;
			if(plot.isOwner(player.getUniqueId())) return true;
			return false;
		}else if(plotLocation.isPlotRoad()) {
			return false;
		}
		return true;
	}
	
	public boolean isOwner(Player player, org.bukkit.Location loc){
		if(getPlugin()==null){return true;}
		Location plotLocation = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		Plot plot = plotLocation.getPlot();
		
		if(Objects.nonNull(plot)) {
			if(plot.isOwner(player.getUniqueId())) return true;
			return false;
		}else if(plotLocation.isPlotRoad()) {
			return false;
		}
		return true;
	}
	
	public boolean isProtectedRegion(org.bukkit.Location loc) {
		if(getPlugin()==null){return true;}
		Location plotLocation = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		return Objects.nonNull(plotLocation.getPlot());
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
