package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.land.location.SimpleChunkLocation;
import org.kingdoms.events.invasion.KingdomInvadeEndEvent;
import org.kingdoms.events.lands.UnclaimLandEvent;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fKingdoms extends ProtectionConfig {
	
	public static final String pluginName = "Kingdoms";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fKingdoms(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	private void onWin(KingdomInvadeEndEvent event){
		if(getObject("InvasionChangeOwner") == false) return;
		Land land = event.getInvasion().getDefender();
		if(land == null) return;
		if(event.hasSucceeded()) {
			SimpleChunkLocation chunckLoc = land.getLocation();
			int xMin = chunckLoc.getX() * 16;
			int xMax = xMin + 16;
			
			int zMin = chunckLoc.getZ() * 16;
			int zMax = zMin + 16;
			
			int yMin = 0;
			int yMax = 256;
			
			Location loc1 = new Location(chunckLoc.getBukkitWorld(), xMin, yMin, zMin);
			Location loc2 = new Location(chunckLoc.getBukkitWorld(), xMax, yMax, zMax);
			
			RegionClearEvent regionEvent = new RegionClearEvent(loc1, loc2);
			regionEvent.setClear(false);
			regionEvent.setUUID(event.getInvasion().getInvader().getId());
			Bukkit.getPluginManager().callEvent(regionEvent);
		}
	}
	
	@EventHandler
	private void onWin(UnclaimLandEvent event){
		if(getObject("RegionClearEvent") == false) return;
		
		Land land = event.getLand();
		if(land == null) return;
		SimpleChunkLocation chunckLoc = land.getLocation();
		int xMin = chunckLoc.getX() * 16;
		int xMax = xMin + 16;
		
		int zMin = chunckLoc.getZ() * 16;
		int zMax = zMin + 16;
		
		int yMin = 0;
		int yMax = 256;
		
		Location loc1 = new Location(chunckLoc.getBukkitWorld(), xMin, yMin, zMin);
		Location loc2 = new Location(chunckLoc.getBukkitWorld(), xMax, yMax, zMax);
		
		RegionClearEvent regionEvent = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(regionEvent);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		SimpleChunkLocation chunckLoc = SimpleChunkLocation.of(loc);
		Land land = chunckLoc.getLand();
		if(land == null){return true;}
		if(land.getKingdom().isMember(player)) return true;
		if(land.getKingdom().getKing().getId().equals(player.getUniqueId())) return true;
		return false;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		SimpleChunkLocation chunckLoc = SimpleChunkLocation.of(loc);
		Land land = chunckLoc.getLand();
		if(land == null){return true;}
		return land.getKingdom().getKing().getId().equals(player.getUniqueId());
	}
	
	public boolean isProtectedRegion(Location location) {
		SimpleChunkLocation chunckLoc = SimpleChunkLocation.of(location);
		Land land = chunckLoc.getLand();
		return Objects.nonNull(land);
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
		this.addDefault("InvasionChangeOwner", true);
	}
}
