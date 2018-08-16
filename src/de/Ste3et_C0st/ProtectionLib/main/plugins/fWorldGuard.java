package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fWorldGuard extends ProtectinObj {
	
	public fWorldGuard(Plugin pl){
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

	@SuppressWarnings("deprecation")
	private boolean canBuild() {
		if(getPlugin()==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		boolean b = true;
		if(wgp.getRegionManager(getLocation().getWorld()).getRegion("__global__") != null) {
			RegionManager regionManager = wgp.getRegionManager(getLocation().getWorld());
			ProtectedRegion r = regionManager.getRegion(ProtectedRegion.GLOBAL_REGION);
			ApplicableRegionSet set = wgp.getRegionManager(getLocation().getWorld()).getApplicableRegions(r);
			if(set == null) return b;
			LocalPlayer player = wgp.wrapPlayer(this.getPlayer());
			if(set.getFlag(DefaultFlag.BUILD, player) == null) return b;
			if(set.getFlag(DefaultFlag.BUILD, player) == State.DENY) {
				b = false;
			}
		}
		if(!b && wgp.canBuild(this.getPlayer(), this.getLocation())) {
			return true;
		}else if(!b) {
			return false;
		}
		return wgp.canBuild(this.getPlayer(), this.getLocation());
	}
	
	private boolean isOwner() {
		if(getPlugin()==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
        RegionManager regionManager = wgp.getRegionManager(this.getPlayer().getWorld());
        if(regionManager != null){
            ProtectedRegion check = new ProtectedCuboidRegion("check", BukkitUtil.toVector(this.getLocation().getBlock()),BukkitUtil.toVector(this.getLocation().getBlock()));
            List<ProtectedRegion> intersects = check.getIntersectingRegions(new ArrayList<ProtectedRegion>(regionManager.getRegions().values()));
            LocalPlayer player = wgp.wrapPlayer(this.getPlayer());
            for (ProtectedRegion intersect : intersects) {
            	return intersect.isOwner(player);
            }
        }
        return true;
	}

}
