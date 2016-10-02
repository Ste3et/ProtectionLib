package de.Ste3et_C0st.ProtectionLib.main;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;

public class fGriefPrevention {
	Player p;
	Location loc;
	
	public fGriefPrevention(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	@EventHandler
	private void onClear(ClaimDeletedEvent e){
		Location loc1 = e.getClaim().getGreaterBoundaryCorner();
		Location loc2 = e.getClaim().getLesserBoundaryCorner();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.loc, true, null);
		if(claim==null) return true;
		if(claim.getOwnerName().equalsIgnoreCase(this.p.getName())) return true;
		if(claim.allowAccess(this.p)==null) return true;
		if(claim.allowBuild(this.p, Material.DIAMOND_BLOCK)==null) return true;
		return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.loc, true, null);
		if(claim==null) return true;
		if(claim.getOwnerName().equalsIgnoreCase(this.p.getName())) return true;
		return false;
	}
}
