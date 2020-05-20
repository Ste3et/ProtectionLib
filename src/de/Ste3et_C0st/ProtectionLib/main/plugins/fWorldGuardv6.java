package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fWorldGuardv6 extends protectionObj {

	public fWorldGuardv6(Plugin pl){
		super(pl);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin()==null) return true;
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		return wgp.canBuild(player, loc);
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		if(getPlugin()==null) return true;
		WorldGuardPlugin wgp = (WorldGuardPlugin) getPlugin();
		RegionManager regionManager = wgp.getRegionManager(loc.getWorld());
		if(Objects.nonNull(regionManager)){
			LocalPlayer localPlayer = wgp.wrapPlayer(player);
			Set<ProtectedRegion> regionSet = regionManager.getApplicableRegions(loc).getRegions();
			if(regionSet.isEmpty()) {
				return regionSet.stream().filter(region -> region.isOwner(localPlayer)).findFirst().isPresent();
			}
		}
		return true;
	}
}
