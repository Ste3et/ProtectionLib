package de.Ste3et_C0st.ProtectionLib.main;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ProtectionVaultPermission {

	Permission permission;
	
	public ProtectionVaultPermission()
	{
	       RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	       if (permissionProvider != null) {
	           permission = permissionProvider.getProvider();
	       }
   }
	
}
