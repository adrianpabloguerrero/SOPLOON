import ar.edu.unicen.isistan.si.soploon.server.client.SoploonClient;
import ar.edu.unicen.isistan.si.soploon.server.models.Project;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import ar.edu.unicen.isistan.si.soploon.server.models.User.Role;

public class Main {

	static final String URL = "jdbc:postgresql://192.168.2.101:5432/soploon";
	static final String USER = "soploon_admin";
	static final String PASS = "soploononon";
	
	public static void main(String[] args) {
		
		//SoploonClient wclient = new SoploonClient("https://si.isistan.unicen.edu.ar/soploon/api/");
		User user = new User();
		user.setId(133);
		user.setName("valle-223");
		user.setRole(Role.student);
		user.setPassword("3877cb08-71ec-42fd-87e1-15bab63d33b2");
		
		SoploonClient wclient = new SoploonClient("http://localhost:8080/soploon/api/",user);
		wclient.authenticate();
		
		
		Project project = new Project();
		project.setId(0);
		project.setName("test  project");
		project.setUserId(user.getId());
		
		Project out = wclient.postProject(project);
		System.out.println(out);
		// Users
//		System.out.println(wclient.getUsers());
//		System.out.println(wclient.getUser(1));
//
//		user.setCreationDate(System.currentTimeMillis());
//		System.out.println(wclient.postUser(user));
		
////		// Proyectos	
//		System.out.println(wclient.getProjects(1));
//		System.out.println(wclient.getProject(1,1));
////		// POST de proyecto
////		
////		// Correcciones
//		System.out.println(wclient.getCorrections(2, 3));
//		System.out.println(wclient.getCorrection(2, 3, 1569294000000L));
////		
////		// Errores
//		System.out.println(wclient.getErrors(2, 3, 1569294000000L));
//		System.out.println(wclient.getError(2, 3, 1569294000000L,6));
////		
////		// Reglas
//		//System.out.println(wclient.getRules());
//		//System.out.println(wclient.getRule(1));
//		//System.out.println(wclient.getRuleHistory(1));
//
//		// Predicados
//		//System.out.println(wclient.getPredicates());
		//System.out.println(wclient.getPredicate(2));
		//System.out.println(wclient.getPredicateHistory(2));

	}

}
