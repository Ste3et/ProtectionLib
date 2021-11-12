package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fTownyAdvanced extends protectionObj{

	public fTownyAdvanced(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		return PlayerCacheUtil.getCachePermission(player, loc, loc.getBlock().getType(), ActionType.BUILD);
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		try {
			Resident resident = TownyUniverse.getInstance().getResident(player.getUniqueId());
			if(Objects.nonNull(resident)) {
				if(resident.hasTown()) {
					return resident.getTown().hasTownBlock(WorldCoord.parseWorldCoord(loc));
				}
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		try {
			TownBlock town = TownyAPI.getInstance().getTownBlock(loc);
			return Objects.nonNull(town) ? Objects.nonNull(town.getTown()) : false;
		} catch (NotRegisteredException e) {
			return false;
		}
	}

}
