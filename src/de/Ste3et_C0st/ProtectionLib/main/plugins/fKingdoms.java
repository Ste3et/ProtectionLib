package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.land.SimpleChunkLocation;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.events.KingdomPlayerWonEvent;
import org.kingdoms.manager.game.GameManagement;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;

public class fKingdoms extends ProtectionConfig {
	
	public fKingdoms(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	private void onWin(KingdomPlayerWonEvent event){
		if(getObject("RegionClearEvent") == false) return;
		Land l = GameManagement.getLandManager().getOrLoadLand(event.getChunk());
		if(l!=null){
			SimpleChunkLocation c = l.getLoc();
			int xMin = c.getX() * 16;
			int xMax = xMin + 16;
			
			int zMin = c.getZ() * 16;
			int zMax = zMin + 16;
			
			int yMin = 0;
			int yMax = 256;
			
			Location loc1 = new Location(Bukkit.getWorld(event.getChunk().getWorld()), xMin, yMin, zMin);
			Location loc2 = new Location(Bukkit.getWorld(event.getChunk().getWorld()), xMax, yMax, zMax);
			
			RegionClearEvent regionEvent = new RegionClearEvent(loc1, loc2);
			regionEvent.setUUID(event.getChallenger().getUuid());
			regionEvent.setClear(false);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		SimpleChunkLocation chunckLoc = new SimpleChunkLocation(loc.getChunk());
		Land l = GameManagement.getLandManager().getOrLoadLand(chunckLoc);
		if(l == null){return true;}
		String owner = l.getOwner();
		if(owner == null){return true;}
		Kingdom k = GameManagement.getKingdomManager().getOrLoadKingdom(owner);
		if(k == null){return true;}
		KingdomPlayer kPlayer = GameManagement.getPlayerManager().getSession(player);
		if(kPlayer == null){return true;}
		if(k.isEnemyMember(kPlayer)){return true;}
		if(k.getKing().equals(player.getUniqueId())){return true;}
		return false;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		SimpleChunkLocation chunckLoc = new SimpleChunkLocation(loc.getChunk());
		Land l = GameManagement.getLandManager().getOrLoadLand(chunckLoc);
		if(l == null){return true;}
		String owner = l.getOwner();
		if(owner == null){return true;}
		Kingdom k = GameManagement.getKingdomManager().getOrLoadKingdom(owner);
		if(k == null){return true;}
		return k.getKing().equals(player.getUniqueId());
	}
	
	public boolean isProtectedRegion(Location location) {
		SimpleChunkLocation chunckLoc = new SimpleChunkLocation(location.getChunk());
		Land land = GameManagement.getLandManager().getOrLoadLand(chunckLoc);
		return Objects.nonNull(land);
	}
	
	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
	
}
