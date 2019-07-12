package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fTowny extends protectionObj {
	
	public fTowny(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		return PlayerCacheUtil.getCachePermission(player, loc, Material.STONE, ActionType.BUILD);
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		 try {
			 if(TownyUniverse.getDataSource() == null) return true;
			 if(TownyUniverse.getDataSource().getWorld(loc.getWorld().getName()) == null) return true;
			 if (!TownyUniverse.getDataSource().getWorld(loc.getWorld().getName()).isUsingTowny()) return true;
			 Town town = WorldCoord.parseWorldCoord(player).getTownBlock().getTown();
			 if(town==null) return true;
			 Resident resi = TownyUniverse.getDataSource().getResident(player.getName());
			 if(resi==null) return false;
			 return town.isMayor(resi);
		} catch (NotRegisteredException e) {
			e.printStackTrace();
			return true;
		}
	}
}
