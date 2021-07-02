package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fWorldGuardV6 extends protectionObj{

	public static final String pluginName = "WorldGuard";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getVersion().startsWith("6");
	};
	
	public fWorldGuardV6(Plugin plugin) {
		super(plugin);
	}

	public boolean canBuild(Player player, Location loc) {
		if(getPlugin()==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		boolean b = true;
		World world = loc.getWorld();
		if(wgp.getRegionManager(world).getRegion("__global__") != null) {
			RegionManager regionManager = wgp.getRegionManager(world);
			ProtectedRegion r = regionManager.getRegion(ProtectedRegion.GLOBAL_REGION);
			ApplicableRegionSet set = wgp.getRegionManager(world).getApplicableRegions(r);
			if(set == null) return b;
			LocalPlayer localPlayer = wgp.wrapPlayer(player);
			if(set.getFlag(DefaultFlag.BUILD, localPlayer) == null) return b;
			if(set.getFlag(DefaultFlag.BUILD, localPlayer) == State.DENY) {
				b = false;
			}
		}
		if(!b && wgp.canBuild(player, loc)) {
			return true;
		}else if(!b) {
			return false;
		}
		return wgp.canBuild(player, loc);
	}
	
	public boolean isOwner(Player player, Location loc) {
		if(getPlugin()==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		World world = loc.getWorld();
        RegionManager regionManager = wgp.getRegionManager(world);
        if(regionManager != null){
            ProtectedRegion check = new ProtectedCuboidRegion("check", BukkitUtil.toVector(loc.getBlock()),BukkitUtil.toVector(loc.getBlock()));
            List<ProtectedRegion> intersects = check.getIntersectingRegions(new ArrayList<ProtectedRegion>(regionManager.getRegions().values()));
            LocalPlayer localPlayer = wgp.wrapPlayer(player);
            for (ProtectedRegion intersect : intersects) {
            	return intersect.isOwner(localPlayer);
            }
        }
        return true;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		if(getPlugin()==null){return true;}
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		World world = loc.getWorld();
        RegionManager regionManager = wgp.getRegionManager(world);
		return regionManager.getApplicableRegions(loc).size() > 0;
	}

	
}
