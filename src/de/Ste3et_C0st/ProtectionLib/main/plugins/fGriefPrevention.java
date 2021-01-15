package de.Ste3et_C0st.ProtectionLib.main.plugins;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;

public class fGriefPrevention extends ProtectionConfig implements Listener {

	public fGriefPrevention(Plugin pl){
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	private void onClear(ClaimDeletedEvent e){
		if(getObject("RegionClearEvent") == false) return;
		Location loc1 = e.getClaim().getGreaterBoundaryCorner();
		Location loc2 = e.getClaim().getLesserBoundaryCorner();
		RegionClearEvent event = new RegionClearEvent(loc1, loc2);
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(loc, true, null);
		if(claim==null) return true;
		if(claim.getOwnerName().equalsIgnoreCase(player.getName())) return true;
		if(claim.allowAccess(player)==null) return true;
		if(claim.allowBuild(player, Material.DIAMOND_BLOCK)==null) return true;
		return false;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(loc, true, null);
		if(claim==null) return true;
		return claim.getOwnerName().equalsIgnoreCase(player.getName());
	}
	
	public boolean isProtectedRegion(Location location) {
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
		return Objects.nonNull(claim);
	}

	@Override
	public void initConfig() {
		this.addDefault("RegionClearEvent", true);
	}
}
