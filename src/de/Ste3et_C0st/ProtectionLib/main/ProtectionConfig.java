package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.plugin.Plugin;

public abstract class ProtectionConfig extends protectionObj {
	
	private boolean clearingOnDeleteRegion = false;
	
	public ProtectionConfig(Plugin plugin) {
		super(plugin);
		ProtectionLib.getInstance().getConfig().addDefault("config." + getPlugin().getName() + ".RegionClearEvent", true);
	}

	public boolean isClearingOnDeleteRegion() {
		return isEnabled() ? clearingOnDeleteRegion : false;
	}

	public void setClearingOnDeleteRegion(boolean clearingOnDeleteRegion) {
		this.clearingOnDeleteRegion = clearingOnDeleteRegion;
	}
	
	public void update() {
		super.update();
		this.setClearingOnDeleteRegion(ProtectionLib.getInstance().getConfig().getBoolean("config." + getPlugin().getName() + ".RegionClearEvent", true));
	}
}
