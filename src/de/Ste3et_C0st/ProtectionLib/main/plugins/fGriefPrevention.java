package de.Ste3et_C0st.ProtectionLib.main.plugins;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefPrevention extends ProtectinObj implements Listener {

	public fGriefPrevention(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
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
	
	@EventHandler
	private void onClear(ClaimDeletedEvent e){
		Location loc1 = e.getClaim().getGreaterBoundaryCorner();
		Location loc2 = e.getClaim().getLesserBoundaryCorner();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.getLocation(), true, null);
		if(claim==null) return true;
		if(claim.getOwnerName().equalsIgnoreCase(this.getPlayer().getName())) return true;
		if(claim.allowAccess(this.getPlayer())==null) return true;
		if(claim.allowBuild(this.getPlayer(), Material.DIAMOND_BLOCK)==null) return true;
		return false;
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(this.getLocation(), true, null);
		if(claim==null) return true;
		if(claim.getOwnerName().equalsIgnoreCase(this.getPlayer().getName())) return true;
		return false;
	}
}
