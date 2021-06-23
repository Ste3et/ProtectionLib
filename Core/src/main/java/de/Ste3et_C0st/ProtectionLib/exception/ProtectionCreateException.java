package de.Ste3et_C0st.ProtectionLib.exception;

public class ProtectionCreateException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ProtectionCreateException (String message) {
        super (message);
    }

    public ProtectionCreateException (Throwable cause) {
        super (cause);
    }

    public ProtectionCreateException (String message, Throwable cause) {
        super (message, cause);
    }
}
