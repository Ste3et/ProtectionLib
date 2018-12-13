package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.DiceChunk.DiceChunk;
import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fDiceChunk extends ProtectinObj{ 
	
	public fDiceChunk(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		this.setPlayer(player);
		this.setLocation(loc);
		return canBuild();
	}

	private boolean canBuild() {
		return DiceChunk.getInstance().canBuild(getLocation(), getPlayer());
	}

	@Override
	public boolean isOwner(Player player, Location loc) {
		return DiceChunk.getInstance().getOwner(getLocation()).equals(getPlayer().getUniqueId());
	}
}
