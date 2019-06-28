package de.Ste3et_C0st.ProtectionLib.main.plugins;

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
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		this.setLocation(loc);
		this.setPlayer(player);
		return canBuild();
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		this.setLocation(loc);
		this.setPlayer(player);
		return isOwner();
	}
	
	private boolean canBuild() {
		MPlayer player = MPlayer.get(getPlayer());
		if(player == null) return true;
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(getLocation()));
		if(faction == null) return true;
		return faction.isPlayerPermitted(player, MPerm.ID_BUILD);
	}
	
	private boolean isOwner() {
		setRegions(0);
		MPlayer player = MPlayer.get(getPlayer());
		if(player == null) return true;
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(getLocation()));
		if(faction == null) return true;
		setRegions(1);
		return faction.getLeader().equals(player);
	}
	
}
