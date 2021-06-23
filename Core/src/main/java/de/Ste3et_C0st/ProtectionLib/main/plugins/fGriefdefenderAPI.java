package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.flowpowered.math.vector.Vector3i;
import com.google.common.base.Predicate;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.ClaimManager;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefdefenderAPI extends ProtectionConfig implements Listener {

	public static final String pluginName = "GriefDefender";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fGriefdefenderAPI(Plugin plugin) {
		super(plugin);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}

	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
	
	@EventHandler
	public void onPlotClear(com.griefdefender.api.event.RemoveClaimEvent removeClaimEvent) {
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
