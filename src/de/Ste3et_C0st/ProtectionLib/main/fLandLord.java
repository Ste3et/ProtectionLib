package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.jcdesimp.landlord.persistantData.OwnedLand;

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
		OwnedLand land = OwnedLand.getApplicableLand(this.loc);
		if(land==null) return true;
		if(land.getOwnerUUID().equals(this.p.getUniqueId()))return true;
		if(land.isFriend(this.p)){return true;}
		return false;
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		OwnedLand land = OwnedLand.getApplicableLand(this.loc);
		if(land==null) return true;
		if(land.getOwnerUUID().equals(this.p.getUniqueId()))return true;
		return false;
	}
}
