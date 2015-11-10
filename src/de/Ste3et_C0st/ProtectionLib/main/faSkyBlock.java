package de.Ste3et_C0st.ProtectionLib.main;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

public class faSkyBlock {

	Player p;
	Location loc;
	public faSkyBlock(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null) return true;
		if(!api.getIslandWorld().equals(this.loc.getWorld())) return true;
		if(!api.islandAtLocation(this.loc)) return true;
		UUID uuid = api.getOwner(this.loc);
		if(uuid==null) return true;
		if(uuid.equals(this.p.getUniqueId())) return true;
		return api.getTeamMembers(uuid).contains(this.p.getUniqueId());
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null) return true;
		if(!api.getIslandWorld().equals(this.loc.getWorld())) return true;
		if(!api.islandAtLocation(this.loc)) return true;
		UUID uuid = api.getOwner(this.loc);
		if(uuid==null) return true;
		if(uuid.equals(this.p.getUniqueId())) return true;
		return false;
	}
}
