package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import biz.princeps.landlord.api.LandLordAPI;
import biz.princeps.landlord.util.OwnedLand;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fLandLord extends protectionObj {

	public fLandLord(Plugin pl){
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
		OwnedLand land = LandLordAPI.getInstance().getLand(this.getLocation());
		if(land==null) return true;
		if(land.isOwner(this.getPlayer().getUniqueId())){return true;}
		WorldGuardPlugin wgp = WorldGuardPlugin.inst();
		if(land.getLand().isMember(wgp.wrapPlayer(this.getPlayer()))){return true;}
		return false;
	}
	
	private boolean isOwner(){
		setRegions(0);
		if(getPlugin()==null){return true;}
		OwnedLand land = LandLordAPI.getInstance().getLand(this.getLocation());
		if(land==null) return true;
		setRegions(1);
		return land.isOwner(this.getPlayer().getUniqueId());
	}
}
