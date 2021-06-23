package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashSet;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fFactions;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIridiumSkyblock;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fIslandWorld;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fMyChunk;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPreciousStones;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fRedProtect;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fResidence;

public class LocalLibary extends ProtectionModule{

	@Override
	public HashSet<Class<? extends protectionObj>> generatePluginMap() {
		HashSet<Class<? extends protectionObj>> protectetionMap = new HashSet<>();
		protectetionMap.add(fFactions.class);
		protectetionMap.add(fIridiumSkyblock.class);
		protectetionMap.add(fIslandWorld.class);
		protectetionMap.add(fMyChunk.class);
		protectetionMap.add(fRedProtect.class);
		protectetionMap.add(fResidence.class);
		protectetionMap.add(fPreciousStones.class);
		return protectetionMap;
	}

	
	
}
