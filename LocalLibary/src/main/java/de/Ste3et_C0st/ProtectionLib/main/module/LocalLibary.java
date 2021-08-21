package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashMap;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionPluginFilter;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fAreaShop;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFactions;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIridiumSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIslandWorld;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fMyChunk;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPreciousStones;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fRedProtect;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fResidence;
import de.Ste3et_C0st.ProtectionLib.main.plugins.faSkyBlock;

public class LocalLibary extends ProtectionModule{

	@Override
	public HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> generatePluginMap() {
		HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> protectetionMap = new HashMap<>();
		protectetionMap.put(fFactions.class, new ProtectionPluginFilter("Factions").containsAuthor("drtshock", false));
		protectetionMap.put(fIridiumSkyblock.class, new ProtectionPluginFilter("IridiumSkyblock"));
		protectetionMap.put(fIslandWorld.class, new ProtectionPluginFilter("IslandWorld"));
		protectetionMap.put(fMyChunk.class, new ProtectionPluginFilter("MyChunk"));
		protectetionMap.put(fRedProtect.class, new ProtectionPluginFilter("RedProtect"));
		protectetionMap.put(fResidence.class, new ProtectionPluginFilter("Residence"));
		protectetionMap.put(fPreciousStones.class, new ProtectionPluginFilter("PreciousStone"));
		protectetionMap.put(fAreaShop.class, new ProtectionPluginFilter("AreaShop"));
		protectetionMap.put(faSkyBlock.class, new ProtectionPluginFilter("aSkyBlock"));
		return protectetionMap;
	}

	
	
}
