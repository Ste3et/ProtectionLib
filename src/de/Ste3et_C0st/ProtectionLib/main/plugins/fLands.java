package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.LandChunk;
import me.angeschossen.lands.api.role.enums.RoleSetting;

public class fLands extends protectionObj{

	private final LandsIntegration landsAddon;
	
	public fLands(Plugin plugin) {
		super(plugin);
		landsAddon = new LandsIntegration(ProtectionLib.getInstance(), false);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		LandChunk chunk = landsAddon.getLandChunk(loc);
		return Objects.isNull(this.landsAddon) ? true : chunk.canAction(player, RoleSetting.BLOCK_PLACE, false);
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		LandChunk chunk = landsAddon.getLandChunk(loc);
		return Objects.isNull(this.landsAddon) ? true : chunk.getOwnerUID().equals(player.getUniqueId());
	}
	
}
