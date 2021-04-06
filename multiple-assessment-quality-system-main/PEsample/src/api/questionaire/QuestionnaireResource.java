package api.questionaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/programs")
public class QuestionnaireResource {
	
	@Path("/count")
	@GET
	public Response getProgramsCount() throws SQLException, NamingException   {
		
		Connection db = Configuration.getAcademiaConnection();
		try {
			
			PreparedStatement st = db.prepareStatement(
					"SELECT COUNT(*) as count FROM program"
					);
			ResultSet rs = st.executeQuery();
			
			int count = 0;
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			
		
		JsonObject entry = Json.createObjectBuilder()
				.add("count", Integer.toString(count)).build();
				
		return Response.ok().entity(entry.toString()).build();
		
		}
		finally {
			db.close();
		}
	}
}