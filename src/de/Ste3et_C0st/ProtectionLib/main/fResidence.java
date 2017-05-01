package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;

public class fResidence extends ProtectinObj {
	Player p;
	Location loc;
	
	public fResidence(Plugin pl){
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
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(this.loc);
		if(residence==null) return true;
		ResidencePermissions perms = residence.getPermissions();
		return perms.playerHas(this.p, Flags.build, true);
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(this.loc);
		if(residence==null) return true;
		if(residence.getOwner().equalsIgnoreCase(this.p.getName())) return true;
		if(residence.getOwner().equalsIgnoreCase(this.p.getUniqueId().toString())) return true;
		return false;
	}
}
