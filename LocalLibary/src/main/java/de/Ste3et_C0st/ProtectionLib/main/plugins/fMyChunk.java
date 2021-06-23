package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import me.ellbristow.mychunk.MyChunk;
import me.ellbristow.mychunk.MyChunkChunk;

public class fMyChunk extends protectionObj {
	
	public static final String pluginName = "MyChunk";
	public static final Predicate<PluginDescriptionFile> PREDICATE = file -> {
		return file.getName().equalsIgnoreCase(pluginName);
	};
	
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

	public boolean isProtectedRegion(Location location) {
		if(!MyChunk.isWorldEnabled(location.getWorld().getName())) return false;
		MyChunkChunk chunk = new MyChunkChunk(location.getChunk());
		return Objects.nonNull(chunk);
	}
}
