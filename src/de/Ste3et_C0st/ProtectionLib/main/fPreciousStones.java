package de.Ste3et_C0st.ProtectionLib.main;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.Field;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class fPreciousStones extends ProtectinObj  {

	Player p;
	Location loc;
	
	public fPreciousStones(Plugin pl) {
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
		return PreciousStones.API().canBreak(this.p, this.loc);
	}
	
	private boolean isOwner(Plugin p){
		if(p==null){return true;}
		return PreciousStones.getInstance().getForceFieldManager().isOwned(new Field(loc.getBlock()), this.p);
	}
}
