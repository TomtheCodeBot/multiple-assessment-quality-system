package api.questionaire;

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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.configuration.Configuration;
import api.model.*;
@Path("/questionaire")
public class QuestionairePOSTService {
	@Path("/post")
	@GET
	public String getMessage() throws SQLException, NamingException   {
		return "Hello Package Api.Questionaire POST Service!";
	}
	
	@Path("/answers")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitQuestionaire(Answers Answers)throws SQLException, NamingException{
		Connection db= Configuration.getAcademiaConnection();
		try {
			PreparedStatement st = db.prepareStatement("{ call StoreQuestionnaireDetail(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			st.setString(1, Answers.getCname());
			st.setString(2, Answers.getLname());
			st.setString(3, Answers.getAttend());
			st.setString(4, Answers.getGender());
			st.setString(5, Answers.getQ1());
			st.setString(6, Answers.getQ2());
			st.setString(7, Answers.getQ3());
			st.setString(8, Answers.getQ4());
			st.setString(9, Answers.getQ5());
			st.setString(10, Answers.getQ6());
			st.setString(11, Answers.getQ7());
			st.setString(12, Answers.getQ8());
			st.setString(13, Answers.getQ9());
			st.setString(14, Answers.getQ10());
			st.setString(15, Answers.getQ11());
			st.setString(16, Answers.getQ12());
			st.setString(17, Answers.getQ13());
			st.setString(18, Answers.getQ14());
			st.setString(19, Answers.getQ15());
			st.setString(20, Answers.getQ16());
			st.setString(21, Answers.getQ17());
			st.setString(22, Answers.getQ18());
			
			int noOfAffectedRows = st.executeUpdate();
			if (noOfAffectedRows == 0) {
				// catch if there are no affected rows
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("There is something wrong").build(); 
			}
			
			return Response.ok().entity("New academic year successfully inserted").build();
		} catch(SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		} finally {
			db.close();			
		}
	}
}