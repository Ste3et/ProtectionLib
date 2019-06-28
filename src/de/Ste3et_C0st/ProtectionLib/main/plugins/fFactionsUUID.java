package de.Ste3et_C0st.ProtectionLib.main.plugins;

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

public class fFactionsUUID extends protectionObj{
	
	public fFactionsUUID(Plugin plugin) {
		super(plugin);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		FLocation flocation = new FLocation(loc);
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
		if(fplayer == null) return true;
		Faction faction = Board.getInstance().getFactionAt(flocation);
		if(faction == null) return true;
		return faction.playerHasOwnershipRights(fplayer, flocation) || faction.getAccess(fplayer, PermissableAction.BUILD).equals(Access.ALLOW);
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		FLocation flocation = new FLocation(loc);
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
		if(fplayer == null) return true;
		Faction faction = Board.getInstance().getFactionAt(flocation);
		if(faction == null) return true;
		return faction.playerHasOwnershipRights(fplayer, flocation);
	}
}
