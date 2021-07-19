package de.Ste3et_C0st.ProtectionLib.main.plugins;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fPreciousStones extends protectionObj  {
	
	public fPreciousStones(Plugin pl) {
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		return PreciousStones.API().canBreak(player, loc);
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		try {
			return PreciousStones.API().canBreak(player, loc);
		}catch(Exception ex) {
			return true;
		}
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		return PreciousStones.API().isPStone(location);
	}
}
