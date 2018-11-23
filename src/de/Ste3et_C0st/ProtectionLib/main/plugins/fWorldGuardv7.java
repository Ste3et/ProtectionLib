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
import com.sk89q.worldguard.protection.regions.RegionQuery;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fWorldGuardv7 extends ProtectinObj {
	
	public fWorldGuardv7(Plugin pl){
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

	private boolean canBuild() {
		if(getPlugin()==null){return true;}
		
		RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(getLocation());
        if (!hasBypass()) {
        	boolean b = query.testState(loc, WorldGuardPlugin.inst().wrapPlayer(getPlayer()), Flags.BUILD);
            return b;
        }else {
            return true;
        }
	}
	
	private boolean isOwner() {
		if(getPlugin()==null){return true;}
		com.sk89q.worldedit.util.Location location = BukkitAdapter.adapt(getLocation());
		com.sk89q.worldedit.world.World w = BukkitAdapter.adapt(getLocation().getWorld());
		ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().get(w).getApplicableRegions(location.getDirection().toBlockPoint());
		if(set==null){return true;}
		ProtectedRegion region = set.getRegions().stream().findFirst().orElse(null);
		if(region==null){return true;}
		return region.isOwner(WorldGuardPlugin.inst().wrapPlayer(getPlayer()));
	}

    public boolean hasBypass() {
        return WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(WorldGuardPlugin.inst().wrapPlayer(getPlayer()), BukkitAdapter.adapt(getLocation().getWorld()));
    }
}
