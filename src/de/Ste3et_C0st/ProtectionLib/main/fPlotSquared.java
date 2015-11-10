package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;

public class fPlotSquared {

	Player p;
	Location loc;
	public fPlotSquared(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	@SuppressWarnings("deprecation")
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		PlotAPI pAPI = new PlotAPI(ProtectionLib.getInstance());
		if(pAPI.isPlotWorld(this.loc.getWorld())){
			Plot plot = pAPI.getPlot(this.loc);
			if(plot!=null){
				if(plot.isAdded(this.p.getUniqueId())) return true;
				if(plot.isOwner(this.p.getUniqueId())) return true;
			}
			return false;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		PlotAPI pAPI = new PlotAPI(ProtectionLib.getInstance());
		if(pAPI.isPlotWorld(this.loc.getWorld())){
			Plot plot = pAPI.getPlot(this.loc);
			if(plot!=null){
				return plot.isOwner(this.p.getUniqueId());
			}
		}
		return false;
	}
}
