package api.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;

@Path("/management")
public class ManagementDELETEService {
	
	@Path("/testing") 
	@GET
	public String getMessage() {
		return "hello from api.management";
	}
	
	@Path("/resources")
	@DELETE
	public Response deleteTable(
			@DefaultValue("") @QueryParam("filter") String filter, 
			@DefaultValue("") @QueryParam("col1") String col1, 
			@DefaultValue("") @QueryParam("col2") String col2, 
			@DefaultValue("") @QueryParam("col3") String col3, 
			@DefaultValue("") @QueryParam("col4") String col4,
			@DefaultValue("") @QueryParam("afcode") String afcode,
			@DefaultValue("") @QueryParam("pfcode") String pfcode,
			@DefaultValue("") @QueryParam("mcode") String mcode, 
			@DefaultValue("") @QueryParam("id") String id,
			@DefaultValue("") @QueryParam("name") String name) throws NamingException, SQLException{
		
		// to make sure filter query param is used correctly
		if (filter.isEmpty() || !filter.equals("single") && !filter.equals("combine")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid request").build();
		}
		
		Connection db = Configuration.getAcademiaConnection();
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			if (filter.equals("single")){
				st = db.prepareStatement("{ call DeleteInfoDatabase(?,?,?) }");
				st.setString(1, col1);
				st.setString(2, id);
				st.setString(3, name);
			} else {
				if (col1.equals("year") && col2.equals("faculty") && col3.equals("program") && col4.equals("module")) {
					st = db.prepareStatement("{ call DeleteInfoYearFacProMod(?,?) }");
					st.setString(1, pfcode);
					st.setString(2, mcode);
					
				} else if (col3.isEmpty() && col4.isEmpty()) {
					st = db.prepareStatement("{ call DeleteInfoYearFac(?) }");
					st.setString(1, afcode);			
				} else if (col4.isEmpty()) {
					st = db.prepareStatement("{ call DeleteInfoYearFacPro(?) }");
					st.setString(1, pfcode);
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Invalid table request").build();
				}
			}
			
			// to make sure catch all exception from database 
			try {
				rs = st.executeQuery();
			} catch (SQLException e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
			
			if (rs.next()){
				if (rs.getInt(1) == 1) {				
					return Response.status(Response.Status.OK).entity("deleted successfully").build();
				}	
			}
			return Response.status(Response.Status.OK).entity("There are no affected rows in database").build();
			
		}
		finally {
			db.close();
		}
	}

}
