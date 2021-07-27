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
import com.griefdefender.api.Subject;
import com.griefdefender.api.Tristate;
import com.griefdefender.api.claim.TrustTypes;
import com.griefdefender.api.permission.flag.Flag;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefdefenderAPI extends ProtectionConfig implements Listener {
	private final Flag PROTECTIONLIB_BUILD_PROTECT;

	public fGriefdefenderAPI(Plugin plugin) {
		super(plugin);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());

        PROTECTIONLIB_BUILD_PROTECT = Flag.builder()
				.id("protectionlib:build_protect")
				.name("build-protect")
				.permission("griefdefender.flag.protectionlib.build-protect")
				.build();
        GriefDefender.getRegistry().getRegistryModuleFor(Flag.class).get().registerCustomType(PROTECTIONLIB_BUILD_PROTECT);
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
		final Subject subject = GriefDefender.getCore().getSubject(player.getUniqueId().toString());
		final Tristate useProtect = claim.getActiveFlagPermissionValue(PROTECTIONLIB_BUILD_PROTECT, subject, null, true);
		final Tristate canBuild = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(claim, subject,
				com.griefdefender.api.permission.flag.Flags.BLOCK_PLACE, player, player.getLocation().getBlock(), null, TrustTypes.BUILDER, true);
		if(!claim.isWilderness()) {
			 return useProtect == Tristate.FALSE || canBuild == Tristate.TRUE;
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		ClaimManager claimManager = GriefDefender.getCore().getClaimManager(loc.getWorld().getUID());
		if(Objects.isNull(claimManager)) return true;
		Claim claim = claimManager.getClaimAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(!claim.isWilderness()) {
			 return claim.getOwnerUniqueId().equals(player.getUniqueId());
		}
		return true;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		ClaimManager claimManager = GriefDefender.getCore().getClaimManager(loc.getWorld().getUID());
		if(Objects.isNull(claimManager)) return true;
		return !claimManager.getClaimAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).isWilderness();
	}

}
