
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
				String count = rs.getString(1);
				String rate = rs.getString(2);
				String average = rs.getNString(3);
				String standard_deviation = rs.getString(4);
				String percentage1 = rs.getString(5);
				String percentage2 = rs.getString(6);
				String percentage3 = rs.getString(7);
				String percentage4 = rs.getString(8);
				String percentage5 = rs.getString(9);
				
				if (rs.wasNull()) {
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				builder.add("Count", count)
					.add("Rate", rate )
					.add("Average", average)
					.add("Standard_Deviation", standard_deviation)
					.add("Percentage_of_1", percentage1)
					.add("Percentage_of_2", percentage2)
					.add("Percentage_of_3", percentage3)
					.add("Percentage_of_4", percentage4)
					.add("Percentage_of_5", percentage5);				
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
					// if return null values
					String never = rs.getString(1);
					String rarely = rs.getString(2);
					String sometimes = rs.getString(3);
					String often = rs.getString(4);
					String always = rs.getString(5);
					
					if (rs.wasNull()) {
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add("Never", never)
					.add("Rarely", rarely)
					.add("Sometime", sometimes)
					.add("Often", often)
					.add("Always", always);
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
			
			if(result.next()) {
				String value=result.getString(1);
				if(result.wasNull()) {
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				builder.add(Json.createObjectBuilder().add(selector, value).build());
			} else {
				return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
			}
			while (result.next()) {
				builder.add(Json.createObjectBuilder().add(selector, result.getString(1)).build());
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
		