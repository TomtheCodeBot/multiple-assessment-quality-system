
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
		if (filter.equals("attends")) {
			Connection db = (Connection) Configuration.getAcademiaConnection();
			try {			
				PreparedStatement st = db.prepareStatement(
						"call GetAttends(\"" + ayname + "\", "
								+ "\"" + sname + "\", "
								+ "\"" + fname + "\", "
								+ "\"" + pname + "\", "
								+ "\"" + mname + "\", "
								+ "\"" + lname + "\", "
								+ "\"" + cname + "\" )");
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
				PreparedStatement st = db.prepareStatement(
						"call GetGenders(\"" + ayname + "\", "
								+ "\"" + sname + "\", "
								+ "\"" + fname + "\", "
								+ "\"" + pname + "\", "
								+ "\"" + mname + "\", "
								+ "\"" + lname + "\", "
								+ "\"" + cname + "\" )");
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
		Connection db = (Connection) Configuration.getAcademiaConnection();
		try {			
			switch(selector) 
			{
				case "class": 
				{
					PreparedStatement st = db.prepareStatement(
							"call GetClassGraph(\"" + ayname + "\", "
									+ "\"" + sname + "\", "
									+ "\"" + fname + "\", "
									+ "\"" + pname + "\", "
									+ "\"" + mname + "\", "
									+ "\"" + lname + "\" )");
					ResultSet result = st.executeQuery();
					JsonArrayBuilder builder = Json.createArrayBuilder();
					while (result.next()) {
						builder.add(Json.createObjectBuilder().add("Class", result.getString(1)).build());
					}
					return Response.ok().entity(builder.build().toString()).build();
				}
				case "academic_year": 
				{
					JsonArrayBuilder academicYearArrayBuilder = Json.createArrayBuilder();
					PreparedStatement st = db.prepareStatement(
							"call GetAcademicYearGraph(\"" + sname + "\", "
									+ "\"" + fname + "\", "
									+ "\"" + pname + "\", "
									+ "\"" + mname + "\", "
									+ "\"" + lname + "\", "
									+ "\"" + cname + "\" )");
					ResultSet result = st.executeQuery();
					while (result.next()) {
						academicYearArrayBuilder.add(Json.createObjectBuilder().add("Academic_Year", result.getString(1)).build());
					}
					return Response.ok().entity(academicYearArrayBuilder.build().toString()).build();
				}
				case "module":
				{
					JsonArrayBuilder moduleArrayBuilder = Json.createArrayBuilder();
					PreparedStatement st = db.prepareStatement(
							"call GetModuleGraph(\"" + ayname + "\", "
									+ "\"" + sname + "\", "
									+ "\"" + pname + "\", "
									+ "\"" + fname + "\", "
									+ "\"" + lname + "\", "
									+ "\"" + cname + "\" )");
					ResultSet result = st.executeQuery();
					while (result.next()) {
						moduleArrayBuilder.add(Json.createObjectBuilder().add("Module", result.getString(1)).build());
					}
					return Response.ok().entity(moduleArrayBuilder.build().toString()).build();
				}
				case "lecturer":
				{
					JsonArrayBuilder lecturerArrayBuilder = Json.createArrayBuilder();
					PreparedStatement st = db.prepareStatement(
							"call GetLecturerGraph(\"" + ayname + "\", "
									+ "\"" + sname + "\", "
									+ "\"" + fname + "\", "
									+ "\"" + pname + "\", "
									+ "\"" + mname + "\", "
									+ "\"" + cname + "\" )");
					ResultSet result = st.executeQuery();
					while (result.next()) {
						lecturerArrayBuilder.add(Json.createObjectBuilder().add("Lecturer", result.getString(1)).build());
					}
					return Response.ok().entity(lecturerArrayBuilder.build().toString()).build();
				}
				
				case "faculty":
				{
					PreparedStatement st = db.prepareStatement(
							"call GetFacultyGraph(\""+ ayname + "\", "
									+ "\"" + sname + "\","
									+ "\"" + pname + "\","
									+ "\"" + mname + "\","
									+ "\"" + lname + "\","
									+ "\"" + cname + "\""
							+ ")"
					);
					System.out.println(st);
					ResultSet rs = st.executeQuery();
					JsonArrayBuilder faculty = Json.createArrayBuilder();
					while(rs.next()) {
						faculty.add( Json.createObjectBuilder()									
								.add("Faculty", rs.getString(1)).build());
					}
					JsonArray entry = faculty.build();
					return Response.ok().entity(entry.toString()).build();
				}
				
				case "semester":
				{
					PreparedStatement st = db.prepareStatement(
							"call GetSemesterGraph(\""+ ayname + "\", "
									+ "\"" + fname + "\","
									+ "\"" + pname + "\","
									+ "\"" + mname + "\","
									+ "\"" + lname + "\","
									+ "\"" + cname + "\""
							+ ")"
					);
					System.out.println(st);
					ResultSet rs = st.executeQuery();
					JsonArrayBuilder faculty = Json.createArrayBuilder();
					while(rs.next()) {
						faculty.add( Json.createObjectBuilder()									
								.add("Semester", rs.getString(1)).build());
					}
					JsonArray entry = faculty.build();
					return Response.ok().entity(entry.toString()).build();
				}
				
				case "program":
				{
					PreparedStatement st = db.prepareStatement(
							"call GetProgramGraph(\""+ ayname + "\", "
									+ "\"" + sname + "\","
									+ "\"" + fname + "\","
									+ "\"" + mname + "\","
									+ "\"" + lname + "\","
									+ "\"" + cname + "\""
							+ ")"
					);
					System.out.println(st);
					ResultSet rs = st.executeQuery();
					JsonArrayBuilder faculty = Json.createArrayBuilder();
					while(rs.next()) {
						faculty.add( Json.createObjectBuilder()									
								.add("Program", rs.getString(1)).build());
					}
					JsonArray entry = faculty.build();
					return Response.ok().entity(entry.toString()).build();
				}
					
			}
		}
		finally {
			db.close();
		}
		return null;
	}
}
		

