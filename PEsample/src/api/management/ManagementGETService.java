package api.management;

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
		if (filter.equals("infodatabase")) {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("call GetInfoDatabase(?);");
				st.setString(1, choice);
			ResultSet myRs = st.executeQuery();	
			JsonArrayBuilder builder = Json.createArrayBuilder();
			while (myRs.next()) {
				builder.add(Json.createObjectBuilder().add("Code", myRs.getString(1)).add("Name", myRs.getString(2)).build());
			}
			return Response.ok().entity(builder.build().toString()).build();
		
		}
		finally {
			db.close();
		
		}
		
		} else	if (filter.equals("AcaFac")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("call getInfoYearFac();");
				ResultSet myRs = st.executeQuery();	
				JsonArrayBuilder builder = Json.createArrayBuilder();
				while (myRs.next()) {
					builder.add(Json.createObjectBuilder().add("AFCode", myRs.getString(1)).add("AYName", myRs.getString(2)).add("FName", myRs.getString(3)).build());
				}
				return Response.ok().entity(builder.build().toString()).build();
			}
			finally {
				db.close();
			}
		}
	   else	if (filter.equals("AcaFacPro")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("call getInfoYearFacPro();");
				ResultSet myRs = st.executeQuery();	
				JsonArrayBuilder builder = Json.createArrayBuilder();
				while (myRs.next()) {
					builder.add(Json.createObjectBuilder().add("PFCode", myRs.getString(1)).add("AYName", myRs.getString(2)).add("FName", myRs.getString(3)).add("PName", myRs.getString(4)).build());
				}
				return Response.ok().entity(builder.build().toString()).build();
			}
			finally {
				db.close();
			}
		}
	   else	if (filter.equals("AcaFacProMod")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("call getInfoYearFacProMod();");
				ResultSet myRs = st.executeQuery();	
				JsonArrayBuilder builder = Json.createArrayBuilder();
				while (myRs.next()) {
					builder.add(Json.createObjectBuilder().add("PFCode", myRs.getString(1))
							.add("MCode", myRs.getString(2))
							.add("AYName", myRs.getString(3))
							.add("FName", myRs.getString(4))
							.add("PName", myRs.getString(5))
							.add("MName", myRs.getString(6)).build());
				}
				return Response.ok().entity(builder.build().toString()).build();
			}
			finally {
				db.close();
			}
		}
		return null;
	}
}