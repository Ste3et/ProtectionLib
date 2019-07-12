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
		if(getPlugin()==null){return true;}
		OwnedLand land = LandLordAPI.getInstance().getLand(loc);
		if(land==null) return true;
		if(land.isOwner(player.getUniqueId())){return true;}
		WorldGuardPlugin wgp = WorldGuardPlugin.inst();
		if(land.getLand().isMember(wgp.wrapPlayer(player))){return true;}
		return false;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		OwnedLand land = LandLordAPI.getInstance().getLand(loc);
		if(land==null) return true;
		return land.isOwner(player.getUniqueId());
	}
}
