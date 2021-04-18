package api.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
			JsonObjectBuilder builder = Json.createObjectBuilder();
			if (rs.next()) {
				if(rs.getString(1)==null) {
					builder.add("faculty", "");
				}
				else {
					builder.add("faculty", rs.getString(1));
				}
				if(rs.getString(2)==null) {
					builder.add("program", "");
				}
				else {
					builder.add("program", rs.getString(2));
				}
			}
			PreparedStatement st2 = db.prepareStatement("{ call CheckLecturer(?) }");
			st2.setString(1, username);
			
			ResultSet rs2 = st2.executeQuery();
			JsonArrayBuilder builder2= Json.createArrayBuilder();
			while (rs2.next()) {
				if(rs2.getString(1)==null) {
					break;
				}
				builder2.add(rs2.getString(1));
			}
			builder.add("lecturer", builder2.build());
			return Response.ok().entity(builder.build().toString()).build();
		} finally {
			db.close();
		}				
	}
	
}
