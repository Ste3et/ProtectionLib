package de.Ste3et_C0st.ProtectionLib.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class fWorldGuard {

	Player p;
	Location loc;
	
	public fWorldGuard(Location loc, Player p) {
		this.p = p;
		this.loc = loc;
	}

	public boolean canBuild(Plugin p) {
		if(p==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) p;
		return wgp.canBuild(this.p, this.loc);
	}
	
	public boolean isOwner(Plugin p) {
		if(p==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) p;
        RegionManager regionManager = wgp.getRegionManager(this.p.getWorld());
        if(regionManager != null){
            ProtectedRegion check = new ProtectedCuboidRegion("check", BukkitUtil.toVector(this.loc.getBlock()),BukkitUtil.toVector(this.loc.getBlock()));
            List<ProtectedRegion> intersects = check.getIntersectingRegions(new ArrayList<ProtectedRegion>(regionManager.getRegions().values()));
            LocalPlayer player = wgp.wrapPlayer(this.p);
            for (ProtectedRegion intersect : intersects) {
            	return intersect.isOwner(player);
            }
        }
        return true;
	}

}
