package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Iterator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fWorldGuardV5 extends protectionObj{

	public static final String pluginName = "WorldGuard";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getVersion().startsWith("5");
	};
	
	public fWorldGuardV5(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin()==null) return true;
		return WorldGuardPlugin.inst().canBuild(player, loc);
	}
	@Override
	public boolean isOwner(Player player, Location loc) {
		if(getPlugin()==null) return true;
		
		World world = loc.getWorld();
		ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(world).getApplicableRegions(loc);
		
		if(set.size() > 0) {
			Iterator<ProtectedRegion> regionIterator = set.iterator();
			String playerName = player.getName();
			while(regionIterator.hasNext()) {
				ProtectedRegion region = regionIterator.next();
				if(region.isOwner(playerName)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	@Override
	public boolean isProtectedRegion(Location loc) {
		return WorldGuardPlugin.inst().getRegionManager(loc.getWorld()).getApplicableRegions(loc).size() > 0;
	}
	
	
	
}
