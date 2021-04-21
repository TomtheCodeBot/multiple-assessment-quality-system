package api.questionaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/questionaire")
public class QuestionaireGETService {
	@Path("/get")
	@GET
	public String getMessage() throws SQLException, NamingException   {
		return "Hello Package Api.Questionaire GET Service!";
	}	
	
	@Path("/classes")
	@GET
	public Response getClasses()throws SQLException, NamingException{
		Connection db = Configuration.getAcademiaConnection();		
		try {
			PreparedStatement st = db.prepareStatement("call GetClasses()");
			ResultSet rs = st.executeQuery();
			JsonArrayBuilder CName = Json.createArrayBuilder();
			
			
			while(rs.next()) {
				String cName = rs.getString(1);
				
				if (rs.wasNull()) {
					// if null values are returned
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				CName.add( Json.createObjectBuilder()									
						.add("Class", cName).build());
			}
			
			
			
			JsonArray entry = CName.build();
			return Response.ok().entity(entry.toString()).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build(); 
		}
		finally {
			db.close();			
		}
	}

	@Path("/info/{CName}")
	@GET
	public Response getClassInfo(@PathParam("CName") String id) throws SQLException, NamingException{
		if (id.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave class name empty").build(); 
		}
		
		Connection db = Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement(
					"{ call GetClassesInfo(?) }");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();		
			JsonObjectBuilder builder = Json.createObjectBuilder();
			if(rs.next()) {
				String acaName = rs.getString(1);
				String sName = rs.getString(2);
				String fName = rs.getString(3);
				String pName = rs.getString(4);
				String mName = rs.getNString(5);
				
				if (rs.wasNull()) {
					// if null values are returned
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				
				builder.add("academic_name", acaName)
				.add("semester_name", sName)
				.add("faculty_name", fName)
				.add("program_name", pName)
				.add("module_name", mName);
			} else {
				// if there is no row returned 
				return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
			}
			
			JsonObject entry = builder.build();
			return Response.ok().entity(entry.toString()).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		finally {
			db.close();
		}
	}
	@Path("/lecturer/{CName}")
	@GET
	public Response GetClassesLecturer(@PathParam("CName") String CName) throws SQLException, NamingException{		
		if (CName.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave class name empty").build(); 
		}
		
		Connection db = Configuration.getAcademiaConnection();
		try {
			JsonArrayBuilder classInfoArrayBuilder = Json.createArrayBuilder();
			
			PreparedStatement st = db.prepareStatement(
					"{ call GetClassesLecturer(?) }");
			st.setString(1, CName);
			ResultSet rs = st.executeQuery();		
			while (rs.next()) {
				String lName = rs.getString(1);
				
				if (rs.wasNull()) {
					// if null values are returned
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				JsonObject entry = Json.createObjectBuilder()									
						.add("Lecturer_Name", lName).build();
				classInfoArrayBuilder.add(entry);
			}					
			
			// if nothing goes wrong
			return Response.ok().entity(classInfoArrayBuilder.build().toString()).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		finally {
			db.close();
		}		
	}
}
