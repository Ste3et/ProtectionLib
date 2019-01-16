package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.github.intellectualsites.plotsquared.bukkit.events.PlotClearEvent;
import com.github.intellectualsites.plotsquared.plot.PlotSquared;
import com.github.intellectualsites.plotsquared.plot.object.Plot;

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
		com.github.intellectualsites.plotsquared.plot.object.Location sqloc1 = e.getPlot().getAllCorners().get(0);
		com.github.intellectualsites.plotsquared.plot.object.Location  sqloc2 = e.getPlot().getAllCorners().get(2);
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
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		if(PlotSquared.get().hasPlotArea(getLocation().getWorld().getName())){
			com.github.intellectualsites.plotsquared.plot.object.Location loc = new com.github.intellectualsites.plotsquared.plot.object.Location(this.getLocation().getWorld().getName(), 
					(int) this.getLocation().getX(), 
					(int) this.getLocation().getY(), 
					(int) this.getLocation().getZ(), 
					this.getLocation().getYaw(), 
					this.getLocation().getPitch());
			if(loc.isPlotArea()) {
				Plot p = loc.getPlot();
				if(p != null) {
					if(p.isAdded(this.getPlayer().getUniqueId())) return true;
					if(p.isOwner(this.getPlayer().getUniqueId())) return true;
				}
				return false;
			}
			return true;
		}else {
			return true;
		}
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		if(PlotSquared.get().hasPlotArea(getLocation().getWorld().getName())){
			com.github.intellectualsites.plotsquared.plot.object.Location loc = new com.github.intellectualsites.plotsquared.plot.object.Location(this.getLocation().getWorld().getName(), 
					(int) this.getLocation().getX(), 
					(int) this.getLocation().getY(), 
					(int) this.getLocation().getZ(), 
					this.getLocation().getYaw(), 
					this.getLocation().getPitch());
			if(loc.isPlotArea()) {
				Plot p = loc.getPlot();
				if(p != null) {
					if(p.isOwner(this.getPlayer().getUniqueId())) return true;
				}
				return false;
			}
			return true;
		}else {
			return true;
		}
	}
}
