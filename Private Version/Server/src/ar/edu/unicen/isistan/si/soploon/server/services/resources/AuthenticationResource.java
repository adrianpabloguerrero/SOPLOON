package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.unicen.isistan.si.soploon.server.database.AuthenticationDao;
import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class AuthenticationResource {
	
	Database database;
	AuthenticationDao dao;
	
	public AuthenticationResource (Database database) {
		this.database = database;
		this.dao = new AuthenticationDao(this.database);
	}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("userName") String username, 
                                     @FormParam("password") String password) {

        try {
            // Authenticate the user using the credentials provided
            authenticate(username, password);
            // Issue a token for the user
            String token = issueToken(username);
            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
        User usuario =  this.dao.getUser(username);
        if ((usuario == null) || (!usuario.getPassword().equals(password)))
            throw new Exception ("Usuario o pass incorrecta");
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	String key = "SoploonKey";
    	String token = Jwts.builder()
    					.signWith(SignatureAlgorithm.HS512, key)
    					.setSubject(username)
    					.setIssuedAt(new Date(System.currentTimeMillis()))
    					.setExpiration(new Date(System.currentTimeMillis() + 900000))
    					.compact();
    	return token;
    }
}