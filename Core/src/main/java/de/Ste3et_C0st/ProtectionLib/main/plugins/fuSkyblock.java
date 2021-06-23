package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import us.talabrek.ultimateskyblock.api.IslandInfo;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class fuSkyblock extends protectionObj {

	public static final String pluginName = "uSkyblock";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	public fuSkyblock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		uSkyBlockAPI api = (uSkyBlockAPI) getPlugin();
		IslandInfo info = api.getIslandInfo(loc);
		if(info == null) return true;
		if(info.isLeader(player)) return true;
		if(info.getMembers().contains(player.getName()) || info.getTrustees().contains(player.getName())) return true;
		return false;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(player==null){return true;}
		uSkyBlockAPI api = (uSkyBlockAPI) getPlugin();
		IslandInfo info = api.getIslandInfo(loc);
		if(info == null) return true;
		if(info.isLeader(player)) return true;
		return false;
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		uSkyBlockAPI api = (uSkyBlockAPI) getPlugin();
		IslandInfo info = api.getIslandInfo(location);
		return Objects.nonNull(info);
	}
}
