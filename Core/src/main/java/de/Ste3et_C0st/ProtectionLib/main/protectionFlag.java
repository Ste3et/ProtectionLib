package de.Ste3et_C0st.ProtectionLib.main;

import java.util.List;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class protectionFlag<K> extends protectionObj{
	
	public protectionFlag(Plugin plugin) {
		super(plugin);
		if(plugin.isEnabled() == false) this.onLoad(plugin);
	}

	public abstract Optional<K> registerFlag(Plugin plugin, String string, boolean bool);
	
	protected void info(String name) {
		ProtectionLib.getInstance().getLogger().info("Register Customflag " + name + " by " + getPlugin().getName());
	}
	
	public abstract boolean queryFlag(String flagName, Player player, Location location);
	
	public abstract List<K> getFlags();
	public abstract void onLoad(Plugin plugin);

	
}
