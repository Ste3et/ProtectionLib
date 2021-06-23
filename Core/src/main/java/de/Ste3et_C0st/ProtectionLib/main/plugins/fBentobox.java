package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

//BentoBox
public class fBentobox extends protectionObj{

	public static final String pluginName = "BentoBox";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	public fBentobox(Plugin plugin) {
		super(plugin);
	}
	
	public boolean canBuild(Player player, Location loc) {
		if(getPlugin() == null) return true;
		if(!BentoBox.getInstance().getIWM().inWorld(loc)) return true;
		Optional<Island> is = BentoBox.getInstance().getIslands().getProtectedIslandAt(loc);
		return is.map(x -> x.isAllowed(Flags.PLACE_BLOCKS)).orElse(Flags.PLACE_BLOCKS.isSetForWorld(loc.getWorld()));
	}

	public boolean isOwner(Player player, Location loc) {
		if(getPlugin() == null) return true;
		if(!BentoBox.getInstance().getIWM().inWorld(loc)) return true;
		Optional<Island> is = BentoBox.getInstance().getIslands().getProtectedIslandAt(loc);
		if(!is.isPresent()) return true;
		return is.filter(x -> x.getOwner().equals(player.getUniqueId())).isPresent();
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin() == null) return false;
		if(!BentoBox.getInstance().getIWM().inWorld(location)) return false;
		Optional<Island> island = BentoBox.getInstance().getIslands().getProtectedIslandAt(location);
		return island.isPresent();
	}
}
