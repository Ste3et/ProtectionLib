package de.Ste3et_C0st.ProtectionLib.main.module;

import java.util.HashMap;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionModule;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionPluginFilter;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;
import de.Ste3et_C0st.ProtectionLib.main.plugins.fPlotSquaredV6;

public class PlotSquaredV6Module  extends ProtectionModule{

	@Override
	public HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> generatePluginMap() {
		HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> protectetionMap = new HashMap<>();
		protectetionMap.put(fPlotSquaredV6.class, new ProtectionPluginFilter("PlotSquared").isVersion(6));
		return protectetionMap;
	}
	
}
