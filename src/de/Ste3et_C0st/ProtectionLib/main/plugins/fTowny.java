package de.Ste3et_C0st.ProtectionLib.main.plugins;

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

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fTowny extends ProtectinObj {
	
	public fTowny(Plugin pl){
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
		 try {
			if (!WorldCoord.parseWorldCoord(this.getPlayer()).getTownBlock().hasTown()) {return true;}
			boolean b =PlayerCacheUtil.getCachePermission(this.getPlayer(), this.getLocation(), BukkitTools.getTypeId(this.getLocation().getBlock()), BukkitTools.getData(this.getLocation().getBlock()), TownyPermission.ActionType.BUILD);
			return b;
		} catch (NotRegisteredException e) {
			return true;
		}
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		 try {
			if (!WorldCoord.parseWorldCoord(this.getPlayer()).getTownBlock().hasTown()) {return true;}
			Town town = WorldCoord.parseWorldCoord(this.getPlayer()).getTownBlock().getTown();
			if(town==null) return true;
			Resident resi = TownyUniverse.getDataSource().getResident(this.getPlayer().getName());
			if(resi==null) return false;
			return town.isMayor(resi);
		} catch (NotRegisteredException e) {
			return true;
		}
	}
}
