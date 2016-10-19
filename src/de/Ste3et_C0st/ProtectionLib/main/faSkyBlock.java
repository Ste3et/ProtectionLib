package de.Ste3et_C0st.ProtectionLib.main;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wasteofplastic.askyblock.ASkyBlockAPI;

public class faSkyBlock extends ProtectinObj {

	Player p;
	Location loc;
	
	public faSkyBlock(Plugin pl){
		super(pl);
	}
	
	public boolean canBuild(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return canBuild(p);
	}
	
	public boolean isOwner(Plugin p, Player player, Location loc){
		this.p = player;
		this.loc = loc;
		return isOwner(p);
	}
	
	private boolean canBuild(Plugin p){
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
	
	private boolean isOwner(Plugin p){
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
