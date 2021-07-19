package de.Ste3et_C0st.ProtectionLib.main;

import java.util.HashMap;

public abstract class ProtectionModule {

	public abstract HashMap<Class<? extends protectionObj>, ProtectionPluginFilter> generatePluginMap();
	
}
