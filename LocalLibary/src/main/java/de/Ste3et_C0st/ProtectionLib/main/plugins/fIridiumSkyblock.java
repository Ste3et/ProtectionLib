package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fIridiumSkyblock extends protectionObj{
	
	public fIridiumSkyblock(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(loc);
		String uuid = player.getUniqueId().toString();
		if(island.isPresent()) {
			return island.get().getMembers().stream().filter(str -> str.getUuid().toString().equalsIgnoreCase(uuid)).findFirst().isPresent();
		}
		return true;
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(loc);
		if(island.isPresent()) {
			return island.get().getOwner().getUuid().toString().equalsIgnoreCase(player.getUniqueId().toString());
		}
		return true;
	}
	
	public boolean isProtectedRegion(Location location) {
		Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
		return island.isPresent();
	}
	
}
