package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.config.file.MainConfig;
import com.massivecraft.factions.perms.PermissibleAction;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionConfig;

public class fFactionsUUID extends ProtectionConfig{
	
	public static final String pluginName = "Factions";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName) && file.getAuthors().contains("drtshock");
	};
	
	public fFactionsUUID(Plugin plugin) {
		super(plugin);
	}

	public boolean canBuild(Player player, Location loc) {
		FLocation flocation = new FLocation(loc);
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
		if(fplayer == null) return true;
		Faction faction = Board.getInstance().getFactionAt(flocation);
		if(faction == null) return true;
		
		if(faction.isWilderness() && getObject("CheckBuildInWilderness")) {
			MainConfig.Factions facConf = FactionsPlugin.getInstance().conf().factions();
			if(facConf.protection().getWorldsNoWildernessProtection().contains(loc.getWorld().getName())) return true;
			if (!facConf.protection().isWildernessDenyUsage()) return true;
			return false;
		}
		
		return faction.playerHasOwnershipRights(fplayer, flocation) || faction.hasAccess(fplayer, PermissibleAction.BUILD);
	}

	public boolean isOwner(Player player, Location loc) {
		FLocation flocation = new FLocation(loc);
		FPlayer fplayer = FPlayers.getInstance().getByPlayer(player);
		if(fplayer == null) return true;
		Faction faction = Board.getInstance().getFactionAt(flocation);
		if(faction == null) return true;
		return faction.playerHasOwnershipRights(fplayer, flocation);
	}
	
	public boolean isProtectedRegion(Location location) {
		FLocation flocation = new FLocation(location);
		return Objects.nonNull(Board.getInstance().getFactionAt(flocation));
	}
	
	@Override
	public void initConfig() {
		this.addDefault("CheckBuildInWilderness", true);
	}
}
