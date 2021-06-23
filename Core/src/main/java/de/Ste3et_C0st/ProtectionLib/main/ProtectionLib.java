package de.Ste3et_C0st.ProtectionLib.main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Predicate;

import de.Ste3et_C0st.ProtectionLib.exception.ProtectionCreateException;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fAreaShop;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fBentobox;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fDiceChunk;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFabledSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFactionsUUID;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fGriefPrevention;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fGriefdefenderAPI;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fKingdoms;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fLandLord;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fSuperiorSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fTowny;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fWorldGuardv7;
import de.Ste3et_C0st.ProtectionLib.main.plugins.faSkyBlock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fuSkyblock;

public class ProtectionLib extends JavaPlugin{
	
	private List<ProtectionClass> protectList = new ArrayList<ProtectionClass>();
	private static ProtectionLib instance;
	public static ProtectionLib getInstance(){return instance;}
	
	private boolean isVaultEnable = false;
	private List<protectionObj> protectionClass = new ArrayList<protectionObj>();
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
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable(){
		instance = null;
	}
	
	public void addWatchers() {
		this.hookIntoPlugins();
		protectionClass.stream().forEach(entry -> entry.update());
	}
	
	private void hookIntoPlugins() {
		HashSet<Class<? extends protectionObj>> protectionClasses = generatePluginMap();
		
		protectionClasses.stream().forEach(entry -> {
			try {
				Field staticPluginNameField = entry.getDeclaredField("pluginName");
				Field predicateFild = entry.getDeclaredField("PREDICATE");
				
				String pluginName = (String) staticPluginNameField.get(entry);
				Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
				
				if(Objects.nonNull(plugin)) {
					ProtectionClass ppL = new ProtectionClass(pluginName);
					this.protectList.add(ppL);
					if(plugin.isEnabled()) {
						Predicate<PluginDescriptionFile> predicate = (Predicate<PluginDescriptionFile>) predicateFild.get(entry);
						if(predicate.test(plugin.getDescription())) {
							protectionObj object = entry.getDeclaredConstructor(Plugin.class).newInstance(plugin);
							this.protectionClass.add(object);
						}
					}
				}
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
		
	}
	
	private HashSet<Class<? extends protectionObj>> generatePluginMap() {
		HashSet<Class<? extends protectionObj>> protectetionMap = new HashSet<>();
		protectetionMap.add(fAreaShop.class);
		protectetionMap.add(faSkyBlock.class);
		protectetionMap.add(fBentobox.class);
		protectetionMap.add(fDiceChunk.class);
		protectetionMap.add(fFabledSkyblock.class);
		protectetionMap.add(fFactionsUUID.class);
		protectetionMap.add(fGriefdefenderAPI.class);
		protectetionMap.add(fGriefPrevention.class);
		protectetionMap.add(fKingdoms.class);
		protectetionMap.add(fLandLord.class);
		protectetionMap.add(fSuperiorSkyblock.class);
		protectetionMap.add(fSuperiorSkyblock.class);
		protectetionMap.add(fTowny.class);
		protectetionMap.add(fuSkyblock.class);
		protectetionMap.add(fWorldGuardv7.class);
		
		//LocalLibary
		//PlotSquaredLegacyModule
		//PlotSquaredV5Module
		//PlotSquaredV6Module
		
		try {
			this.registerModules("LocalLibary", protectetionMap);
			//this.registerModules("PlotSquaredLegacyModule", protectetionMap);
			this.registerModules("PlotSquaredV5Module", protectetionMap);
			this.registerModules("PlotSquaredV6Module", protectetionMap);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return protectetionMap;
	}
	
	private void registerModules(String modul, HashSet<Class<? extends protectionObj>> hashSet) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String name = "de.Ste3et_C0st.ProtectionLib.main.module." + modul;
		Class<?> clazz = Class.forName(name);
		if(Objects.isNull(clazz)) {
			System.out.println("ProtectionLib-Module: " + modul + " not found!");
			return;
		}
		
		ProtectionModule protectionModule = (ProtectionModule) clazz.newInstance();
		hashSet.addAll(protectionModule.generatePluginMap());
		System.out.println("ProtectionLib-Module: " + modul + " hooked!");
	}
	
	public void addPrivateProtectionPlugin(String pluginName, protectionObj protectionClass) {
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
		HandlerList.unregisterAll(getInstance());
		this.protectionClass.clear();
		this.protectList.clear();
	}
	
	public boolean hasPermissions(Player p){
		if(p.isOp()) return true;
		return isVaultEnable ? permissions.permission.has(p, "ProtectionLib.admin") : p.hasPermission("ProtectionLib.admin");
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
	
	public List<protectionObj> getWatchers(){
		return this.protectionClass;
	}
	
	public boolean canBuild(Location loc, Player player){
		if(hasPermissions(player)) return true;
		if(playerList.contains(player.getUniqueId())) {
			if(getWatchers().isEmpty()) {
				player.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
			}else {
				protectionClass.stream().forEach(protection -> {
					if(protection.isEnabled()) {
						player.sendMessage("§f[§6canBuild§f->§a"+ protection.getClass().getSimpleName()+"§f] " + protection.getPlugin().getName() + ": " + protection.canBuild(player, loc));
					}else {
						player.sendMessage("§f[§6canBuild§f->§c"+ protection.getClass().getSimpleName()+"§f] " + protection.getPlugin().getName() + ": §cdisabled");
					}
				});
			}
		}
		return !this.protectionClass.stream().filter(protectionObj::isEnabled).filter(protection -> protection.canBuild(player, loc) == false).findFirst().isPresent();
	}
	
	public boolean isOwner(Location loc, Player player){
		if(hasPermissions(player)) return true;
		if(playerList.contains(player.getUniqueId())) {
			if(getWatchers().isEmpty()) {
				player.sendMessage("§c§lProtectionLib is not hooked to any Plugin !");
			}else {
				protectionClass.stream().forEach(protection -> {
					if(protection.isEnabled()) {
						player.sendMessage("§f[§6isOwner§f->§a"+protection.getClass().getSimpleName()+"§f] " +protection.getPlugin().getName() + ": " + protection.isOwner(player, loc));
					}else {
						player.sendMessage("§f[§6isOwner§f->§c"+protection.getClass().getSimpleName()+"§f] " +protection.getPlugin().getName() + ": §cdisabled");
					}
				});
			}
		}
		return !this.protectionClass.stream().filter(protectionObj::isEnabled).filter(protection -> protection.isOwner(player, loc) == false).findFirst().isPresent();
	}
	
	public boolean isProtectedRegion(Location location) {
		return this.protectionClass.stream().filter(protectionObj::isEnabled).filter(protection -> protection.isProtectedRegion(location)).findFirst().isPresent();
	}
	
	public boolean registerFlag(Plugin plugin, String str, boolean defaultValue) {
		if(getWatchers().isEmpty()) {
			System.out.println("ProtectionLib is not hooked to any Plugin !");
		    return false;
		}else {
			AtomicBoolean feedback = new AtomicBoolean(false);
			protectionClass.stream().forEach(protection -> {
				boolean b = protection.registerFlag(plugin, str, defaultValue);
				if(b) {
					feedback.set(true);
					System.out.print("ProtectionLib: " + protection.getPlugin().getName() + " register Customflag " + str + " by " + plugin.getName());
				}
			});
			return feedback.get();
		}
	}
	
	public boolean queryFlag(String string, Player player, Location location) {
		return !this.protectionClass.stream().filter(protection -> protection.queryFlag(string, player, location) == false).findFirst().isPresent();
	}
}
