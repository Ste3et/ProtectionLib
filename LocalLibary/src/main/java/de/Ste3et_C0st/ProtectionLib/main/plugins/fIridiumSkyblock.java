package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.managers.IslandManager;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fIridiumSkyblock extends protectionObj{

	public static final String pluginName = "IridiumSkyblock";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fIridiumSkyblock(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		
		Island island = IslandManager.getIslandViaLocation(loc);
		String uuid = player.getUniqueId().toString();
		if(Objects.nonNull(island)) {
			return island.members.stream().filter(str -> str.equalsIgnoreCase(uuid)).findFirst().isPresent();
		}
		return true;
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		Island island = IslandManager.getIslandViaLocation(loc);
		if(Objects.nonNull(island)) {
			return island.owner.equalsIgnoreCase(player.getUniqueId().toString());
		}
		return true;
	}
	
	public boolean isProtectedRegion(Location location) {
		Island island = IslandManager.getIslandViaLocation(location);
		return Objects.nonNull(island);
	}
	
}
