package de.Ste3et_C0st.ProtectionLib.main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.Ste3et_C0st.ProtectionLib.exception.ProtectionCreateException;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fAreaShop;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fBentobox;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fDiceChunk;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFabledSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFactions;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFactionsUUID;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fGriefPrevention;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fGriefdefenderAPI;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIridiumSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIslandWorld;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fKingdoms;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fLandLord;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fLands;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fMyChunk;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquared;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquaredLegacy;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotsquaredV5;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPreciousStones;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fRedProtect;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fResidence;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fSuperiorSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fTowny;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fWorldGuardv7;
import de.Ste3et_C0st.ProtectionLib.main.plugins.faSkyBlock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fuSkyblock;
import net.md_5.bungee.api.chat.TextComponent;

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
		addProtectionPlugin("BentoBox");
		addProtectionPlugin("DiceChunk");
		addProtectionPlugin("AreaShop");
		addProtectionPlugin("Factions");
		addProtectionPlugin("PreciousStones");
		addProtectionPlugin("MyChunk");
		addProtectionPlugin("Lands");
		addProtectionPlugin("SuperiorSkyblock2");
		addProtectionPlugin("IridiumSkyblock");
		addProtectionPlugin("FabledSkyblock");
		addProtectionPlugin("GriefDefender");
		protectionClass.stream().forEach(entry -> entry.update());
	}
	
	private void addProtectionPlugin(String a){
		if(Bukkit.getPluginManager().isPluginEnabled(a)) {
			ProtectionClass ppL = new ProtectionClass(a);
			protectList.add(ppL);
			Plugin pl = Bukkit.getPluginManager().getPlugin(a);
			switch (a) {
				case "WorldGuard": 
					if(!isVersionAvaiable(pl).equalsIgnoreCase("")) {
						protectionClass.add(new fWorldGuardv7(pl));
					}
					
					break;
				case "PlotSquared":
					if(pl.getDescription().getVersion().startsWith("5") || pl.getDescription().getVersion().startsWith("6")) {
						protectionClass.add(new fPlotsquaredV5(pl));
					}else if(!isVersionAvaiable(pl).equalsIgnoreCase("")) {
						protectionClass.add(new fPlotSquared(pl));
					}else {
						protectionClass.add(new fPlotSquaredLegacy(pl));
					}
					break;
				
				case "Towny": protectionClass.add(new fTowny(pl));break;
				case "GriefPrevention": protectionClass.add(new fGriefPrevention(pl));break;
				case "Landlord": protectionClass.add(new fLandLord(pl));break;
				case "uSkyBlock": protectionClass.add(new fuSkyblock(pl));break;
				case "ASkyBlock": protectionClass.add(new faSkyBlock(pl));break;
				case "RedProtect": protectionClass.add(new fRedProtect(pl));break;
				case "Residence": protectionClass.add(new fResidence(pl));break;
				case "Kingdoms": protectionClass.add(new fKingdoms(pl));break;
				case "IslandWorld": protectionClass.add(new fIslandWorld(pl));break;
				case "BentoBox": protectionClass.add(new fBentobox(pl));break;
				case "DiceChunk": protectionClass.add(new fDiceChunk(pl));break;
				case "AreaShop" : protectionClass.add(new fAreaShop(pl));break;
				case "MyChunk" : protectionClass.add(new fMyChunk(pl));break;
				case "Lands" : protectionClass.add(new fLands(pl));break;
				case "SuperiorSkyblock2" : protectionClass.add(new fSuperiorSkyblock(pl));break;
				case "IridiumSkyblock" : protectionClass.add(new fIridiumSkyblock(pl));break;
				case "GriefDefender" : protectionClass.add(new fGriefdefenderAPI(pl));break;
				//case "FabledSkyblock": protectionClass.add(new fFabledSkyblock(pl));
				case "Factions" : 
					if(pl.getDescription().getAuthors().stream().filter(b -> b.equalsIgnoreCase("drtshock")).findFirst().isPresent()) {
						protectionClass.add(new fFactionsUUID(pl));break;
					}else {
						protectionClass.add(new fFactions(pl));break;
					}
				case "PreciousStones" : protectionClass.add(new fPreciousStones(pl));break;
			default:break;
			}
		}
	}
	
	private String isVersionAvaiable(Plugin pl) {
		String str = "";
		try {
			Class<?> descriptionClass = PluginDescriptionFile.class;
			Field field = descriptionClass.getDeclaredField("apiVersion");
			if(Objects.nonNull(field)) {
				field.setAccessible(true);
				return (String) field.get(pl.getDescription());
			}
		}catch (Exception e) {
			return "";
		}
		return str;
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
