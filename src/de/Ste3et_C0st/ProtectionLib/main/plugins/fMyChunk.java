package de.Ste3et_C0st.ProtectionLib.main.plugins;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.ellbristow.mychunk.MyChunk;
import me.ellbristow.mychunk.MyChunkChunk;

public class fMyChunk extends protectionObj {
	
	public fMyChunk(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(MyChunk.isWorldEnabled(loc.getWorld().getName())) {
			return MyChunkChunk.isAllowed(loc.getChunk(), player, "B");
		}
		return true;
	}
	
	public boolean isOwner(Player player, Location loc){
		if(MyChunk.isWorldEnabled(loc.getWorld().getName())) {
			MyChunkChunk c = new MyChunkChunk(loc.getChunk());
			return c.getOwnerId().equals(player.getUniqueId());
		}
		return true;
	}

}
