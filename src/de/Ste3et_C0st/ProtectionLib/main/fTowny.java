package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import com.palmergames.bukkit.util.BukkitTools;

public class fTowny extends ProtectinObj {
	Player p;
	Location loc;
	
	public fTowny(Plugin pl){
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
		 try {
			if (!WorldCoord.parseWorldCoord(this.p).getTownBlock().hasTown()) {return true;}
			boolean b =PlayerCacheUtil.getCachePermission(this.p, this.loc, BukkitTools.getTypeId(this.loc.getBlock()), BukkitTools.getData(this.loc.getBlock()), TownyPermission.ActionType.BUILD);
			return b;
		} catch (NotRegisteredException e) {
			return true;
		}
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		 try {
			if (!WorldCoord.parseWorldCoord(this.p).getTownBlock().hasTown()) {return true;}
			Town town = WorldCoord.parseWorldCoord(this.p).getTownBlock().getTown();
			if(town==null) return true;
			Resident resi = TownyUniverse.getDataSource().getResident(this.p.getName());
			if(resi==null) return false;
			return town.isMayor(resi);
		} catch (NotRegisteredException e) {
			return true;
		}
	}
}
