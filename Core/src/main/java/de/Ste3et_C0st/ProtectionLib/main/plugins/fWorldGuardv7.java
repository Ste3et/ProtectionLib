package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.Ste3et_C0st.ProtectionLib.main.protectionFlag;

public class fWorldGuardv7 extends protectionFlag<StateFlag> {
	
	public static final String pluginName = "WorldGuard";
	
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getAPIVersion().isEmpty() == false;
	};
	
	private static final String stateFlag = "Furniture-build";
	
	public fWorldGuardv7(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin()==null) return true;
		com.sk89q.worldedit.util.Location worldEditLocation = BukkitAdapter.adapt(loc);
		LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
		return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testBuild(worldEditLocation, localPlayer, buildFlagArray());
	}
	
	private ProtectedRegion getRegion(Location loc) {
		com.sk89q.worldedit.world.World w = BukkitAdapter.adapt(loc.getWorld());
		ApplicableRegionSet set = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(loc));
		if(set==null){return null;}
		ProtectedRegion region = set.getRegions().stream().sorted((k1,k2) -> Integer.compare(k1.getPriority(), k2.getPriority())).findFirst().orElse(WorldGuard.getInstance().getPlatform().getRegionContainer().get(w).getRegion("__global__"));
		if(region==null){return null;}
		return region;
	}
	
	public boolean isOwner(Player player, Location loc) {
		if(getPlugin()==null){return true;}
		ProtectedRegion region = getRegion(loc);
		if(region==null){return true;}
		if(region.getId().equalsIgnoreCase("__global__")) return true;
		return region.isOwner(WorldGuardPlugin.inst().wrapPlayer(player));
	}
	
	private StateFlag[] buildFlagArray() {
		List<StateFlag> flagList = getFlags();
		if(flagList.isEmpty()) {
			return new StateFlag[] { Flags.BUILD };
		}else {
			List<StateFlag> flags = Lists.newArrayList();
			flags.add(Flags.BUILD);
			flags.addAll(flagList);
			return flags.stream().toArray(StateFlag[]::new);
		}
	}
	
	@Override
	public Optional<StateFlag> registerFlag(Plugin plugin, String str, boolean b) {
		FlagRegistry flagRegister = WorldGuard.getInstance().getFlagRegistry();
		try {
	        StateFlag flag = new StateFlag(str, b);
	        flagRegister.register(flag);
	        info(str);
	        return Optional.of(flag);
	    } catch (FlagConflictException e) {
	        Flag<?> existing = flagRegister.get(str);
	        if (existing instanceof StateFlag) {
	        	return Optional.of(StateFlag.class.cast(existing));
	        }
	    }
		return Optional.empty();
	}
	
	public boolean isProtectedRegion(Location location) {
		return Objects.nonNull(getRegion(location));
	}

	@Override
	public void onLoad(Plugin plugin) {
		registerFlag(plugin, stateFlag, true);
	}

	@Override
	public List<StateFlag> getFlags() {
		List<StateFlag> flags = Lists.newArrayList();
		WorldGuard.getInstance().getFlagRegistry().getAll().stream().filter(entry -> entry.getName().equalsIgnoreCase(stateFlag)).map(StateFlag.class::cast).forEach(flags::add);
		return flags;
	}

	@Override
	public boolean queryFlag(String flagName, Player player, Location location) {
		com.sk89q.worldedit.util.Location worldEditLocation = BukkitAdapter.adapt(location);
		LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
		return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().testBuild(worldEditLocation, localPlayer, getFlags().stream().toArray(StateFlag[]::new));
	}
}
