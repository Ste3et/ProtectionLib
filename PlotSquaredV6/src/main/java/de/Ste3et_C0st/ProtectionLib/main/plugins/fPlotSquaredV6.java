package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.events.PlotClearEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.util.RegionUtil;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import com.sk89q.worldedit.math.BlockVector3;

public class fPlotSquaredV6 extends ProtectionConfig{

	public fPlotSquaredV6(Plugin pl) {
		super(pl);
		PlotAPI plotAPI = new PlotAPI();
		plotAPI.registerListener(this);
	}
	
	@Subscribe
	public void onPlotClear(PlotClearEvent clearEvent) {
		if(getObject("RegionClearEvent") == false) return;
		Location[] locationList = RegionUtil.getCorners(clearEvent.getWorld(), clearEvent.getPlot().getRegions());
		Location plotLocMin = locationList[0];
		Location plotLocMax = locationList[1];
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(clearEvent.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
	
	@Subscribe
	public void onPlotDelete(PlotDeleteEvent clearEvent) {
		if(getObject("RegionClearEvent") == false) return;
		Location[] locationList = RegionUtil.getCorners(clearEvent.getWorld(), clearEvent.getPlot().getRegions());
		Location plotLocMin = locationList[0];
		Location plotLocMax = locationList[1];
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(clearEvent.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

	@Override
	public boolean canBuild(Player player, org.bukkit.Location loc) {
		Location location = Location.at(loc.getWorld().getName(), BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
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
		Location location = Location.at(loc.getWorld().getName(), BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
		if(location.isPlotArea()) {
			Plot plot = location.getPlot();
			if(Objects.nonNull(plot)) {
				return plot.isOwner(player.getUniqueId());
			}
			return false;
		}
		
		return true;
	}
	
	public boolean isProtectedRegion(org.bukkit.Location loc) {
		Location location = Location.at(loc.getWorld().getName(), BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
		if(location.isPlotArea()) {
			Plot plot = location.getPlot();
			return Objects.nonNull(plot);
		}
		return false;
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
