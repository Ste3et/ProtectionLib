package de.Ste3et_C0st.ProtectionLib.main;


public class ConfigType<K> {

	private K object;
	private String name;
	
	public ConfigType(String name, K object) {
		this.name = name;
		this.object = object;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.getName();
	}
	
	public K getObject() {
		return this.object;
	}
	
	public void setObject(K object) {
		this.object = object;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		ConfigType<K> key = (ConfigType<K>) object;

		if (object != null ? !object.equals(key.getObject()) : key.getObject() != null) return false;
		return true;
	}

}
