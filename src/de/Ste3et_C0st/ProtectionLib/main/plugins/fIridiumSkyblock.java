package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fIridiumSkyblock extends protectionObj{

	public fIridiumSkyblock(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(loc);
		if(Objects.nonNull(island)) {
			return island.getMembers().stream().filter(str -> str.equalsIgnoreCase(player.getName())).findFirst().isPresent();
		}
		return true;
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(loc);
		if(Objects.nonNull(island)) {
			return island.getOwner().equalsIgnoreCase(player.getName());
		}
		return true;
	}
	
}
