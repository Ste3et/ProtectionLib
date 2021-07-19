package de.Ste3et_C0st.ProtectionLib.main;

import java.util.function.Predicate;

import org.bukkit.plugin.PluginDescriptionFile;

public class ProtectionPluginFilter {

	private final String pluginName;
	private Predicate<PluginDescriptionFile> fileFilter;
	
	public ProtectionPluginFilter(String pluginName) {
		this(pluginName, file -> file.getName().equalsIgnoreCase(pluginName));
	}
	
	public ProtectionPluginFilter isVersion(int version) {
		Predicate<PluginDescriptionFile> fileFilter = file -> file.getVersion().startsWith(version + ".");
		this.fileFilter = this.fileFilter.and(fileFilter);
		return this;
	}
	
	public ProtectionPluginFilter containsAuthor(String author) {
		return containsAuthor(author, true);
	}
	
	public ProtectionPluginFilter containsAuthor(String author, boolean bool) {
		Predicate<PluginDescriptionFile> fileFilter = file -> file.getAuthors().contains(author) == bool;
		this.fileFilter = this.fileFilter.and(fileFilter);
		return this;
	}
	
	public ProtectionPluginFilter(String pluginname, Predicate<PluginDescriptionFile> fileFilter) {
		this.pluginName = pluginname;
		this.fileFilter = fileFilter;
	}

	public Predicate<PluginDescriptionFile> getFileFilter() {
		return fileFilter;
	}

	public String getPluginName() {
		return pluginName;
	}
	
}
