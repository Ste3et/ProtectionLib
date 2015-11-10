package de.Ste3et_C0st.ProtectionLib.main;

import me.kyle.plotz.api.Plotz;
import me.kyle.plotz.obj.Plot;
import me.kyle.plotz.obj.PlotMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class fPlotz {

	Player p;
	Location loc;
	public fPlotz(Location loc, Player p){
		this.p = p;
		this.loc = loc;
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
