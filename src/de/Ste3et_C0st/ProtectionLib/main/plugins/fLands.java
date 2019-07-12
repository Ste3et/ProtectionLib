package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.angeschossen.lands.api.enums.LandsAction;
import me.angeschossen.lands.api.landsaddons.LandsAddon;
import me.angeschossen.lands.api.objects.LandChunk;

public class fLands extends protectionObj{

	private LandsAddon addon;
	
	public fLands(Plugin plugin) {
		super(plugin);
		this.addon = new LandsAddon(ProtectionLib.getInstance(), true);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(this.addon == null) return true;
		LandChunk chunk = addon.getLandChunk(loc.getChunk());
		return chunk == null ? true : chunk.canAction(player.getUniqueId().toString(), LandsAction.BLOCK_PLACE);
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		if(this.addon == null) return true;
		LandChunk chunk = addon.getLandChunk(loc.getChunk());
		return chunk == null ? true : chunk.getOwnerUUID().equalsIgnoreCase(player.getUniqueId().toString());
	}
	
}
