package ar.edu.unicen.isistan.si.soploon.plugin.api;

import ar.edu.unicen.isistan.si.soploon.server.client.WebClient;

public class SoploonApi {
	
	private final String BASE_PATH = "http://localhost:8080/soploon/api/";
	
	//private Client client;
	
	public SoploonApi() {
		WebClient wc = new WebClient(BASE_PATH);
	}
	
}
