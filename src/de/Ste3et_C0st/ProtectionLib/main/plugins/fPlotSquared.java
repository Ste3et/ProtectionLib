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
import com.plotsquared.bukkit.events.PlotClearEvent;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fPlotSquared extends ProtectinObj implements Listener{

	public fPlotSquared(Plugin pl){
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
	
	@SuppressWarnings("deprecation")
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		PlotAPI pAPI = new PlotAPI();
		if(pAPI.isPlotWorld(this.getLocation().getWorld())){
			com.intellectualcrafters.plot.object.Location loc = new com.intellectualcrafters.plot.object.Location(this.getLocation().getWorld().getName(), 
					(int) this.getLocation().getX(), 
					(int) this.getLocation().getY(), 
					(int) this.getLocation().getZ(), 
					this.getLocation().getYaw(), 
					this.getLocation().getPitch());
			if(loc.isPlotArea()){
				Plot plot = pAPI.getPlot(this.getLocation());
				if(plot!=null){
					if(plot.isAdded(this.getPlayer().getUniqueId())) return true;
					if(plot.isOwner(this.getPlayer().getUniqueId())) return true;
				}
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		PlotAPI pAPI = new PlotAPI();
		if(pAPI.isPlotWorld(this.getLocation().getWorld())){
			com.intellectualcrafters.plot.object.Location loc = new com.intellectualcrafters.plot.object.Location(this.getLocation().getWorld().getName(), 
					(int) this.getLocation().getX(), 
					(int) this.getLocation().getY(), 
					(int) this.getLocation().getZ(), 
					this.getLocation().getYaw(), 
					this.getLocation().getPitch());
			if(loc.isPlotArea()){
				Plot plot = pAPI.getPlot(this.getLocation());
				if(plot!=null) if(plot.isOwner(this.getPlayer().getUniqueId())) return true;
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
