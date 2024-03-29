package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fFactions extends protectionObj{
	
	public fFactions(Plugin plugin) {
		super(plugin);
	}
	
	public boolean canBuild(Player player, Location loc) {
		MPlayer mPlayer = MPlayer.get(player);
		if(mPlayer == null) return true;
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(loc));
		if(faction == null) return true;
		return MPerm.getPermBuild().has(mPlayer, faction, false);
	}
	
	public boolean isOwner(Player player, Location loc) {
		MPlayer mPlayer = MPlayer.get(player);
		if(mPlayer == null) return true;
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(loc));
		if(faction == null) return true;
		return faction.getLeader().equals(mPlayer);
	}
	
	public boolean isProtectedRegion(Location location) {
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(location));
		return Objects.nonNull(faction);
	}
}
