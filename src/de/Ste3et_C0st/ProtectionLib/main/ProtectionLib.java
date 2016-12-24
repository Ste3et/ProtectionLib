package de.Ste3et_C0st.ProtectionLib.main;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectionLib extends JavaPlugin{
	
	List<ProtectionClass> protectList = new ArrayList<ProtectionClass>();
	private static JavaPlugin instance;
	public static JavaPlugin getInstance(){return instance;}
	private boolean isVaultEnable = false;
	private faSkyBlock skyBlock;
	private fGriefPrevention GriefPrevention;
	private fLandLord landLord;
	private fPlotMe plotMe;
	private fPlotSquared plotsquared;
	private fPlotz plotz;
	private fRedProtect redProtect;
	private fResidence residence;
	private fTowny towny;
	private fuSkyblock uSkyBlock;
	private fWorldGuard worldGuard;
	private fPreciousStones PreciousStones;
	private fKingdoms Kingdoms;
	
	
	Plugin pWorldGuard, pPlotz, pPlotSquared, pPlotMe, pTowny, pGriefPrevention, pLandLord, puSkyBlock, paSkyBlock, pRedProtect, pResidence, pPreciousStones, pKingdoms;
	
	ProtectionVaultPermission permissions = null;
	@Override
	public void onEnable(){
		instance = this;
		addProtectionPlugin("WorldGuard");
		addProtectionPlugin("Plotz");
		addProtectionPlugin("PlotSquared");
		addProtectionPlugin("PlotMe");
		addProtectionPlugin("Towny");
		addProtectionPlugin("GriefPrevention");
		addProtectionPlugin("Landlord");
		addProtectionPlugin("uSkyBlock");
		addProtectionPlugin("aSkyBlock");
		addProtectionPlugin("RedProtect");
		addProtectionPlugin("Residence");
		addProtectionPlugin("Kingdoms");
		
		pWorldGuard = isLoadet("WorldGuard");
		pPlotz = isLoadet("Plotz");
		pPlotSquared = isLoadet("PlotSquared");
		pPlotMe = isLoadet("PlotMe");
		pTowny = isLoadet("Towny");
		pGriefPrevention = isLoadet("GriefPrevention");
		pLandLord = isLoadet("Landlord");
		puSkyBlock = isLoadet("uSkyBlock");
		paSkyBlock = isLoadet("aSkyBlock");
		pRedProtect = isLoadet("RedProtect");
		pResidence = isLoadet("Residence");
		pKingdoms = isLoadet("Kingdoms");
		pPreciousStones = isLoadet("PreciousStones");
		
		if(pWorldGuard!=null) worldGuard = new fWorldGuard(this);
		if(puSkyBlock!=null) uSkyBlock = new fuSkyblock(this);
		if(pPlotz!=null) plotz = new fPlotz(getInstance());
		if(pPlotSquared!=null) plotsquared = new fPlotSquared(getInstance());
		if(pPlotMe!=null) plotMe = new fPlotMe(getInstance());
		if(pTowny!=null) towny = new fTowny(getInstance());
		if(pGriefPrevention!=null) GriefPrevention = new fGriefPrevention(getInstance());
		if(pLandLord!=null) landLord = new fLandLord(getInstance());
		if(paSkyBlock!=null) skyBlock = new faSkyBlock(getInstance());
		if(pRedProtect!=null) redProtect = new fRedProtect(getInstance());
		if(pResidence!=null) residence = new fResidence(getInstance());
		if(pPreciousStones!=null) PreciousStones = new fPreciousStones(getInstance());
		if(pKingdoms!=null) Kingdoms = new fKingdoms(getInstance());
		
		if(Bukkit.getPluginManager().isPluginEnabled("Vault")){
			isVaultEnable = true;
			permissions = new ProtectionVaultPermission();
		}
	}
	
	public void onDisable(){
		
	}
	
	private void addProtectionPlugin(String a){
		ProtectionClass ppL = new ProtectionClass(a);
		protectList.add(ppL);
	}
	
	public boolean hasPermissions(Player p){
		if(p.isOp()) return true;
		boolean b = p.hasPermission("ProtectionLib.admin");
		if(isVaultEnable){
			b = permissions.permission.has(p, "ProtectionLib.admin");
		}
		return b;
	}
	
	private Plugin isLoadet(String s){
		for(ProtectionClass ppL : protectList){
			if(ppL.getName().equalsIgnoreCase(s) && ppL.isLoaded()){
				return ppL.getPlugin();
			}
		}
		return null;
	}
	boolean debug = false;
	public boolean canBuild(Location loc, Player player){
		if(hasPermissions(player)) return true;
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true, fPreciousStones = true, fKingdoms = true;
		
		if(pWorldGuard!=null)fWG = worldGuard.canBuild(pWorldGuard, player, loc);
		if(pPlotz!=null)fPlotz = plotz.canBuild(pPlotz, player, loc);
		if(pPlotSquared!=null)fPlotSquared = plotsquared.canBuild(pPlotSquared, player, loc);
		if(pPlotMe!=null)fPlotMe = plotMe.canBuild(pPlotMe, player, loc);
		if(pTowny!=null)fTowny = towny.canBuild(pTowny, player, loc);
		if(pGriefPrevention!=null)fGriefPrevention = GriefPrevention.canBuild(pGriefPrevention, player, loc);
		if(pLandLord!=null)fLandLord = landLord.canBuild(pLandLord, player, loc);
		if(puSkyBlock!=null)fuSkyBlock = uSkyBlock.canBuild(puSkyBlock, player, loc);
		if(paSkyBlock!=null)faSkyBlock = skyBlock.canBuild(paSkyBlock, player, loc);
		if(pRedProtect!=null)fRedProtect = redProtect.canBuild(pRedProtect, player, loc);
		if(pResidence!=null)fResidence = residence.canBuild(pResidence, player, loc);
		if(pPreciousStones!=null) fPreciousStones = PreciousStones.canBuild(pPreciousStones, player, loc);
		if(pKingdoms!=null) fKingdoms = Kingdoms.canBuild(pKingdoms, player, loc);
		if(debug){
			player.sendMessage("WorldGuard:" + fWG);
			player.sendMessage("Plotz:" + fPlotz);
			player.sendMessage("PlotSquared:" + fPlotSquared);
			player.sendMessage("PlotMe:" + fPlotMe);
			player.sendMessage("Towny:" + fTowny);
			player.sendMessage("GriefPrevention:" + fGriefPrevention);
			player.sendMessage("LandLord:" + fLandLord);
			player.sendMessage("uSkyBlock:" + fuSkyBlock);
			player.sendMessage("aSkyBlock:" + faSkyBlock);
			player.sendMessage("RedProtect:" + fRedProtect);
			player.sendMessage("Residence:" + fResidence);
			player.sendMessage(("PreciousStones" + fPreciousStones));
		}
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence&&fPreciousStones&&fKingdoms){
			return true;
		}
		return false;
	}
	
	public boolean isOwner(Location loc, Player player){
		if(hasPermissions(player)) return true;
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true, fPreciousStones = true, fKingdoms = true;
		
		if(pWorldGuard!=null)fWG = worldGuard.isOwner(pWorldGuard, player, loc);
		if(pPlotz!=null)fPlotz = plotz.isOwner(pPlotz, player, loc);
		if(pPlotSquared!=null)fPlotSquared = plotsquared.isOwner(pPlotSquared, player, loc);
		if(pPlotMe!=null)fPlotMe = plotMe.isOwner(pPlotMe, player, loc);
		if(pTowny!=null)fTowny = towny.isOwner(pTowny, player, loc);
		if(pGriefPrevention!=null)fGriefPrevention = GriefPrevention.isOwner(pGriefPrevention, player, loc);
		if(pLandLord!=null)fLandLord = landLord.isOwner(pLandLord, player, loc);
		if(puSkyBlock!=null)fuSkyBlock = uSkyBlock.isOwner(puSkyBlock, player, loc);
		if(paSkyBlock!=null)faSkyBlock = skyBlock.isOwner(paSkyBlock, player, loc);
		if(pRedProtect!=null)fRedProtect = redProtect.isOwner(pRedProtect, player, loc);
		if(pPreciousStones!=null) fPreciousStones = PreciousStones.isOwner(pPreciousStones, player, loc);
		if(pKingdoms!=null) fKingdoms = Kingdoms.isOwner(pKingdoms, player, loc);
		if(debug){
			player.sendMessage("WorldGuard:" + fWG);
			player.sendMessage("Plotz:" + fPlotz);
			player.sendMessage("PlotSquared:" + fPlotSquared);
			player.sendMessage("PlotMe:" + fPlotMe);
			player.sendMessage("Towny:" + fTowny);
			player.sendMessage("GriefPrevention:" + fGriefPrevention);
			player.sendMessage("LandLord:" + fLandLord);
			player.sendMessage("uSkyBlock:" + fuSkyBlock);
			player.sendMessage("aSkyBlock:" + faSkyBlock);
			player.sendMessage("RedProtect:" + fRedProtect);
			player.sendMessage("Residence:" + fResidence);
			player.sendMessage(("PreciousStones" + fPreciousStones));
			player.sendMessage(("Kindoms" + fKingdoms));
		}
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence&&fPreciousStones&&fKingdoms){
			return true;
		}
		return false;
	}
}
