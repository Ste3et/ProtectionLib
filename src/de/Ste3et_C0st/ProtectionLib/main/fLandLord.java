package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.jcdesimp.landlord.persistantData.OwnedLand;

public class fLandLord {
	Player p;
	Location loc;
	
	public fLandLord(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		OwnedLand land = OwnedLand.getApplicableLand(this.loc);
		if(land==null) return true;
		if(land.getOwnerUUID().equals(this.p.getUniqueId()))return true;
		if(land.isFriend(this.p)){return true;}
		return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		OwnedLand land = OwnedLand.getApplicableLand(this.loc);
		if(land==null) return true;
		if(land.getOwnerUUID().equals(this.p.getUniqueId()))return true;
		return false;
	}
}
