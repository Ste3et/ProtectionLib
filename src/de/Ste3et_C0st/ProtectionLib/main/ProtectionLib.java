package de.Ste3et_C0st.ProtectionLib.main;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectionLib extends JavaPlugin {
	
	List<ProtectionClass> protectList = new ArrayList<ProtectionClass>();
	private static ProtectionLib instance;
	public static ProtectionLib getInstance(){return instance;}
	private boolean isVaultEnable = false;
	Plugin pWorldGuard, pPlotz, pPlotSquared, pPlotMe, pTowny, pGriefPrevention, pLandLord, puSkyBlock, paSkyBlock, pRedProtect, pResidence;
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
	public boolean canBuild(Location loc, Player p){
		if(hasPermissions(p)) return true;
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true;
		if(pWorldGuard !=null) fWG = new fWorldGuard(loc, p).canBuild(pWorldGuard);
		if(pPlotz!=null)fPlotz = new fPlotz(loc, p).canBuild(pPlotz);
		if(pPlotSquared!=null)fPlotSquared = new fPlotSquared(loc, p).canBuild(pPlotSquared);
		if(pPlotMe!=null) fPlotMe = new fPlotMe(loc, p).canBuild(pPlotMe);
		if(pTowny!=null) fTowny = new fTowny(loc, p).canBuild(pTowny);
		if(pGriefPrevention!=null) fGriefPrevention = new fGriefPrevention(loc, p).canBuild(pGriefPrevention);
		if(pLandLord!=null) fLandLord = new fLandLord(loc, p).canBuild(pLandLord);
		if(puSkyBlock!=null) fuSkyBlock = new fuSkyblock(loc, p).canBuild(puSkyBlock);
		if(paSkyBlock!=null) faSkyBlock = new faSkyBlock(loc, p).canBuild(paSkyBlock);
		if(pRedProtect!=null) fRedProtect = new fRedProtect(loc, p).canBuild(pRedProtect);
		if(pResidence!=null) fResidence = new fResidence(loc, p).canBuild(pResidence);
		
		if(debug){
			p.sendMessage("WorldGuard:" + fWG);
			p.sendMessage("Plotz:" + fPlotz);
			p.sendMessage("PlotSquared:" + fPlotSquared);
			p.sendMessage("PlotMe:" + fPlotMe);
			p.sendMessage("Towny:" + fTowny);
			p.sendMessage("GriefPrevention:" + fGriefPrevention);
			p.sendMessage("LandLord:" + fLandLord);
			p.sendMessage("uSkyBlock:" + fuSkyBlock);
			p.sendMessage("aSkyBlock:" + faSkyBlock);
			p.sendMessage("RedProtect:" + fRedProtect);
			p.sendMessage("Residence:" + fResidence);
		}
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence){
			return true;
		}
		return false;
	}
	
	public boolean isOwner(Location loc, Player p){
		if(hasPermissions(p)) return true;
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true;
		if(pWorldGuard !=null) fWG = new fWorldGuard(loc, p).isOwner(pWorldGuard);
		if(pPlotz!=null)fPlotz = new fPlotz(loc, p).isOwner(pPlotz);
		if(pPlotSquared!=null)fPlotSquared = new fPlotSquared(loc, p).isOwner(pPlotSquared);
		if(pPlotMe!=null) fPlotMe = new fPlotMe(loc, p).isOwner(pPlotMe);
		if(pTowny!=null) fTowny = new fTowny(loc, p).isOwner(pTowny);
		if(pGriefPrevention!=null) fGriefPrevention = new fGriefPrevention(loc, p).isOwner(pGriefPrevention);
		if(pLandLord!=null) fLandLord = new fLandLord(loc, p).isOwner(pLandLord);
		if(puSkyBlock!=null) fuSkyBlock = new fuSkyblock(loc, p).isOwner(puSkyBlock);
		if(paSkyBlock!=null) faSkyBlock = new faSkyBlock(loc, p).isOwner(paSkyBlock);
		if(pRedProtect!=null) fRedProtect = new fRedProtect(loc, p).isOwner(pRedProtect);
		if(pResidence!=null) fResidence = new fResidence(loc, p).isOwner(pResidence);		
		
		if(debug){
			p.sendMessage("WorldGuard:" + fWG);
			p.sendMessage("Plotz:" + fPlotz);
			p.sendMessage("PlotSquared:" + fPlotSquared);
			p.sendMessage("PlotMe:" + fPlotMe);
			p.sendMessage("Towny:" + fTowny);
			p.sendMessage("GriefPrevention:" + fGriefPrevention);
			p.sendMessage("LandLord:" + fLandLord);
			p.sendMessage("uSkyBlock:" + fuSkyBlock);
			p.sendMessage("aSkyBlock:" + faSkyBlock);
			p.sendMessage("RedProtect:" + fRedProtect);
			p.sendMessage("Residence:" + fResidence);
		}
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence){
			return true;
		}
		return false;
	}
}
