package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.handler.WorldGuardHandler;
import us.talabrek.ultimateskyblock.island.IslandInfo;

public class fuSkyblock extends ProtectinObj {
	Player p;
	Location loc;
	
	public fuSkyblock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return canBuild(p);
	}
	
	public boolean isOwner(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return isOwner(p);
	}
	
	private boolean canBuild(Plugin p){
		if(p==null){return true;}
		if(!uSkyBlock.getInstance().isSkyWorld(this.loc.getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.loc)) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.loc));
		if(island.isLeader(this.p)) return true;
		if(island.getMembers().contains(this.p.getName()) || island.getTrustees().contains(this.p.getName())) return true;
		return false;
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		if(!uSkyBlock.getInstance().isSkyWorld(this.loc.getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.loc)) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.loc));
		if(island.isLeader(this.p)) return true;
		return false;
	}
}
