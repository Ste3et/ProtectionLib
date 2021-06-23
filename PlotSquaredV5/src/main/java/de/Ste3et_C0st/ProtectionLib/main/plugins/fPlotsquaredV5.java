package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.plotsquared.core.api.PlotAPI;
import com.plotsquared.core.events.PlotClearEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.util.eventbus.Subscribe;
import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;

public class fPlotsquaredV5 extends ProtectionConfig{

	public static final String pluginName = "PlotSquared";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getVersion().startsWith("5");
	};
	
	public fPlotsquaredV5(Plugin pl) {
		super(pl);
		PlotAPI plotAPI = new PlotAPI();
		plotAPI.registerListener(this);
	}
	
	@Subscribe
	public void onPlotClear(PlotClearEvent clearEvent) {
		if(getObject("RegionClearEvent") == false) return;
		List<Location> locationList = clearEvent.getPlot().getAllCorners();
		Location plotLocMin = locationList.get(0);
		Location plotLocMax = locationList.get(2);
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(plotLocMin.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
	
	@Subscribe
	public void onPlotDelete(PlotDeleteEvent clearEvent) {
		if(getObject("RegionClearEvent") == false) return;
		List<Location> locationList = clearEvent.getPlot().getAllCorners();
		Location plotLocMin = locationList.get(0);
		Location plotLocMax = locationList.get(2);
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(plotLocMin.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

	@Override
	public boolean canBuild(Player player, org.bukkit.Location loc) {
		
		Location location = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(location.isPlotArea()) {
			Plot plot = location.getPlot();
			UUID uuid = player.getUniqueId();
			if(Objects.nonNull(plot)) {
				return plot.isAdded(uuid) || plot.isOwner(uuid);
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, org.bukkit.Location loc) {
		Location location = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(location.isPlotArea()) {
			Plot plot = location.getPlot();
			if(Objects.nonNull(plot)) {
				return plot.isOwner(player.getUniqueId());
			}
			return false;
		}
		
		return true;
	}
	
	public boolean isProtectedRegion(org.bukkit.Location location) {
		Location plotLocation = new Location(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
		if(plotLocation.isPlotArea()) {
			Plot plot = plotLocation.getPlot();
			return Objects.nonNull(plot);
		}
		return false;
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
