package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.type.Flags;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandWorld;

public class fLands extends protectionObj{

	private final LandsIntegration landsAddon;
	
	public fLands(Plugin plugin) {
		super(plugin);
		landsAddon = LandsIntegration.of(ProtectionLib.getInstance());
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		final LandWorld world = landsAddon.getWorld(loc.getWorld());
		return Objects.isNull(world) ? true : world.hasRoleFlag(player, loc,  Flags.BLOCK_PLACE, Material.STONE, isEnabled());
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		Land chunk = landsAddon.getLandByChunk(loc.getWorld(), loc.getBlockX() >> 4, loc.getBlockZ() >> 4);
		return Objects.isNull(chunk) ? true : chunk.getOwnerUID().equals(player.getUniqueId());
	}
	
	public boolean isProtectedRegion(Location location) {
		Land chunk = landsAddon.getLandByChunk(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4);
		return Objects.nonNull(chunk);
	}
}
