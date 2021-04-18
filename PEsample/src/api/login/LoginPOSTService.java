package api.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.crypto.SecretKey;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/login")
public class LoginPOSTService {
	
	private String issueToken(User authUser) throws NamingException {
		SecretKey key = Configuration.getSecretKey();
        String authToken = Jwts.builder()
	       	.claim("role", authUser.getRole())
	       	.signWith(SignatureAlgorithm.HS512, key)
	       	.compact();
        return authToken;	
	}
	
	@Path("/user")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) throws SQLException, NamingException {
		Connection db = Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call CheckLogin(?,?) }");
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			
			int flag = st.executeQuery().getInt(1);
			if (flag == 0) { 
				return Response.status(Response.Status.FORBIDDEN).entity("Invalid username or password").build();				
			}
			
			String authToken = this.issueToken(user);
			NewCookie cookie = new NewCookie("pesample", authToken, "/", "", "auth_token", NewCookie.DEFAULT_MAX_AGE, false);

			return Response.ok("OK").cookie(cookie).build();			
		} finally {
			db.close();
		}			
	}
}
