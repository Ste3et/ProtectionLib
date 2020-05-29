package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.api.island.Island;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fFabledSkyblock extends protectionObj{

	public fFabledSkyblock(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		Island island = SkyBlockAPI.getIslandManager().getIslandAtLocation(loc);
		if(Objects.nonNull(island)) {
			return Objects.nonNull(island.getOwnerUUID()) ? island.isCoopPlayer(player) : false;
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		Island island = SkyBlockAPI.getIslandManager().getIslandAtLocation(loc);
		if(Objects.nonNull(island)) {
			return Objects.nonNull(island.getOwnerUUID()) ? island.getOwnerUUID().equals(player.getUniqueId()) : false;
		}
		return true;
	}

	
	
}
