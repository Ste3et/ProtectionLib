package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.api.ILocation;
import com.worldcretornica.plotme_core.bukkit.api.BukkitLocation;
import com.worldcretornica.plotme_core.bukkit.event.PlotResetEvent;

public class fPlotMe extends ProtectinObj {

	public fPlotMe(Plugin pl){
		super(pl);
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
	
	@EventHandler
	private void onClear(PlotResetEvent e){
		Location loc1 = e.getLowerBound();
		Location loc2 = e.getUpperBound();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
//		if(PlotManager.isPlotWorld(loc)){
//			Plot plot = PlotManager.getPlotById(loc);
//			if(plot==null) return true;
//			if(plot.isAllowed(this.p.getName())){
//				return true;
//			}else{
//				return false;
//			}
//		}else{
//			return true;
//		}
		PlotMeCoreManager manager = PlotMeCoreManager.getInstance();
		ILocation loc = new BukkitLocation(this.getLocation());
		if(!manager.isPlotWorld(loc)){return true;}
		String id = manager.getPlotId(loc);
		if(id==null) return false;
		Plot plot = manager.getPlotById(id, loc.getWorld());
		if(plot==null) return false;
		if(plot.isAllowed(this.getPlayer().getUniqueId())) return true;
		if(plot.getOwnerId().equals(this.getPlayer().getUniqueId())) return true;
		return false;
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
//		if(PlotManager.isPlotWorld(loc)){
//			Plot plot = PlotManager.getPlotById(loc);
//			if(plot==null) return true;
//			if(plot.getOwner().equalsIgnoreCase(this.p.getName())){
//				return true;
//			}else{
//				return false;
//			}
//		}else{
//			return true;
//		}
//		
		PlotMeCoreManager manager = PlotMeCoreManager.getInstance();
		ILocation loc = new BukkitLocation(this.getLocation());
		if(!manager.isPlotWorld(loc)){return true;}
		String id = manager.getPlotId(loc);
		if(id==null) return false;
		Plot plot = manager.getPlotById(id, loc.getWorld());
		if(plot==null) return false;
		if(plot.getOwnerId().equals(this.getPlayer().getUniqueId())) return true;
		return false;
	}
}
