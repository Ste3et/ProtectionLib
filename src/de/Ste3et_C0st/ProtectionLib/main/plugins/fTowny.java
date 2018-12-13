package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Coord;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
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
		return PlayerCacheUtil.getCachePermission(getPlayer(), getLocation(), Material.STONE, ActionType.BUILD);
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		 try {
			 if(TownyUniverse.getDataSource() == null) return true;
			 if(TownyUniverse.getDataSource().getWorld(getLocation().getWorld().getName()) == null) return true;
			 if (!TownyUniverse.getDataSource().getWorld(getLocation().getWorld().getName()).isUsingTowny()) return true;
			 Town town = WorldCoord.parseWorldCoord(this.getPlayer()).getTownBlock().getTown();
			 if(town==null) return true;
			 Resident resi = TownyUniverse.getDataSource().getResident(this.getPlayer().getName());
			 if(resi==null) return false;
			 return town.isMayor(resi);
		} catch (NotRegisteredException e) {
			e.printStackTrace();
			return true;
		}
	}
}
