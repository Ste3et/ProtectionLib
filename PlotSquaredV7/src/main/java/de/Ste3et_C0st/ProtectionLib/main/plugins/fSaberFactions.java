package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.zcore.fperms.Access;
import com.massivecraft.factions.zcore.fperms.PermissableAction;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fSaberFactions extends protectionObj{

	public fSaberFactions(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(isProtectedRegion(loc) == false) return true;
		FPlayer factionPlayer = FPlayers.getInstance().getByPlayer(player);
		if(factionPlayer.isAdminBypassing()) return true;
		Faction actorFaction = Board.getInstance().getFactionAt(new FLocation(loc));
		if(actorFaction == null) return true;
		return actorFaction.getAccess(factionPlayer, PermissableAction.PAIN_BUILD) == Access.ALLOW;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		if(isProtectedRegion(loc) == false) return true;
		FLocation fLocation = new FLocation(loc);
		Faction otherFaction = Board.getInstance().getFactionAt(fLocation);
		return otherFaction.getOwnerList(fLocation).contains(player.getName());
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		FLocation fLocation = new FLocation(loc);
		if(Objects.nonNull(Board.getInstance().getFactionAt(fLocation))) {
			Faction faction = Board.getInstance().getFactionAt(fLocation);
			if(faction.isWilderness()) return false;
			if(faction.isSafeZone()) return false;
			if(faction.isWarZone()) return false;
			return true;
		}
		return false;
	}

}
