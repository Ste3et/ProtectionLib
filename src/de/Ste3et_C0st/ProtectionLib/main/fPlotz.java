package de.Ste3et_C0st.ProtectionLib.main;

import me.kyle.plotz.api.Plotz;
import me.kyle.plotz.api.events.PlotResetEvent;
import me.kyle.plotz.obj.Plot;
import me.kyle.plotz.obj.PlotMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

public class fPlotz {

	Player p;
	Location loc;
	public fPlotz(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	@EventHandler
	private void onClear(PlotResetEvent e){
		Location loc1 = e.getPlot().getBottomLeft();
		Location loc2 = e.getPlot().getBottomRight();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		if(!PlotMap.isPlotWorld(loc.getWorld())){return true;}
		if(Plotz.isOnPlot(this.loc)){
			Plot plot = Plotz.getPlotByLocation(this.loc);
			if(plot == null){return false;}
			return plot.isAllowed(this.p);
		}
		return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		if(!PlotMap.isPlotWorld(loc.getWorld())){return true;}
		if(Plotz.isOnPlot(this.loc)){
			Plot plot = Plotz.getPlotByLocation(this.loc);
			if(plot == null){return false;}
			return plot.isOwner(this.p);
		}
		return false;
	}
	
}
