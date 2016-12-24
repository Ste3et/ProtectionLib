package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.kingdoms.constants.kingdom.Kingdom;
import org.kingdoms.constants.land.Land;
import org.kingdoms.constants.land.SimpleChunkLocation;
import org.kingdoms.constants.player.KingdomPlayer;
import org.kingdoms.manager.game.GameManagement;

public class fKingdoms extends ProtectinObj {

	private Player p;
	private Location loc;

	public fKingdoms(Plugin plugin) {
		super(plugin);
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
		SimpleChunkLocation chunckLoc = new SimpleChunkLocation(loc.getChunk());
		Land l = GameManagement.getLandManager().getOrLoadLand(chunckLoc);
		if(l == null){return true;}
		String owner = l.getOwner();
		if(owner == null){return true;}
		Kingdom k = GameManagement.getKingdomManager().getOrLoadKingdom(owner);
		if(k == null){return true;}
		KingdomPlayer player = GameManagement.getPlayerManager().getSession(this.p);
		if(player == null){return true;}
		if(k.isEnemyMember(player)){return true;}
		if(k.getKing().equals(this.p.getUniqueId())){return true;}
		return false;
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		SimpleChunkLocation chunckLoc = new SimpleChunkLocation(loc.getChunk());
		Land l = GameManagement.getLandManager().getOrLoadLand(chunckLoc);
		if(l == null){return true;}
		String owner = l.getOwner();
		if(owner == null){return true;}
		Kingdom k = GameManagement.getKingdomManager().getOrLoadKingdom(owner);
		if(k == null){return true;}
		return k.getKing().equals(this.p.getUniqueId());
	}
	
}
