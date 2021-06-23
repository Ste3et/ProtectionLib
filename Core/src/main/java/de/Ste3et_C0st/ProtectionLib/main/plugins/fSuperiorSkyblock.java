package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fSuperiorSkyblock extends protectionObj{
	
	public static final String pluginName = "SuperiorSkyblock";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fSuperiorSkyblock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		Island i = SuperiorSkyblockAPI.getSuperiorSkyblock().getGrid().getIslandAt(loc);
		if(i == null) return true;
		return i.isMember(SuperiorSkyblockAPI.getPlayer(player));
	}
	
	public boolean isOwner(Player player, Location loc){
		Island i = SuperiorSkyblockAPI.getSuperiorSkyblock().getGrid().getIslandAt(loc);
		if(i == null) return true;
		return i.getOwner().equals(SuperiorSkyblockAPI.getPlayer(player));
	}
	
	public boolean isProtectedRegion(Location location) {
		Island island = SuperiorSkyblockAPI.getSuperiorSkyblock().getGrid().getIslandAt(location);
		return Objects.nonNull(island);
	}
}
