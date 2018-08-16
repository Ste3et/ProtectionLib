package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fResidence extends ProtectinObj {

	public fResidence(Plugin pl){
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
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(this.getLocation());
		if(residence==null) return true;
		ResidencePermissions perms = residence.getPermissions();
		return perms.playerHas(this.getPlayer(), Flags.build, true);
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(this.getLocation());
		if(residence==null) return true;
		if(residence.getOwner().equalsIgnoreCase(this.getPlayer().getName())) return true;
		if(residence.getOwner().equalsIgnoreCase(this.getPlayer().getUniqueId().toString())) return true;
		return false;
	}
}
