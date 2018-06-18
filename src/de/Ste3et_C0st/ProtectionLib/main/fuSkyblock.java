package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.handler.WorldGuardHandler;
import us.talabrek.ultimateskyblock.island.IslandInfo;

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
		if(!uSkyBlock.getInstance().isSkyWorld(this.getLocation().getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.getLocation())) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.getLocation()));
		if(island.isLeader(this.getPlayer())) return true;
		if(island.getMembers().contains(this.getPlayer().getName()) || island.getTrustees().contains(this.getPlayer().getName())) return true;
		return false;
	}
	
	private boolean isOwner(){
		if(getPlayer()==null){return true;}
		if(!uSkyBlock.getInstance().isSkyWorld(this.getLocation().getWorld())) return true;
		if(!uSkyBlock.getInstance().islandAtLocation(this.getLocation())) return true;
		IslandInfo island = uSkyBlock.getInstance().getIslandInfo(WorldGuardHandler.getIslandNameAt(this.getLocation()));
		if(island.isLeader(this.getPlayer())) return true;
		return false;
	}
}
