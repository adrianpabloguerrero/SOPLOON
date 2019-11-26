import ar.edu.unicen.isistan.si.soploon.server.client.WebClient;

public class Main {

	static final String URL = "jdbc:postgresql://192.168.2.101:5432/soploon";
	static final String USER = "soploon_admin";
	static final String PASS = "soploononon";
	
	public static void main(String[] args) {
		
		WebClient wclient = new WebClient("http://localhost:8080/soploon/api/");
		System.out.println(wclient.getUsers());
		System.out.println(wclient.getUser(1));
		System.out.println(wclient.getProjects(1));
	}

}
