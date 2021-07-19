package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashMap;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionPluginFilter;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquaredV4;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fWorldGuardV6;

public class PlotSquaredV4Module extends ProtectionModule{

	@Override
	public HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> generatePluginMap() {
		HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> protectetionMap = new HashMap<>();
		protectetionMap.put(fPlotSquaredV4.class, new ProtectionPluginFilter("PlotSquared").isVersion(4));
		protectetionMap.put(fWorldGuardV6.class, new ProtectionPluginFilter("WorldGuard").isVersion(6));
		return protectetionMap;
	}
	
}
