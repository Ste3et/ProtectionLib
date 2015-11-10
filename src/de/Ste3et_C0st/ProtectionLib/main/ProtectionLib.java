package de.Ste3et_C0st.ProtectionLib.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectionLib extends JavaPlugin {
	
	List<ProtectionClass> protectList = new ArrayList<ProtectionClass>();
	private static ProtectionLib instance;
	public static ProtectionLib getInstance(){return instance;}
	Plugin pWorldGuard, pPlotz, pPlotSquared, pPlotMe, pTowny, pGriefPrevention, pLandLord, puSkyBlock, paSkyBlock, pRedProtect, pResidence;
	
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
	}
	
	public void onDisable(){
		
	}
	
	private void addProtectionPlugin(String a){
		ProtectionClass ppL = new ProtectionClass(a);
		protectList.add(ppL);
	}
	
	private Plugin isLoadet(String s){
		for(ProtectionClass ppL : protectList){
			if(ppL.getName().equalsIgnoreCase(s) && ppL.isLoaded()){
				return ppL.getPlugin();
			}
		}
		return null;
	}
	
	public boolean canBuild(Location loc, Player p){
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true;
		if(pWorldGuard !=null) fWG = new fWorldGuard(loc, p).canBuild(pWorldGuard);
		if(pPlotz!=null)fPlotz = new fPlotz(loc, p).canBuild(pPlotz);
		if(pPlotSquared!=null)fPlotSquared = new fPlotSquared(loc, p).canBuild(pPlotz);
		if(pPlotMe!=null) fPlotMe = new fPlotMe(loc, p).canBuild(pPlotMe);
		if(pTowny!=null) fTowny = new fTowny(loc, p).canBuild(pTowny);
		if(pGriefPrevention!=null) fGriefPrevention = new fGriefPrevention(loc, p).canBuild(pGriefPrevention);
		if(pLandLord!=null) fLandLord = new fLandLord(loc, p).canBuild(pLandLord);
		if(puSkyBlock!=null) fuSkyBlock = new fuSkyblock(loc, p).canBuild(puSkyBlock);
		if(paSkyBlock!=null) faSkyBlock = new faSkyBlock(loc, p).canBuild(paSkyBlock);
		if(pRedProtect!=null) fRedProtect = new fRedProtect(loc, p).canBuild(pRedProtect);
		if(pResidence!=null) fResidence = new fResidence(loc, p).canBuild(pResidence);
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence){
			return true;
		}
		return false;
	}
	
	public boolean isOwner(Location loc, Player p){
		boolean fWG = true, fPlotz = true, fPlotSquared = true, fPlotMe = true, 
				fTowny = true, fGriefPrevention = true, fLandLord = true, fuSkyBlock = true,
				faSkyBlock = true, fRedProtect = true, fResidence = true;
		if(pWorldGuard !=null) fWG = new fWorldGuard(loc, p).isOwner(pWorldGuard);
		if(pPlotz!=null)fPlotz = new fPlotz(loc, p).isOwner(pPlotz);
		if(pPlotSquared!=null)fPlotSquared = new fPlotSquared(loc, p).isOwner(pPlotz);
		if(pPlotMe!=null) fPlotMe = new fPlotMe(loc, p).isOwner(pPlotMe);
		if(pTowny!=null) fTowny = new fTowny(loc, p).isOwner(pTowny);
		if(pGriefPrevention!=null) fGriefPrevention = new fGriefPrevention(loc, p).isOwner(pGriefPrevention);
		if(pLandLord!=null) fLandLord = new fLandLord(loc, p).isOwner(pLandLord);
		if(puSkyBlock!=null) fuSkyBlock = new fuSkyblock(loc, p).isOwner(puSkyBlock);
		if(paSkyBlock!=null) faSkyBlock = new faSkyBlock(loc, p).isOwner(paSkyBlock);
		if(pRedProtect!=null) fRedProtect = new fRedProtect(loc, p).isOwner(pRedProtect);
		if(pResidence!=null) fResidence = new fResidence(loc, p).isOwner(pResidence);		
		
		if(fWG&&fPlotz&&fPlotSquared&&fPlotMe&&fTowny&&fGriefPrevention&&fLandLord&&fuSkyBlock&&faSkyBlock&&fRedProtect&&fResidence){
			return true;
		}
		return false;
	}
}
