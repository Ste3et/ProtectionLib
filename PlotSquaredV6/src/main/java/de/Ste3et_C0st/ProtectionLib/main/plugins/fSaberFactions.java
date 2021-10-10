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

import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;

public class fSaberFactions extends ProtectionConfig{

	public fSaberFactions(Plugin plugin) {
		super(plugin);
	}

	@Override
	public void initConfig() {
		this.addDefault("CheckBuildInWilderness", true);
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		Faction faction = Board.getInstance().getFactionAt(toFactionLocation(loc));
		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
		return Objects.nonNull(faction) ? faction.getAccess(fPlayer, PermissableAction.BUILD) == Access.ALLOW : false;
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		Faction faction = Board.getInstance().getFactionAt(toFactionLocation(loc));
		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
		return Objects.nonNull(faction) ? fPlayer.getFaction().equals(faction) : false;
	}

	@Override
	public boolean isProtectedRegion(Location loc) {
		Faction faction = Board.getInstance().getFactionAt(toFactionLocation(loc));
		if(Objects.nonNull(faction)) {
			if(faction.isWilderness()) {
				if(getObject("CheckBuildInWilderness")) {
					return true;
				}else {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private FLocation toFactionLocation(Location loc) {
		return new FLocation(loc);
	}

}
