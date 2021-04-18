package api.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/login")
public class LoginGETService {
	
	
	@Path("/get")
	@GET
	public String getMessage() {
		return "Hello from api.login";
	}
	
	@Path("/{username}/role") 
	@GET
	public Response getRole(@PathParam("username") String username) throws SQLException, NamingException{
		Connection db = Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call CheckRole(?) }");
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();
			JsonArrayBuilder builder = Json.createArrayBuilder();
			while (rs.next()) {
				builder.add(Json.createObjectBuilder()
						.add("faculty", rs.getString(1))
						.add("program", rs.getString(2)).build());
			}
			return Response.ok().entity(builder.build().toString()).build();
		} finally {
			db.close();
		}				
	}
	
}
