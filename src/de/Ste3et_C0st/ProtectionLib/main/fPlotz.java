package de.Ste3et_C0st.ProtectionLib.main;

import me.kyle.plotz.api.Plotz;
import me.kyle.plotz.api.events.PlotResetEvent;
import me.kyle.plotz.obj.Plot;
import me.kyle.plotz.obj.PlotMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class fPlotz extends ProtectinObj implements Listener {

	public fPlotz(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	
	@EventHandler
	private void onClear(PlotResetEvent e){
		Location loc1 = e.getPlot().getBottomLeft();
		Location loc2 = e.getPlot().getBottomRight();
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
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		if(!PlotMap.isPlotWorld(getLocation().getWorld())){return true;}
		if(Plotz.isOnPlot(this.getLocation())){
			Plot plot = Plotz.getPlotByLocation(this.getLocation());
			if(plot == null){return false;}
			return plot.isAllowed(this.getPlayer());
		}
		return false;
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		if(!PlotMap.isPlotWorld(getLocation().getWorld())){return true;}
		if(Plotz.isOnPlot(this.getLocation())){
			Plot plot = Plotz.getPlotByLocation(this.getLocation());
			if(plot == null){return false;}
			return plot.isOwner(this.getPlayer());
		}
		return false;
	}
	
}
