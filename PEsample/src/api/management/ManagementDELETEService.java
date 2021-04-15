package api.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			@DefaultValue("") @QueryParam("aycode") String aycode,
			@DefaultValue("") @QueryParam("fcode") String fcode,
			@DefaultValue("") @QueryParam("pcode") String pcode, 
			@DefaultValue("") @QueryParam("mcode") String mcode, 
			@DefaultValue("") @QueryParam("id") String id,
			@DefaultValue("") @QueryParam("name") String name) throws SQLException, NamingException{
		
		if (filter.isEmpty() || !filter.equals("single") || !filter.equals("combine")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid request").build();
		}
		
		Connection db = Configuration.getAcademiaConnection();
		try {
			int noOfAffectedRows = 0;
			if (filter.equals("single")){
				PreparedStatement st = db.prepareStatement("{ call deleteInfoDatabase(?,?,?) }");
				st.setString(1, col1);
				st.setString(2, id);
				st.setString(3, name);
				
				noOfAffectedRows = st.executeUpdate();
			} else {
				if (col1.equals("year") && col2.equals("faculty") && col3.equals("program") && col4.equals("module")) {
					PreparedStatement st = db.prepareStatement("{ call deleteInfoYearFacProMod(?,?,?,?) }");
					st.setString(1, aycode);
					st.setString(2, fcode);
					st.setString(3, pcode);
					st.setString(4, mcode);
					
					noOfAffectedRows = st.executeUpdate();
				} else if (col3.isEmpty() && col4.isEmpty()) {
					PreparedStatement st = db.prepareStatement("{ call deleteInfoYearFac(?,?) }");
					st.setString(1, aycode);
					st.setString(2, fcode);		
					
					noOfAffectedRows = st.executeUpdate();
				} else if (col4.isEmpty()) {
					PreparedStatement st = db.prepareStatement("{ call deleteInfoYearFacPro(?,?,?) }");
					st.setString(1, aycode);
					st.setString(2, fcode);
					st.setString(3, pcode);
					
					noOfAffectedRows = st.executeUpdate();
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Invalid table request").build();
				}
			}
			if (noOfAffectedRows == 0) {
				return Response.status(Response.Status.NOT_MODIFIED).entity("There is no affected row in database").build();
			}	
			return Response.status(Response.Status.OK).entity("deleted successfully").build();
		} finally {
			db.close();
		}
	}

}
