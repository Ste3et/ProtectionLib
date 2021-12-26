package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

@SuppressWarnings("deprecation")
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
			 if(TownyAPI.getInstance().getDataSource() == null) return true;
			 if(TownyAPI.getInstance().getDataSource().getWorld(loc.getWorld().getName()) == null) return true;
			 if (!TownyAPI.getInstance().getDataSource().getWorld(loc.getWorld().getName()).isUsingTowny()) return true;
			 Town town = WorldCoord.parseWorldCoord(player).getTownBlock().getTown();
			 if(town==null) return true;
			 Resident resi = TownyAPI.getInstance().getDataSource().getResident(player.getName());
			 if(resi==null) return false;
			 return town.isMayor(resi);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		try {
			if(TownyAPI.getInstance().getDataSource().getWorld(location.getWorld().getName()) == null) return false;
			WorldCoord coord = WorldCoord.parseWorldCoord(location);
			return Objects.nonNull(coord) ? coord.hasTownBlock() : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
