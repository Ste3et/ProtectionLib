package de.Ste3et_C0st.ProtectionLib.main;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.Ste3et_C0st.ProtectionLib.exception.ProtectionCreateException;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fGriefPrevention;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIslandWorld;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fKingdoms;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fLandLord;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquared;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fRedProtect;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fResidence;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fTowny;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fWorldGuard;
import de.Ste3et_C0st.ProtectionLib.main.plugins.faSkyBlock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fuSkyblock;

public class ProtectionLib extends JavaPlugin{
	
	private List<ProtectionClass> protectList = new ArrayList<ProtectionClass>();
	private static ProtectionLib instance;
	public static ProtectionLib getInstance(){return instance;}
	
	private boolean isVaultEnable = false;
	private List<ProtectinObj> protectionClass = new ArrayList<ProtectinObj>();
	private ProtectionVaultPermission permissions = null;
	private List<UUID> playerList = new ArrayList<UUID>();
	
	@Override
	public void onEnable(){
		instance = this;
		if(Bukkit.getPluginManager().isPluginEnabled("Vault")){
			isVaultEnable = true;
			permissions = new ProtectionVaultPermission();
		}
		getCommand("protectionlib").setExecutor(new command());
		addWatchers();
	}
	
	public void onDisable(){
		
	}
	
	public void addWatchers() {
		addProtectionPlugin("WorldGuard");
		addProtectionPlugin("PlotSquared");
		addProtectionPlugin("Towny");
		addProtectionPlugin("GriefPrevention");
		addProtectionPlugin("Landlord");
		addProtectionPlugin("uSkyBlock");
		addProtectionPlugin("ASkyBlock");
		addProtectionPlugin("RedProtect");
		addProtectionPlugin("Residence");
		addProtectionPlugin("Kingdoms");
		addProtectionPlugin("IslandWorld");
	}
	
	private void addProtectionPlugin(String a){
		if(Bukkit.getPluginManager().isPluginEnabled(a)) {
			ProtectionClass ppL = new ProtectionClass(a);
			protectList.add(ppL);
			Plugin pl = Bukkit.getPluginManager().getPlugin(a);
			switch (a) {
				case "WorldGuard": protectionClass.add(new fWorldGuard(pl));break;
				case "PlotSquared": protectionClass.add(new fPlotSquared(pl));break;
				case "Towny": protectionClass.add(new fTowny(pl));break;
				case "GriefPrevention": protectionClass.add(new fGriefPrevention(pl));break;
				case "Landlord": protectionClass.add(new fLandLord(pl));break;
				case "uSkyBlock": protectionClass.add(new fuSkyblock(pl));break;
				case "ASkyBlock": protectionClass.add(new faSkyBlock(pl));break;
				case "RedProtect": protectionClass.add(new fRedProtect(pl));break;
				case "Residence": protectionClass.add(new fResidence(pl));break;
				case "Kingdoms": protectionClass.add(new fKingdoms(pl));break;
				case "IslandWorld": protectionClass.add(new fIslandWorld(pl));break;
			default:break;
			}
		}
	}
	
	public void addPrivateProtectionPlugin(String pluginName, ProtectinObj protectionClass) {
		ProtectionClass ppL = new ProtectionClass(pluginName);
		if(!this.protectList.contains(ppL)) {
			this.protectList.add(ppL);
			if(ppL.isLoaded()) {
				if(!this.protectionClass.contains(protectionClass)) {
					this.protectionClass.add(protectionClass);
				}else {
					try {
						throw(new ProtectionCreateException("", null));
					} catch (ProtectionCreateException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void clearWatchers() {
		this.protectionClass.clear();
		this.protectList.clear();
	}
	
	public boolean hasPermissions(Player p){
		if(p.isOp()) return true;
		boolean b = p.hasPermission("ProtectionLib.admin");
		if(isVaultEnable){
			b = permissions.permission.has(p, "ProtectionLib.admin");
		}
		return b;
	}
	
	public boolean toogleDebug(UUID uuid) {
		if(this.playerList.contains(uuid)) {
			this.playerList.remove(uuid);
			return false;
		}else {
			this.playerList.add(uuid);
			return true;
		}
	}
	
	public List<ProtectinObj> getWatchers(){
		return this.protectionClass;
	}

	public boolean canBuild(Location loc, Player player){
		if(hasPermissions(player)) return true;
		if(playerList.contains(player.getUniqueId())) {
			if(getWatchers().isEmpty()) {
				player.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
			}else {
				protectionClass.stream().forEach(protection -> player.sendMessage("§f[§6canBuild§f] " + protection.getPlugin().getName() + ": " + protection.canBuild(player, loc)));
			}
		}
		return !this.protectionClass.stream().filter(protection -> protection.canBuild(player, loc) == false).findFirst().isPresent();
	}
	
	public boolean isOwner(Location loc, Player player){
		if(hasPermissions(player)) return true;
		if(playerList.contains(player.getUniqueId())) {
			if(getWatchers().isEmpty()) {
				player.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
			}else {
				protectionClass.stream().forEach(protection -> player.sendMessage("§f[§6isOwner§f] " +protection.getPlugin().getName() + ": " + protection.isOwner(player, loc)));
			}
		}
		return !this.protectionClass.stream().filter(protection -> protection.canBuild(player, loc) == false).findFirst().isPresent();
	}
}
