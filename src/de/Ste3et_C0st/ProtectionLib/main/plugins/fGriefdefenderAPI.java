package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.flowpowered.math.vector.Vector3i;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.ClaimManager;
import com.griefdefender.api.event.RemoveClaimEvent;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefdefenderAPI extends ProtectionConfig implements Listener {

	public fGriefdefenderAPI(Plugin plugin) {
		super(plugin);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}

	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
	
	@EventHandler
	public void onPlotClear(RemoveClaimEvent removeClaimEvent) {
		if(getObject("RegionClearEvent") == false) return;
		if(removeClaimEvent.cancelled() == false) {
			World world = Bukkit.getWorld(removeClaimEvent.getClaim().getWorldUniqueId());
			
			Vector3i min = removeClaimEvent.getClaim().getLesserBoundaryCorner();
			Vector3i max = removeClaimEvent.getClaim().getGreaterBoundaryCorner();
			
			org.bukkit.Location locationMin = new org.bukkit.Location(world, min.getX(), min.getY(), min.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, max.getX(), max.getY(), max.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
			

	@Override
	public boolean canBuild(Player player, Location loc) {
		ClaimManager claimManager = GriefDefender.getCore().getClaimManager(loc.getWorld().getUID());
		if(Objects.isNull(claimManager)) return true; 
		Claim claim = claimManager.getClaimAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(Objects.nonNull(claim) == true) {
			 return claim.isTrusted(player.getUniqueId());
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		ClaimManager claimManager = GriefDefender.getCore().getClaimManager(loc.getWorld().getUID());
		if(Objects.isNull(claimManager)) return true; 
		Claim claim = claimManager.getClaimAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(Objects.nonNull(claim) == true) {
			 return claim.getOwnerUniqueId().equals(player.getUniqueId());
		}
		return true;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		ClaimManager claimManager = GriefDefender.getCore().getClaimManager(loc.getWorld().getUID());
		if(Objects.isNull(claimManager)) return true;
		return Objects.nonNull(claimManager.getClaimAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
	}

}
