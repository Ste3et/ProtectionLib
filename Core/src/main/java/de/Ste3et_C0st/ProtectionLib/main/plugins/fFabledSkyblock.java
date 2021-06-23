package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

//import com.songoda.skyblock.api.SkyBlockAPI;
//import com.songoda.skyblock.api.island.Island;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fFabledSkyblock extends protectionObj{

	public static final String pluginName = "FabledSkyblock";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fFabledSkyblock(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
//		Island island = SkyBlockAPI.getIslandManager().getIslandAtLocation(loc);
//		if(Objects.nonNull(island)) {
//			return Objects.nonNull(island.getOwnerUUID()) ? island.isCoopPlayer(player) : false;
//		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
//		Island island = SkyBlockAPI.getIslandManager().getIslandAtLocation(loc);
//		if(Objects.nonNull(island)) {
//			return Objects.nonNull(island.getOwnerUUID()) ? island.getOwnerUUID().equals(player.getUniqueId()) : false;
//		}
		return true;
	}

	public boolean isProtectedRegion(Location location) {
//		Island island = SkyBlockAPI.getIslandManager().getIslandAtLocation(location);
//		return Objects.nonNull(island);
		return false;
	}
	
}
