package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fResidence extends protectionObj {

	public fResidence(Plugin pl){
		super(pl);
	}

	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(loc);
		if(residence==null) return true;
		ResidencePermissions perms = residence.getPermissions();
		return perms.playerHas(player, Flags.build, true);
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(loc);
		if(residence==null) return true;
		if(residence.getOwner().equalsIgnoreCase(player.getName())) return true;
		if(residence.getOwner().equalsIgnoreCase(player.getUniqueId().toString())) return true;
		return false;
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(location);
		return Objects.nonNull(residence);
	}
}
