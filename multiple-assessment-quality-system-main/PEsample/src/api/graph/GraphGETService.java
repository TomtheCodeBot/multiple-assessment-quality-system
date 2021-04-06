package api.graph;

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
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.Classes;

@Path("/graph")
public class GraphGETService {
	@Path("/get")
	@GET
	public String getMessage() throws SQLException, NamingException {
		return "Hello Package Api.Graph GET Service!";
	}
	
	@Path("/attends")
	@GET
	public Response getAttend(Classes classes) throws SQLException, NamingException {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {			
			PreparedStatement st = db.prepareStatement(
					"call GetAttends(\"" + classes.getAYName() + "\", "
							+ "\"" + classes.getSName() + "\", "
							+ "\"" + classes.getFName() + "\", "
							+ "\"" + classes.getPName() + "\", "
							+ "\"" + classes.getMName() + "\", "
							+ "\"" + classes.getLName() + "\", "
							+ "\"" + classes.getCName() + "\" )");
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
	}
	@Path("/gender")
	@GET
	public Response getGender(Classes classes) throws SQLException, NamingException {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {			
			PreparedStatement st = db.prepareStatement(
					"call GetGenders(\"" + classes.getAYName() + "\", "
							+ "\"" + classes.getSName() + "\", "
							+ "\"" + classes.getFName() + "\", "
							+ "\"" + classes.getPName() + "\", "
							+ "\"" + classes.getMName() + "\", "
							+ "\"" + classes.getLName() + "\", "
							+ "\"" + classes.getCName() + "\" )");
			ResultSet rs = st.executeQuery();
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
	@Path("/class")
	@GET
	public Response getClassGraph(Classes classes) throws SQLException, NamingException {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {			
			PreparedStatement st = db.prepareStatement(
					"call GetClassGraph(\"" + classes.getAYName() + "\", "
							+ "\"" + classes.getSName() + "\", "
							+ "\"" + classes.getFName() + "\", "
							+ "\"" + classes.getPName() + "\", "
							+ "\"" + classes.getMName() + "\", "
							+ "\"" + classes.getLName() + "\" )");
			ResultSet result = st.executeQuery();
			JsonArrayBuilder builder = Json.createArrayBuilder();
			while (result.next()) {
				builder.add(Json.createObjectBuilder().add("Class", result.getString(1)).build());
			}
			return Response.ok().entity(builder.build().toString()).build();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return Response.ok().entity(e.toString()).build();
		}
		finally {
			db.close();
		}
	}
}