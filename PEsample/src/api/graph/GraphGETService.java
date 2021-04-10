
package api.graph;

import java.sql.CallableStatement;
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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.Classes;

@Path("/graph")
public class GraphGETService {
	@Path("/get")
	@GET
	public String getMessage() throws SQLException, NamingException {
		return "Hello from api.graph";
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
		
		if (filter.equals("attends")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {			
				PreparedStatement st = db.prepareStatement("{ call GetAttends(?,?,?,?,?,?,?) }");
				st.setString(1, ayname);
				st.setString(2, sname);
				st.setString(3, fname);
				st.setString(4, pname);
				st.setString(5, mname);
				st.setString(6, lname);
				st.setString(7, cname);
				
				ResultSet rs = st.executeQuery();
				JsonObjectBuilder builder = Json.createObjectBuilder();
				if(rs.next()) {
					builder.add("Never", rs.getString(1))
					.add("Rarely", rs.getString(2))
					.add("Sometime", rs.getString(3))
					.add("Often", rs.getString(4))
					.add("Always", rs.getString(5));
				}
				JsonObject entry = builder.build();
				return Response.ok().entity(entry.toString()).build();
			}
			finally {
				db.close();
			}
		} else if (filter.equals("gender")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {			
				PreparedStatement st = db.prepareStatement("{ call GetGenders(?,?,?,?,?,?,?) }");
				st.setString(1, ayname);
				st.setString(2, sname);
				st.setString(3, fname);
				st.setString(4, pname);
				st.setString(5, mname);
				st.setString(6, lname);
				st.setString(7, cname);
				
				ResultSet rs = st.executeQuery();
				System.out.println(st);
				JsonObjectBuilder builder = Json.createObjectBuilder();
				if(rs.next()) {
					builder.add("Female", rs.getString(1))
					.add("Male", rs.getString(2))
					.add("Other", rs.getString(3));
					
				}
				JsonObject entry = builder.build();
				return Response.ok().entity(entry.toString()).build();
			}
			finally {
				db.close();
			}
		}
		return null;
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
			System.out.print(st);
			ResultSet result = st.executeQuery();
			JsonArrayBuilder builder = Json.createArrayBuilder();
			while (result.next()) {
				builder.add(Json.createObjectBuilder().add(selector, result.getString(1)).build());
			}
			return Response.ok().entity(builder.build().toString()).build();
		}
		finally {
			db.close();
		}
	}
}
		

