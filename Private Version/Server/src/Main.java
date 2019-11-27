import ar.edu.unicen.isistan.si.soploon.server.client.WebClient;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.User.Role;

public class Main {

	static final String URL = "jdbc:postgresql://192.168.2.101:5432/soploon";
	static final String USER = "soploon_admin";
	static final String PASS = "soploononon";
	
	public static void main(String[] args) {
		
		WebClient wclient = new WebClient("http://localhost:8080/soploon/api/");
		// Users
		System.out.println(wclient.getUsers());
		System.out.println(wclient.getUser(1));
		//User user = new User();
		//user.setName("mi nombre");
		//user.setRole(Role.student);
		//System.out.println(wclient.postUser(user));
		
		// Proyectos	
		System.out.println(wclient.getProjects(1));
		System.out.println(wclient.getProject(1,1));
		// POST de proyecto
		
		// Correcciones
		
		// Errores
		
		
		// Reglas
		System.out.println(wclient.getRules());
		System.out.println(wclient.getRule(1));
		System.out.println(wclient.getRuleHistory(1));

		// Predicados
		System.out.println(wclient.getPredicates());
		System.out.println(wclient.getPredicate(2));
		System.out.println(wclient.getPredicateHistory(2));

	}

}
