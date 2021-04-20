
package api.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/graph")
public class GraphGETService {
	@Path("/get")
	@GET
	public String getMessage() throws SQLException, NamingException {
		return "Hello from api.graph";
	}
	
	@Path("question")
	@GET
	public Response getQuestionGraph(
			@DefaultValue("") @QueryParam("ayname") String ayname, 
			@DefaultValue("") @QueryParam("sname") String sname, 
			@DefaultValue("") @QueryParam("fname") String fname, 
			@DefaultValue("") @QueryParam("pname") String pname, 
			@DefaultValue("") @QueryParam("mname") String mname, 
			@DefaultValue("") @QueryParam("lname") String lname, 
			@DefaultValue("") @QueryParam("cname") String cname,
			@DefaultValue("") @QueryParam("qname") String qname
			)throws SQLException, NamingException {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call GetQuestionGraph(?,?,?,?,?,?,?,?) }");
				st.setString(1, ayname);
				st.setString(2, sname);
				st.setString(3, fname);
				st.setString(4, pname);
				st.setString(5, mname);
				st.setString(6, lname);
				st.setString(7, cname);
				st.setString(8, qname);
			ResultSet rs = st.executeQuery();	
			JsonObjectBuilder builder = Json.createObjectBuilder();
			if(rs.next()) {
				builder.add("Count", rs.getString(1))
					.add("Rate", rs.getString(2))
					.add("Average", rs.getString(3))
					.add("Standard_Deviation", rs.getString(4))
					.add("Percentage_of_1", rs.getString(5))
					.add("Percentage_of_2", rs.getString(6))
					.add("Percentage_of_3", rs.getString(7))
					.add("Percentage_of_4", rs.getString(8))
					.add("Percentage_of_5", rs.getString(9));				
			} else {
				// catch not return any value at all.
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
	@Path("general")
	@GET
	public Response getGeneralInfo(
			@DefaultValue("") @QueryParam("filter") String filter,
			@DefaultValue("") @QueryParam("ayname") String ayname, 
			@DefaultValue("") @QueryParam("sname") String sname, 
			@DefaultValue("") @QueryParam("fname") String fname, 
			@DefaultValue("") @QueryParam("pname") String pname, 
			@DefaultValue("") @QueryParam("mname") String mname, 
			@DefaultValue("") @QueryParam("lname") String lname, 
			@DefaultValue("") @QueryParam("cname") String cname
			) throws SQLException, NamingException {
		if (filter.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave filter empty").build();
		}
		
		Connection db = Configuration.getAcademiaConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		JsonObjectBuilder builder = Json.createObjectBuilder();		
		try {
			switch(filter) {
			case "attends":
				st = db.prepareStatement("{ call GetAttends(?,?,?,?,?,?,?) }");
				st.setString(1, ayname);
				st.setString(2, sname);
				st.setString(3, fname);
				st.setString(4, pname);
				st.setString(5, mname);
				st.setString(6, lname);
				st.setString(7, cname);
				
				rs = st.executeQuery();
				if(rs.next()) {
					builder.add("Never", rs.getString(1))
					.add("Rarely", rs.getString(2))
					.add("Sometime", rs.getString(3))
					.add("Often", rs.getString(4))
					.add("Always", rs.getString(5));
				} else {
					// catch not return any value at all.
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}				
				break;
			case "gender":
				st = db.prepareStatement("{ call GetGenders(?,?,?,?,?,?,?) }");
				st.setString(1, ayname);
				st.setString(2, sname);
				st.setString(3, fname);
				st.setString(4, pname);
				st.setString(5, mname);
				st.setString(6, lname);
				st.setString(7, cname);
				
				rs = st.executeQuery();
				if(rs.next()) {
					builder.add("Female", rs.getString(1))
					.add("Male", rs.getString(2))
					.add("Other", rs.getString(3));					
				} else {
					// catch not return any value at all.
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}				
				break;
			default:
				// catch invalid filter names.
				return Response.status(Response.Status.FORBIDDEN).entity("Invalid filter").build();
			}
			// if nothing goes wrong
			JsonObject entry = builder.build();
			return Response.ok().entity(entry.toString()).build();
		} catch(SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			db.close();
		}			
	}
	
	@Path("resources")
	@GET
	public Response getFilterResources(
			@DefaultValue("") @QueryParam("selector") String selector,
			@DefaultValue("") @QueryParam("ayname") String ayname, 
			@DefaultValue("") @QueryParam("sname") String sname, 
			@DefaultValue("") @QueryParam("fname") String fname, 
			@DefaultValue("") @QueryParam("pname") String pname, 
			@DefaultValue("") @QueryParam("mname") String mname, 
			@DefaultValue("") @QueryParam("lname") String lname, 
			@DefaultValue("") @QueryParam("cname") String cname
			) throws SQLException, NamingException {
		
		if (selector.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave selector empty").build();
		}
		
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {		
			
			PreparedStatement st = db.prepareStatement("{ call GetInfoGraph(?,?,?,?,?,?,?,?) }");
			st.setString(1, ayname);
			st.setString(2, sname);
			st.setString(3, fname);
			st.setString(4, pname);
			st.setString(5, mname);
			st.setString(6, lname);
			st.setString(7, cname);
			st.setString(8, selector);
			
			ResultSet result = st.executeQuery();
			JsonArrayBuilder builder = Json.createArrayBuilder();
			while (result.next()) {
				builder.add(Json.createObjectBuilder().add(selector, result.getString(1)).build());
			}			
			// if there is nothing to return 
			// in case someone finds the api and tries to mess it up.
			if (!result.next()) {
				return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
			}
			
			// if nothing goes wrong
			return Response.ok().entity(builder.build().toString()).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		finally {
			db.close();
		}
	}
}
		