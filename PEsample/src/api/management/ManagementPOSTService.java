package api.management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

import java.lang.String;

@Path("/management")
public class ManagementPOSTService{
	
	@Path("/post")
	@GET
	public String getMessage(){
		return "Hello Package Api.Management POST Service!";
	}
	
	@Path("/insert/{colName}")
	@POST
	public Response insertDataManagements(
			@DefaultValue("") @PathParam("colName") String colName,
			@DefaultValue("") @FormParam("CName") String CName,
			@DefaultValue("") @FormParam("CCode") String CCode,
			@DefaultValue("") @FormParam("CSize") String CSize,
			@DefaultValue("") @FormParam("MCode") String MCode,
			@DefaultValue("") @FormParam("SCode") String SCode,
			@DefaultValue("") @FormParam("SName") String SName,
			@DefaultValue("") @FormParam("AYCode") String AYCode,
			@DefaultValue("") @FormParam("LCode") String LCode,
			@DefaultValue("") @FormParam("LName") String LName,
			@DefaultValue("") @FormParam("AYName") String AYName,
			@DefaultValue("") @FormParam("FCode") String FCode,
			@DefaultValue("") @FormParam("FName") String FName,
			@DefaultValue("") @FormParam("MName") String MName,
			@DefaultValue("") @FormParam("PCode") String PCode,
			@DefaultValue("") @FormParam("PName") String PName) throws IOException, SQLException, NamingException{
		
		Connection db = (Connection) Configuration.getAcademiaConnection();
		PreparedStatement st = null;				
		ResultSet rs = null;
		
		try {
			// call relevant case regarding the resources stated in the query param
			switch(colName) {
			  case "class":			  				 
					st = db.prepareStatement("{ call InsertClass(?,?,?,?,?) }");
					st.setString(1, CName);
					st.setString(2, CCode);
					st.setInt(3, Integer.parseInt(CSize));
					st.setString(4, MCode);
					st.setString(5, SCode);						 
					break;
			  case "semester":	
					st = db.prepareStatement("{ call InsertSemester(?,?,?) }");
					st.setString(1, SCode);
					st.setString(2, SName);
					st.setString(3, AYCode);			  
					break;
			  case "lecturer":				  
				    st = db.prepareStatement("{ call InsertLecturer(?,?,?) }");
					st.setString(1, LCode);
					st.setString(2, LName);
					st.setString(3, CCode);			
					break;
			  case "AcadYear":				  
				    st = db.prepareStatement("{ call InsertAcdemicYear(?,?) }");
					st.setString(1, AYCode);
					st.setString(2, AYName);			  
					break;
			  case "Faculty":				 
				    st = db.prepareStatement("{ call InsertFaculty(?,?) }");
					st.setString(1, FCode);
					st.setString(2, FName);			  
					break;		 
			  case "Module":				  
				    st = db.prepareStatement("{ call InsertModule(?,?) }");
					st.setString(1, MCode);
					st.setString(2, MName);
					break;		
			  case "Program":				  
				  st = db.prepareStatement("{ call InsertProgram(?,?) }");
					st.setString(1, PCode);
					st.setString(2, PName);	
					break;	
			  // if the 'colName' receives null or other values
			  default:
				  return Response.status(Response.Status.FORBIDDEN).entity("Invalid resources").build();
			}
			
			// handling SQLException
			try {
				rs = st.executeQuery();
			} catch (SQLException e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
			
		} finally {
			db.close();
		}
		
		// check the ResultSet value 
		// make sure there is a value returned.
		if (rs.next()) {
			if (rs.getInt(1) == 1) {
				return Response.status(Response.Status.OK).entity("insert successfully").build();
			}
		}
		return Response.status(Response.Status.NOT_MODIFIED).entity("duplicate values").build();
	}
}
