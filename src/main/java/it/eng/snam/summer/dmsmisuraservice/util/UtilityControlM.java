package it.eng.snam.summer.dmsmisuraservice.util;

public final class UtilityControlM {

	private UtilityControlM() {
		super();
	}
	
	public static final void setSslProperties(String password, String trustStorePath) {		
	    System.setProperty("javax.net.ssl.trustStore", trustStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", password);

	}
}
