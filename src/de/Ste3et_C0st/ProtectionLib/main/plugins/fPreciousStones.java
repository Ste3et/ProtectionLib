package de.Ste3et_C0st.ProtectionLib.main.plugins;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.Field;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.Ste3et_C0st.ProtectionLib.main.ProtectinObj;

public class fPreciousStones extends ProtectinObj  {


	public fPreciousStones(Plugin pl) {
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
		return PreciousStones.API().canBreak(this.getPlayer(), this.getLocation());
	}
	
	private boolean isOwner(){
		if(getPlugin()==null){return true;}
		return PreciousStones.getInstance().getForceFieldManager().isOwned(new Field(getLocation().getBlock()), this.getPlayer());
	}
}
