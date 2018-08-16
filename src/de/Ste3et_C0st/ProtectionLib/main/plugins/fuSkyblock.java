package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;
import us.talabrek.ultimateskyblock.api.IslandInfo;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

public class fuSkyblock extends ProtectinObj {

	public fuSkyblock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		this.setPlayer(player);
		this.setLocation(loc);
		return canBuild();
	}
	
	public boolean isOwner(Player player, Location loc){
		this.setPlayer(player);
		this.setLocation(loc);
		return isOwner();
	}
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		uSkyBlockAPI api = (uSkyBlockAPI) getPlugin();
		IslandInfo info = api.getIslandInfo(getLocation());
		if(info == null) return true;
		if(info.isLeader(this.getPlayer())) return true;
		if(info.getMembers().contains(this.getPlayer().getName()) || info.getTrustees().contains(this.getPlayer().getName())) return true;
		return false;
	}
	
	private boolean isOwner(){
		if(getPlayer()==null){return true;}
		uSkyBlockAPI api = (uSkyBlockAPI) getPlugin();
		IslandInfo info = api.getIslandInfo(getLocation());
		if(info == null) return true;
		if(info.isLeader(this.getPlayer())) return true;
		return false;
	}
}
