package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;
import pl.islandworld.IslandWorld;
import pl.islandworld.api.IslandWorldApi;
import pl.islandworld.entity.SimpleIsland;

public class fIslandWorld extends ProtectinObj{

	public fIslandWorld(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		this.setPlayer(player);
		this.setLocation(loc);
		return canBuild();
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		this.setPlayer(player);
		this.setLocation(loc);
		return isOwner();
	}
	
	private boolean canBuild() {
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(getLocation().getWorld())) return true;
		return IslandWorldApi.canBuildOnLocation(getPlayer(), getLocation(), true);
	}

	private boolean isOwner() {
		if(getPlugin() == null) return true;
		if(!IslandWorldApi.getIslandWorld().equals(getLocation().getWorld())) return true;
		IslandWorld world = IslandWorld.getInstance();
		SimpleIsland simpleIsland = world.xgetIslandOnLoc(getLocation());
		if(simpleIsland == null) return true;
		return simpleIsland.getOwnerUUID().equals(getPlayer().getUniqueId());
	}

}
