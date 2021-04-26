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
				if (choice.equals("class")) {
					st = db.prepareStatement("{ call GetClassDatabase() }");	
					rs = st.executeQuery();
					
					if (!rs.next()) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String ccode = rs.getString(1);
					String cname = rs.getString(2);
					String csize = rs.getString(3);
					String mname = rs.getString(4);
					String sname = rs.getString(5);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("ccode", ccode)
							.add("cname", cname)
							.add("csize", csize)
							.add("mname", mname)
							.add("sname", sname).build());
					while (rs.next()) {
						ccode = rs.getString(1);
						cname = rs.getString(2);
						csize = rs.getString(3);
						mname = rs.getString(4);
						sname = rs.getString(5);
						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder()
								.add("ccode", ccode)
								.add("cname", cname)
								.add("csize", csize)
								.add("mname", mname)
								.add("sname", sname).build());
					}
					
				} else if (choice.equals("semester")) {
					st = db.prepareStatement("{ call GetSemesterDatabase() }");	
					rs = st.executeQuery();
					
					if (!rs.next()) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String scode = rs.getString(1);
					String sname = rs.getString(2);
					String ayname = rs.getString(3);
					
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("scode", scode)
							.add("sname", sname)
							.add("ayname", ayname).build());
					
					while (rs.next()) {
						scode = rs.getString(1);
						sname = rs.getString(2);
						ayname = rs.getString(3);
						
						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder()
								.add("scode", scode)
								.add("sname", sname)
								.add("ayname", ayname).build());
					}
				} else if (choice.equals("lecturer")) {
					st = db.prepareStatement("{ call GetLecturerDatabase() }");	
					rs = st.executeQuery();
					
					if (!rs.next()) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String lcode = rs.getString(1);
					String lname = rs.getString(2);
					String cname = rs.getString(3);
					
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("lcode", lcode)
							.add("lname", lname)
							.add("cname", cname).build());
					while (rs.next()) {
						lcode = rs.getString(1);
						lname = rs.getString(2);
						cname = rs.getString(3);
						
						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder()
								.add("lcode", lcode)
								.add("lname", lname)
								.add("cname", cname).build());
					}
				} else if (choice.equals("user")) {
					st = db.prepareStatement("{ call GetUserDatabase() }");
					System.out.print(st);
					rs = st.executeQuery();
					
					if (!rs.next()) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String username = rs.getString(1);						
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("user", username).build());
					while (rs.next()) {
						username = rs.getString(1);						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder()
								.add("user", username).build());
					}
				}
				else {
					st = db.prepareStatement("{ call GetInfoDatabase(?) }");
					st.setString(1, choice);
					
					if (choice.isEmpty()) {					
						return Response.status(Response.Status.BAD_REQUEST).entity("Can not leave choice empty").build();					
					}
					rs = st.executeQuery();
					
					if (!rs.next()) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String code = rs.getString(1);
					String name = rs.getString(2);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder().add("code", code).add("name", name).build());
					while (rs.next()) {
						code = rs.getString(1);
						name = rs.getString(2);
						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder().add("code", code).add("name", name).build());
					}						
				}				
				break;
			case "AcaFac":
				st = db.prepareStatement("{ call getInfoYearFac() }");			
				rs = st.executeQuery();
				
				if (!rs.next()) {
					// if there is no row returned 
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				String afCode = rs.getString(1);
				String ayName = rs.getString(2);
				String fName = rs.getString(3);
				
				if (rs.wasNull()) {
					// if null values are returned 
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();					
				}
				
				builder.add(Json.createObjectBuilder().add("AFCode", afCode).add("AYName", ayName).add("FName", fName).build());
				
				while (rs.next()) {
					afCode = rs.getString(1);
					ayName = rs.getString(2);
					fName = rs.getString(3);
					
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
				
				if (!rs.next()) {
					// if there is no row returned 
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				String pfCode = rs.getString(1);
				String ayname = rs.getString(2);
				String fname = rs.getString(3);
				String pName = rs.getString(4);
				
				if (rs.wasNull()) {
					// if null values are returned
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				builder.add(Json.createObjectBuilder().add("PFCode", pfCode).add("AYName", ayname).add("FName", fname).add("PName", pName).build());
				while (rs.next()) {
					pfCode = rs.getString(1);
					ayname = rs.getString(2);
					fname = rs.getString(3);
					pName = rs.getString(4);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					builder.add(Json.createObjectBuilder().add("PFCode", pfCode).add("AYName", ayname).add("FName", fname).add("PName", pName).build());
				}
				break;
			case "AcaFacProMod":
				st = db.prepareStatement("{ call getInfoYearFacProMod() }");
				rs = st.executeQuery();
				
				if (!rs.next() ) {
					// if there is no row returned 
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				String pfcode = rs.getString(1);
				String mCode = rs.getString(2);
				String AYname = rs.getString(3);
				String FName = rs.getString(4);
				String PName = rs.getString(5);
				String mName = rs.getString(6);
				
				if (rs.wasNull()) {
					// if null values are returned
					return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
				}
				
				builder.add(Json.createObjectBuilder()
						.add("PFCode", pfcode )
						.add("MCode", mCode)
						.add("AYName", AYname)
						.add("FName", FName)
						.add("PName", PName)
						.add("MName", mName).build());
				while (rs.next()) {
					pfcode = rs.getString(1);
					mCode = rs.getString(2);
					AYname = rs.getString(3);
					FName = rs.getString(4);
					PName = rs.getString(5);
					mName = rs.getString(6);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("PFCode", pfcode )
							.add("MCode", mCode)
							.add("AYName", AYname)
							.add("FName", FName)
							.add("PName", PName)
							.add("MName", mName).build());
				}
				break;
			case "combine":
				if (choice.equals("module")) {
					st = db.prepareStatement("{ call GetModuleForInsert(?) }");					
					rs = st.executeQuery();
															
					if (!rs.next() ) {
						// if there is no row returned 
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					String MCode = rs.getString(1);
					String MName = rs.getString(2);
					
					if (rs.wasNull()) {
						// if null values are returned
						return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
					}
					
					builder.add(Json.createObjectBuilder()
							.add("MCode", MCode)								
							.add("MName", MName).build());
					while (rs.next()) {
						 MCode = rs.getString(1);
						 MName = rs.getString(2);
						
						if (rs.wasNull()) {
							// if null values are returned
							return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
						}
						
						builder.add(Json.createObjectBuilder()
								.add("MCode", MCode)								
								.add("MName", MName).build());
					}
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Invalid resources").build();	
				}
				break;
			default:
				// catch invalid resource names.
				return Response.status(Response.Status.FORBIDDEN).entity("Invalid resources").build();				
			}
			
			System.out.print(builder);
			return Response.ok().entity(builder.build().toString()).build();
			
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			db.close();
		}			
	}
}