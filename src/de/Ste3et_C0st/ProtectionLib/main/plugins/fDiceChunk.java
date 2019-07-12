package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.DiceChunk.DiceChunk;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fDiceChunk extends protectionObj{ 
	
	public fDiceChunk(Plugin plugin) {
		super(plugin);
	}

	public boolean canBuild(Player player, Location loc) {
		return DiceChunk.getInstance().canBuild(loc, player);
	}

	public boolean isOwner(Player player, Location loc) {
		return DiceChunk.getInstance().getOwner(loc).equals(player.getUniqueId());
	}
}
