package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import pl.islandworld.IslandWorld;
import pl.islandworld.api.IslandWorldApi;
import pl.islandworld.entity.SimpleIsland;

public class fIslandWorld extends protectionObj{

	public static final String pluginName = "IslandWorld";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fIslandWorld(Plugin plugin) {
		super(plugin);
	}
	
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(loc.getWorld())) return true;
		return IslandWorldApi.canBuildOnLocation(player, loc, true);
	}

	public boolean isOwner(Player player, Location loc) {
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(loc.getWorld())) return true;
		IslandWorld world = IslandWorld.getInstance();
		SimpleIsland simpleIsland = world.xgetIslandOnLoc(loc);
		if(simpleIsland == null) return true;
		return simpleIsland.getOwnerUUID().equals(player.getUniqueId());
	}

	public boolean isProtectedRegion(Location location) {
		if(!IslandWorldApi.getIslandWorld().equals(location.getWorld())) return false;
		IslandWorld world = IslandWorld.getInstance();
		SimpleIsland simpleIsland = world.xgetIslandOnLoc(location);
		return Objects.nonNull(simpleIsland);
	}
}
