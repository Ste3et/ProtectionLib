package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.bukkit.events.PlotClearEvent;
import com.plotsquared.bukkit.events.PlotDeleteEvent;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fPlotSquaredLegacy extends protectionObj implements Listener{

	public fPlotSquaredLegacy(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	private void onClear(PlotClearEvent e){
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
		this.setRegions(0);
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
}
