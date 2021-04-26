package api.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.ClassSize;

@Path("/management")
public class ManagementPUTService {
	
	@Path("/class/size")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClassSize(ClassSize classSize) throws SQLException, NamingException {
		Connection db = Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call ModifyClassSize(?, ?) }");
			st.setString(1, classSize.getClassCode());
			st.setInt(2, classSize.getSize());
			
			int noOfAffectedRows = st.executeUpdate();
			if (noOfAffectedRows == 0) {
				// if null values are returned
				return Response.status(Response.Status.NO_CONTENT).entity("There is nothing to return").build();
			} 
			return Response.status(Response.Status.OK).entity("update successfully").build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		finally {
			db.close();
		} 		
	}
}
