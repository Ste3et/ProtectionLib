package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import pl.islandworld.IslandWorld;
import pl.islandworld.api.IslandWorldApi;
import pl.islandworld.entity.SimpleIsland;

public class fIslandWorld extends protectionObj{

	public fIslandWorld(Plugin plugin) {
		super(plugin);
	}
	
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(loc.getWorld())) return true;
		return IslandWorldApi.canBuildOnLocation(player, loc, true);
	}

	public boolean isOwner(Player player, Location loc) {
		setRegions(0);
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(loc.getWorld())) return true;
		IslandWorld world = IslandWorld.getInstance();
		SimpleIsland simpleIsland = world.xgetIslandOnLoc(loc);
		if(simpleIsland == null) return true;
		setRegions(1);
		return simpleIsland.getOwnerUUID().equals(player.getUniqueId());
	}

}
