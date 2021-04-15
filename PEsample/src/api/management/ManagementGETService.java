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
	@Path("infodatabase")
	@GET
	public Response GetInfoDatabase(
			@DefaultValue("") @QueryParam("choice") String choice 		
			)throws SQLException, NamingException {
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call GetInfoDatabase(?) }");
				st.setString(1, choice);
			ResultSet myRs = st.executeQuery();	
			JsonObjectBuilder builder = Json.createObjectBuilder();
			JsonObject entry = builder.build();
			while (myRs.next()) {
				if(choice=="academic_year") {
				builder.add("AYCode", myRs.getString(1))
				.add("AYName", myRs.getString(2));		
			}
				
				if(choice=="faculty") {
					builder.add("FCode", myRs.getString(1))
					.add("FName", myRs.getString(2));	
				}
				if(choice=="program") {
					builder.add("PCode", myRs.getString(1))
					.add("PName", myRs.getString(2));	
					
				}
				if(choice=="module") {
					builder.add("MCode", myRs.getString(1))
					.add("MName", myRs.getString(2));		
				
				}
				if(choice=="lecturer") {
					builder.add("LCode", myRs.getString(1))
					.add("LName", myRs.getString(2));	
				}
				if(choice=="class") {
					builder.add("CCode", myRs.getString(1))
					.add("CName", myRs.getString(2));	
				}
				if(choice=="semester") {
					builder.add("SCode", myRs.getString(1))
					.add("SName", myRs.getString(2));	
				}
		}
			return Response.ok().entity(entry.toString()).build();
		}
		finally {
			db.close();
		
		}
	}

	@Path("AcaFac")
	@GET
	public Response GetInfoYearFac() throws SQLException, NamingException {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("{ call getInfoYearFac() }");
				ResultSet myRs = st.executeQuery();	
				JsonObjectBuilder builder = Json.createObjectBuilder();
				JsonObject entry = builder.build();
				if (myRs.next()) {
					builder.add("AFCode", myRs.getString(1));
				}
				return Response.ok().entity(entry.toString()).build();
			}
			finally {
				db.close();
			}
		}
	@Path("AcaFacPro")
	@GET
	public Response GetInfoYearFacPro() throws SQLException, NamingException {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("{ call getInfoYearFacPro() }");
				ResultSet myRs = st.executeQuery();	
				JsonObjectBuilder builder = Json.createObjectBuilder();
				JsonObject entry = builder.build();
				if (myRs.next()) {
					builder.add("PFCode", myRs.getString(1));
				}
				return Response.ok().entity(entry.toString()).build();
			}
			finally {
				db.close();
			}
		}
	@Path("AcaProFacMod")
	@GET
	public Response GetInfoYearFacProMod() throws SQLException, NamingException {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {
				PreparedStatement st = db.prepareStatement("{ call getInfoYearFacProMod() }");
				ResultSet myRs = st.executeQuery();	
				JsonObjectBuilder builder = Json.createObjectBuilder();
				JsonObject entry = builder.build();
				if (myRs.next()) {
					builder.add("PFCode", myRs.getString(1))
					.add("MCode", myRs.getString(2));
				}
				return Response.ok().entity(entry.toString()).build();
			}
			finally {
				db.close();
			}
		}
	}
