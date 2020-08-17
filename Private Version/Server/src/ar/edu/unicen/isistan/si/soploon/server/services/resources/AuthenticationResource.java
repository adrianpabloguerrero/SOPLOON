package ar.edu.unicen.isistan.si.soploon.server.services.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.digest.DigestUtils;

import ar.edu.unicen.isistan.si.soploon.server.database.AuthenticationDao;
import ar.edu.unicen.isistan.si.soploon.server.database.Database;
import ar.edu.unicen.isistan.si.soploon.server.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationResource {
	
	private static final long ONE_HOUR = 3600000L;
	
	private Database database;
	private AuthenticationDao dao;
	private String key;
	
	public AuthenticationResource (Database database, String key) {
		this.database = database;
		this.dao = new AuthenticationDao(this.database);
		this.key = key;
	}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("userId") Long userID, @FormParam("userName") String userName, @FormParam("password") String password) {
    	try {
    		// Authenticate the user using the credentials provided
    		if (userID != null)
    			authenticate(userID, password);
    		else 
    			authenticate(userName, password);
            
            // Issue a token for the user
            String token = issueToken(userName);
            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }

    private void authenticate(String userName, String password) throws Exception {
        User usuario =  this.dao.getUser(userName);
        if ((usuario == null) || (!DigestUtils.sha256Hex(password).equals(usuario.getPassword())))
            throw new Exception ("Usuario o pass incorrecta");
    }

    private void authenticate(Long userID, String password) throws Exception {
        User usuario =  this.dao.getUser(userID);
        if ((usuario == null) || (!DigestUtils.sha256Hex(password).equals(usuario.getPassword())))
            throw new Exception ("Usuario o pass incorrecta");
    }
    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	String token = Jwts.builder()
    					.signWith(SignatureAlgorithm.HS256, this.key)
    					.setSubject(username)
    					.setIssuedAt(new Date(System.currentTimeMillis()))
    					.setExpiration(new Date(System.currentTimeMillis() + ONE_HOUR))
    					.compact();
    	return token;
    }
}