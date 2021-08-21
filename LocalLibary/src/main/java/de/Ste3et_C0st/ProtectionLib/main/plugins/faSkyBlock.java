package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.wasteofplastic.askyblock.ASkyBlockAPI;

import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class faSkyBlock extends protectionObj {
	
	public faSkyBlock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Player player, Location loc){
		if(getPlugin()==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null){return true;}
		if(!api.getIslandWorld().equals(loc.getWorld())){return true;}
		if(!api.islandAtLocation(loc)){return false;}
		UUID uuid = api.getOwner(loc);
		if(uuid==null){return true;}
		if(uuid.equals(player.getUniqueId())){return true;}
		return api.getTeamMembers(uuid).contains(player.getUniqueId());
	}
	
	public boolean isOwner(Player player, Location loc){
		if(getPlugin()==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null) return true;
		if(!api.getIslandWorld().equals(loc.getWorld())) return true;
		if(!api.islandAtLocation(loc)) return false;
		UUID uuid = api.getOwner(loc);
		if(uuid==null) return true;
		if(uuid.equals(player.getUniqueId())) return true;
		return false;
	}
	
	public boolean isProtectedRegion(Location location) {
		if(getPlugin()==null){return false;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null) return false;
		if(!api.getIslandWorld().equals(location.getWorld())) return false;
		if(!api.islandAtLocation(location)) return false;
		return true;
	}
}
