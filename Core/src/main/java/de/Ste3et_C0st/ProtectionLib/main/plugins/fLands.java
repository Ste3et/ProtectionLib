package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.role.enums.RoleSetting;

public class fLands extends protectionObj{

	public static final String pluginName = "Lands";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
	private final LandsIntegration landsAddon;
	
	public fLands(Plugin plugin) {
		super(plugin);
		landsAddon = new LandsIntegration(ProtectionLib.getInstance());
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		
		@Nullable Area chunk = landsAddon.getAreaByLoc(loc);
		return Objects.isNull(chunk) ? true : chunk.canSetting(player, RoleSetting.BLOCK_PLACE, false);
	}
	
	@Override
	public boolean isOwner(Player player, Location loc) {
		if(Objects.isNull(landsAddon)) return true;
		Land chunk = landsAddon.getLand(loc);
		return Objects.isNull(chunk) ? true : chunk.getOwnerUID().equals(player.getUniqueId());
	}
	
	public boolean isProtectedRegion(Location location) {
		Land chunk = landsAddon.getLand(location);
		return Objects.nonNull(chunk);
	}
}
