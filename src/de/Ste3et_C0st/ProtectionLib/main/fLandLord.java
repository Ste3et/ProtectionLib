package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import biz.princeps.landlord.api.LandLordAPI;
import biz.princeps.landlord.util.OwnedLand;

public class fLandLord extends ProtectinObj {
	Player p;
	Location loc;
	
	public fLandLord(Plugin pl){
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
		OwnedLand land = LandLordAPI.getInstance().getLand(this.loc);
		if(land==null) return true;
		if(land.isOwner(this.p.getUniqueId())){return true;}
		WorldGuardPlugin wgp = WorldGuardPlugin.inst();
		if(land.getLand().isMember(wgp.wrapPlayer(this.p))){return true;}
		return false;
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		OwnedLand land = LandLordAPI.getInstance().getLand(this.loc);
		if(land==null) return true;
		if(land.isOwner(this.p.getUniqueId())){return true;}
		return false;
	}
}
