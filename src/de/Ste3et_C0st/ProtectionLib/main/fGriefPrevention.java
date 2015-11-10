package de.Ste3et_C0st.ProtectionLib.main;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class fGriefPrevention {
	Player p;
	Location loc;
	
	public fGriefPrevention(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		 Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.loc, false, null);
		 String noBuildReason = GriefPrevention.instance.allowBuild(this.p, this.loc, this.loc.getBlock().getType());
		 if(claim==null){return true;}
		 if(noBuildReason==null) return true;
		 return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		 Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.loc, false, null);
		 if(claim==null){return true;}
		 if(claim.getOwnerName().equalsIgnoreCase(this.p.getName())) return true;
		 return false;
	}
}
