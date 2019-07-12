package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fWorldGuardv7 extends protectionObj {
	
	public fWorldGuardv7(Plugin pl){
		super(pl);
	}

	public boolean canBuild(Player player, Location loc) {
		this.setRegions(0);
		if(getPlugin()==null){return true;}
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionQuery query = container.createQuery();
		return query.testState(BukkitAdapter.adapt(loc), WorldGuardPlugin.inst().wrapPlayer(player), Flags.BUILD);
	}
	
	private ProtectedRegion getRegion(Location loc) {
		com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(loc);
		com.sk89q.worldedit.world.World w = BukkitAdapter.adapt(loc.getWorld());
		ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().get(w).getApplicableRegions(location.toVector().toBlockPoint());
		if(set==null){return null;}
		ProtectedRegion region = set.getRegions().stream().findFirst().orElse(WorldGuard.getInstance().getPlatform().getRegionContainer().get(w).getRegion("__global__"));
		if(region==null){return null;}
		return region;
	}
	
	public boolean isOwner(Player player, Location loc) {
		this.setRegions(0);
		if(getPlugin()==null){return true;}
		ProtectedRegion region = getRegion(loc);
		if(region==null){return true;}
		this.setRegions(1);
		return region.isOwner(WorldGuardPlugin.inst().wrapPlayer(player));
	}
}
