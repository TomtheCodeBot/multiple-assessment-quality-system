package api.questionaire;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.Classes;

@Path("/programs")
public class DataTable {
	
	@Path("/table")
	@GET
	public Response getProgramsCount() throws SQLException, NamingException   {
		
		Connection db = Configuration.getAcademiaConnection();
		try {
			JsonArrayBuilder jsonArray =Json.createArrayBuilder();
			PreparedStatement st = db.prepareStatement(
					"call getTableOfData(NULL,NULL,NULL,NULL,NULL,NULL,NULL)"
					);
			ResultSet rs = st.executeQuery();
			List<Classes> data = new ArrayList<Classes>();
			while (rs.next()) {
		        data.add(new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));
		        jsonArray.add((javax.json.JsonValue) Json.createObjectBuilder().add("academic_year", rs.getString(1))
		        													.add("Faculty", rs.getString(2))
		        													.add("Lecturer", rs.getString(3))
		        													.add("Module", rs.getString(4))
		        													.add("Semester", rs.getString(5))
		        													.add("Class", rs.getString(6))
		        													.add("Programm", rs.getString(7)).build());
		        }
			JsonArray retArray = jsonArray.build();
		return Response.ok().entity(retArray.toString()).build();
		}
		finally {
			db.close();
		}
	}
}