package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fWorldGuardv7 extends protectionObj {
	
	public static List<StateFlag> flagList = new ArrayList<StateFlag>();
	
	
	public fWorldGuardv7(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin()==null) return true;
		return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testBuild(BukkitAdapter.adapt(loc),  WorldGuardPlugin.inst().wrapPlayer(player), new StateFlag[] { Flags.BUILD });
	}
	
	private ProtectedRegion getRegion(Location loc) {
		com.sk89q.worldedit.world.World w = BukkitAdapter.adapt(loc.getWorld());
		ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(loc));
		if(set==null){return null;}
		ProtectedRegion region = set.getRegions().stream().findFirst().orElse(WorldGuard.getInstance().getPlatform().getRegionContainer().get(w).getRegion("__global__"));
		if(region==null){return null;}
		
		return region;
	}
	
	public boolean isOwner(Player player, Location loc) {
		if(getPlugin()==null){return true;}
		ProtectedRegion region = getRegion(loc);
		if(region==null){return true;}
		this.setRegions(1);
		return region.isOwner(WorldGuardPlugin.inst().wrapPlayer(player));
	}
	
	public boolean registerFlag(Plugin plugin,String str) {
		return registerFlag(plugin, str, true);
	}
	
	@Override
	public boolean registerFlag(Plugin plugin,String str, boolean b) {
		FlagRegistry flagRegister = WorldGuard.getInstance().getFlagRegistry();
		try {
	        StateFlag flag = new StateFlag(str, b);
	        flagRegister.register(flag);
	        return flagList.add(flag);
	    } catch (FlagConflictException e) {
	        Flag<?> existing = flagRegister.get(str);
	        if (existing instanceof StateFlag) {
	            return flagList.add((StateFlag) existing);
	        }
	    }
		return true;
	}
	
	@Override
	public boolean queryFlag(String str, Player player, Location loc) {
		if(getPlugin()==null) return true;
		if(flagList.isEmpty()) return true;
		StateFlag flag = flagList.stream().filter(sFlag -> sFlag.getName().equalsIgnoreCase(str)).findFirst().orElse(null);
		if(Objects.isNull(flag)) {
			return true;
		}else {
			return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testBuild(BukkitAdapter.adapt(loc),  WorldGuardPlugin.inst().wrapPlayer(player), new StateFlag[] { flag });
		}
	}
}
