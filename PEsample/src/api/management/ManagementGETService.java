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
					String code = rs.getString(1);
					String name = rs.getString(2);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder().add("Code", rs.getString(1)).add("Name", rs.getString(2)).build());
				}		
				break;
			case "AcaFac":
				st = db.prepareStatement("{ call getInfoYearFac() }");			
				rs = st.executeQuery();
				while (rs.next()) {
					String afCode = rs.getString(1);
					String ayName = rs.getString(2);
					String fName = rs.getString(3);
					
					if (rs.wasNull()) {
						// if null values are returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();					
					}
					
					builder.add(Json.createObjectBuilder().add("AFCode", afCode).add("AYName", ayName).add("FName", fName).build());
				}
				break;
			case "AcaFacPro":
				st = db.prepareStatement("{ call getInfoYearFacPro() }");
				rs = st.executeQuery();
				while (rs.next()) {
					String pfCode = rs.getString(1);
					String ayName = rs.getString(2);
					String fName = rs.getString(3);
					String pName = rs.getString(4);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder().add("PFCode", pfCode).add("AYName", ayName).add("FName", fName).add("PName", pName).build());
				}
				break;
			case "AcaFacProMod":
				st = db.prepareStatement("{ call getInfoYearFacProMod() }");
				rs = st.executeQuery();
				while (rs.next()) {
					String pfCode = rs.getString(1);
					String mCode = rs.getString(2);
					String ayName = rs.getString(3);
					String fName = rs.getString(4);
					String pName = rs.getString(5);
					String mName = rs.getString(6);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("PFCode", pfCode)
							.add("MCode", mCode)
							.add("AYName", ayName)
							.add("FName", fName)
							.add("PName", pName)
							.add("MName", mName).build());
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
