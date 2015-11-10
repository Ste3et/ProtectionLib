package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.handler.WorldGuardHandler;
import us.talabrek.ultimateskyblock.island.IslandInfo;

public class fuSkyblock {

	Player p;
	Location loc;
	public fuSkyblock(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		if(!uSkyBlock.getInstance().isSkyWorld(this.loc.getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.loc)) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.loc));
		if(island.isLeader(this.p)) return true;
		if(island.getMembers().contains(this.p.getName()) || island.getTrustees().contains(this.p.getName())) return true;
		return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		if(!uSkyBlock.getInstance().isSkyWorld(this.loc.getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.loc)) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.loc));
		if(island.isLeader(this.p)) return true;
		return false;
	}
}
