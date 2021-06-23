package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fPlotSquaredLegacy extends ProtectionConfig implements Listener{

	public static final String pluginName = "PlotSquared";
	
	public fPlotSquaredLegacy(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	private void onClear(PlotClearEvent e){
		if(getObject("RegionClearEvent") == false) return;
		World world = Bukkit.getWorld(e.getWorld());
		com.intellectualcrafters.plot.object.Location sqloc1 = e.getPlot().getAllCorners().get(0);
		com.intellectualcrafters.plot.object.Location  sqloc2 = e.getPlot().getAllCorners().get(2);
		Location loc1 = new Location(world, sqloc1.getX(), sqloc1.getY(), sqloc1.getZ());
		Location  loc2 = new Location(world, sqloc2.getX(), sqloc2.getY(), sqloc2.getZ());
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@EventHandler
	private void onClear(PlotDeleteEvent e){
		if(getObject("RegionClearEvent") == false) return;
		World world = Bukkit.getWorld(e.getWorld());
		com.intellectualcrafters.plot.object.Location sqloc1 = e.getPlot().getAllCorners().get(0);
		com.intellectualcrafters.plot.object.Location  sqloc2 = e.getPlot().getAllCorners().get(2);
		Location loc1 = new Location(world, sqloc1.getX(), sqloc1.getY(), sqloc1.getZ());
		Location  loc2 = new Location(world, sqloc2.getX(), sqloc2.getY(), sqloc2.getZ());
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	
	
	@SuppressWarnings("deprecation")
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		PlotAPI pAPI = new PlotAPI();
		if(pAPI.isPlotWorld(loc.getWorld())){
			PlotPlayer plotPlayer = PlotPlayer.wrap(player);
			if(plotPlayer == null) return false;
			com.intellectualcrafters.plot.object.Location plotLoc = new com.intellectualcrafters.plot.object.Location(loc.getWorld().getName(), 
					(int) loc.getX(), 
					(int) loc.getY(), 
					(int) loc.getZ(), 
					loc.getYaw(), 
					loc.getPitch());
			if(plotLoc.isPlotArea()){
				Plot plot = plotLoc.getPlot();
				if(plot!=null){
					if(plot.isOwner(plotPlayer.getUUID())) return true;
					if(plot.isAdded(plotPlayer.getUUID())) return true;
				}
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		PlotAPI pAPI = new PlotAPI();
		if(pAPI.isPlotWorld(loc.getWorld())){
			PlotPlayer plotPlayer = PlotPlayer.wrap(player);
			if(plotPlayer == null) return false;
			com.intellectualcrafters.plot.object.Location plotLoc = new com.intellectualcrafters.plot.object.Location(loc.getWorld().getName(), 
					(int) loc.getX(), 
					(int) loc.getY(), 
					(int) loc.getZ(), 
					loc.getYaw(), 
					loc.getPitch());
			if(plotLoc.isPlotArea()){
				Plot plot = plotLoc.getPlot();
				if(plot!=null) {
					return plot.isOwner(plotPlayer.getUUID());
				}
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		PlotAPI pAPI = new PlotAPI();
		if(pAPI.isPlotWorld(location.getWorld())){
			com.intellectualcrafters.plot.object.Location plotLoc = new com.intellectualcrafters.plot.object.Location(location.getWorld().getName(), 
					(int) location.getX(), 
					(int) location.getY(), 
					(int) location.getZ(), 
					location.getYaw(), 
					location.getPitch());
			if(plotLoc.isPlotArea()){
				Plot plot = plotLoc.getPlot();
				return Objects.nonNull(plot);
			}
		}
		return false;
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}

	@Override
	public boolean shouldActivate(PluginDescriptionFile descriptionFile) {
		return descriptionFile.getName().equalsIgnoreCase(getPluginName()) && descriptionFile.getVersion().startsWith("4");
	}

	@Override
	public String getPluginName() {
		return "PlotSquared";
	}
}
