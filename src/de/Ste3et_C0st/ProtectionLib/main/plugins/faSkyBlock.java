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
		this.setPlayer(player);
		this.setLocation(loc);
		return canBuild();
	}
	
	public boolean isOwner(Player player, Location loc){
		this.setPlayer(player);
		this.setLocation(loc);
		return isOwner();
	}
	
	
	private boolean canBuild(){
		if(getPlugin()==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null){return true;}
		if(!api.getIslandWorld().equals(this.getLocation().getWorld())){return true;}
		if(!api.islandAtLocation(this.getLocation())){return false;}
		UUID uuid = api.getOwner(this.getLocation());
		if(uuid==null){return true;}
		if(uuid.equals(this.getPlayer().getUniqueId())){return true;}
		return api.getTeamMembers(uuid).contains(this.getPlayer().getUniqueId());
	}
	
	private boolean isOwner(){
		setRegions(0);
		if(getPlugin()==null){return true;}
		ASkyBlockAPI api = ASkyBlockAPI.getInstance();
		if(api.getIslandWorld()==null) return true;
		if(!api.getIslandWorld().equals(this.getLocation().getWorld())) return true;
		if(!api.islandAtLocation(this.getLocation())) return false;
		setRegions(1);
		UUID uuid = api.getOwner(this.getLocation());
		if(uuid==null) return true;
		if(uuid.equals(this.getPlayer().getUniqueId())) return true;
		return false;
	}
}
