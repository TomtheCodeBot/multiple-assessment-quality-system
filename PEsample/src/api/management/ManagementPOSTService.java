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
import javax.ws.rs.QueryParam;
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
			@DefaultValue("") @QueryParam("CName") String CName,
			@DefaultValue("") @QueryParam("CCode") String CCode,
			@DefaultValue("") @QueryParam("CSize") String CSize,
			@DefaultValue("") @QueryParam("MCode") String MCode,
			@DefaultValue("") @QueryParam("SCode") String SCode,
			@DefaultValue("") @QueryParam("SName") String SName,
			@DefaultValue("") @QueryParam("AYCode") String AYCode,
			@DefaultValue("") @QueryParam("LCode") String LCode,
			@DefaultValue("") @QueryParam("LName") String LName,
			@DefaultValue("") @QueryParam("AYName") String AYName,
			@DefaultValue("") @QueryParam("FCode") String FCode,
			@DefaultValue("") @QueryParam("FName") String FName,
			@DefaultValue("") @QueryParam("MName") String MName,
			@DefaultValue("") @QueryParam("PCode") String PCode,
			@DefaultValue("") @QueryParam("AFCode") String AFCode,
			@DefaultValue("") @QueryParam("PFCode") String PFCode,
			@DefaultValue("") @QueryParam("PName") String PName) throws IOException, SQLException, NamingException{
		
		Connection db = (Connection) Configuration.getAcademiaConnection();
		PreparedStatement st = null;				
		ResultSet rs = null;
		
		try {
			// call relevant case regarding the resources stated in the query param
			switch(colName) {
			  case "class":			  				 
					st = db.prepareStatement("{ call InsertClass(?,?,?,?) }");
					st.setString(1, CName);
					st.setInt(2, Integer.parseInt(CSize));
					st.setString(3, MCode);
					st.setString(4, SCode);						 
					break;
			  case "semester":	
					st = db.prepareStatement("{ call InsertSemester(?,?) }");
					st.setString(1, SName);
					st.setString(2, AYCode);			  
					break;
			  case "lecturer":				  
				    st = db.prepareStatement("{ call InsertLecturer(?,?) }");
					st.setString(1, LName);
					st.setString(2, CCode);			
					break;
			  case "AcadYear":				  
				    st = db.prepareStatement("{ call InsertAcdemicYear(?) }");
					st.setString(1, AYName);	  
					break;
			  case "Faculty":				 
				    st = db.prepareStatement("{ call InsertFaculty(?) }");
					st.setString(1, FName);			  
					break;		 
			  case "Module":				  
				    st = db.prepareStatement("{ call InsertModule(?) }");
					st.setString(1, MName);
					break;		
			  case "Program":				  
				  st = db.prepareStatement("{ call InsertProgram(?) }");
					st.setString(1, PName);
			  case "AcaFac":				  
				  st = db.prepareStatement("{ call InsertAcaFac(?,?) }");
					st.setString(1, AYCode);
					st.setString(2, FCode);
			  case "AcaFacPro":				  
				  st = db.prepareStatement("{ call InsertAcaFacPro(?,?) }");
					st.setString(1, AFCode);
					st.setString(2, PCode);
			  case "AcaFacProMode":				  
				  st = db.prepareStatement("{ call InsertAcaFacProMod(?,?) }");
					st.setString(1, PFCode);
					st.setString(2, MCode);
					break;	
			  // if the 'colName' receives null or other values
			  default:
				  return Response.status(Response.Status.FORBIDDEN).entity("Invalid resources").build();
			}
			
			// handling SQLException
			try {
				// check the ResultSet value 
				// make sure there is a value returned.
				
				rs = st.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) == 1) {
						return Response.status(Response.Status.OK).entity("insert successfully").build();
					}
				}
			} catch (SQLException e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
			
		} finally {
			db.close();
		}
		
		
		return Response.status(Response.Status.NOT_MODIFIED).entity("duplicate values").build();
	}
}
