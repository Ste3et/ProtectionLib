package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import us.talabrek.ultimateskyblock.api.IslandInfo;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class fuSkyblock extends protectionObj {

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
}
