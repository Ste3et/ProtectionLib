package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashMap;
import java.util.HashSet;

import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionPluginFilter;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquaredV3;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fWorldGuardV5;

public class PlotSquaredV3Module extends ProtectionModule{

	@Override
	public HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> generatePluginMap() {
		HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> protectetionMap = new HashMap<>();
		protectetionMap.put(fPlotSquaredV3.class, new ProtectionPluginFilter("PlotSquared").isVersion(3));
		protectetionMap.put(fWorldGuardV5.class, new ProtectionPluginFilter("WorldGuard").isVersion(5));
		return protectetionMap;
	}
	
}
