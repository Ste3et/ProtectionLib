package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

//BentoBox
public class fBentobox extends protectionObj{

	public fBentobox(Plugin plugin) {
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
		if(getPlugin() == null) return true;
		if(!BentoBox.getInstance().getIWM().inWorld(getLocation())) return true;
		Optional<Island> is = BentoBox.getInstance().getIslands().getProtectedIslandAt(getLocation());
		return is.map(x -> x.isAllowed(Flags.PLACE_BLOCKS)).orElse(Flags.PLACE_BLOCKS.isSetForWorld(getLocation().getWorld()));
	}

	private boolean isOwner() {
		setRegions(0);
		if(getPlugin() == null) return true;
		if(!BentoBox.getInstance().getIWM().inWorld(getLocation())) return true;
		Optional<Island> is = BentoBox.getInstance().getIslands().getProtectedIslandAt(getLocation());
		if(is==null) return true;
		setRegions(1);
		return is.filter(x -> x.getOwner().equals(getPlayer().getUniqueId())).isPresent();
	}
}
