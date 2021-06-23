package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashSet;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotsquaredV5;

public class PlotSquaredV5Module extends ProtectionModule{

	@Override
	public HashSet<Class<? extends protectionObj>> generatePluginMap() {
		HashSet<Class<? extends protectionObj>> protectetionMap = new HashSet<>();
		protectetionMap.add(fPlotsquaredV5.class);
		return protectetionMap;
	}
	
}
