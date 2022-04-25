package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.User;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.event.RemoveClaimEvent;
import com.griefdefender.lib.flowpowered.math.vector.Vector3i;
import com.griefdefender.lib.kyori.event.EventSubscriber;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefdefenderAPI extends ProtectionConfig {

	public fGriefdefenderAPI(Plugin plugin) {
		super(plugin);
		new RemoveClaimEventListener();
	}

	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		final Claim claim = GriefDefender.getCore().getClaimAt(loc);
		if(Objects.nonNull(claim) == true) {
			 final User user = GriefDefender.getCore().getUser(player.getUniqueId());
			 return claim.canBreak(player, loc, user);
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		final Claim claim = GriefDefender.getCore().getClaimAt(loc);
		if(Objects.nonNull(claim) == true) {
			 return claim.getOwnerUniqueId().equals(player.getUniqueId());
		}
		return true;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		final Claim claim = GriefDefender.getCore().getClaimAt(loc);
		return claim != null && !claim.isWilderness();
	}

	private class RemoveClaimEventListener {

		public RemoveClaimEventListener() {
			GriefDefender.getEventManager().getBus().subscribe(RemoveClaimEvent.class, new EventSubscriber<RemoveClaimEvent>() {
				@Override
				public void on(RemoveClaimEvent event) throws Throwable {
					if(getObject("RegionClearEvent") == false) return;
					final World world = Bukkit.getWorld(event.getClaim().getWorldUniqueId());
						
					final Vector3i min = event.getClaim().getLesserBoundaryCorner();
					final Vector3i max = event.getClaim().getGreaterBoundaryCorner();
						
					final Location locationMin = new Location(world, min.getX(), min.getY(), min.getZ());
					final Location locationMax = new Location(world, max.getX(), max.getY(), max.getZ());
					RegionClearEvent libEvent = new RegionClearEvent(locationMin, locationMax);
					Bukkit.getPluginManager().callEvent(libEvent);
				}
			});
		}
	}
}
