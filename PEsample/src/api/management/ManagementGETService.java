package api.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;

import javax.json.JsonArrayBuilder;
import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/management")
public class ManagementGETService {
	@Path("/get")
	@GET
	public String getMessage() throws SQLException, NamingException {
		return "Hello from api.management";
	}
	@Path("/resources")
	@GET
	public Response GetInfoDatabase(
			@DefaultValue("") @QueryParam("filter") String filter,
			@DefaultValue("") @QueryParam("choice") String choice 		
			)throws SQLException, NamingException {
		
		if (filter.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave filter empty").build();
		}
		
		Connection db = (Connection) Configuration.getAcademiaConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		try {
			switch(filter) {
			case "infodatabase":
				st = db.prepareStatement("{ call GetInfoDatabase(?) }");
				st.setString(1, choice);
				
				if (choice.isEmpty()) {					
					return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave choice empty").build();					
				}
				rs = st.executeQuery();
				while (rs.next()) {
					if(rs.getString(2)==null) {
						System.out.println("gotem");
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();

					}
					builder.add(Json.createObjectBuilder().add("Code", rs.getString(1)).add("Name", rs.getString(2)).build());
				}		
				break;
			case "AcaFac":
				st = db.prepareStatement("{ call getInfoYearFac() }");			
				rs = st.executeQuery();
				while (rs.next()) {
					builder.add(Json.createObjectBuilder().add("AFCode", rs.getString(1)).add("AYName", rs.getString(2)).add("FName", rs.getString(3)).build());
				}
				break;
			case "AcaFacPro":
				st = db.prepareStatement("{ call getInfoYearFacPro() }");
				rs = st.executeQuery();
				while (rs.next()) {
					builder.add(Json.createObjectBuilder().add("PFCode", rs.getString(1)).add("AYName", rs.getString(2)).add("FName", rs.getString(3)).add("PName", rs.getString(4)).build());
				}
				break;
			case "AcaFacProMod":
				st = db.prepareStatement("{ call getInfoYearFacProMod() }");
				rs = st.executeQuery();
				while (rs.next()) {
					builder.add(Json.createObjectBuilder()
							.add("PFCode", rs.getString(1))
							.add("MCode", rs.getString(2))
							.add("AYName", rs.getString(3))
							.add("FName", rs.getString(4))
							.add("PName", rs.getString(5))
							.add("MName", rs.getString(6)).build());
				}
				break;
			default:
				// catch invalid resource names.
				return Response.status(Response.Status.FORBIDDEN).entity("Invalid resources").build();				
			}
			
			// if no error arises.
			return Response.ok().entity(builder.build().toString()).build();
			
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			db.close();
		}			
	}
}
