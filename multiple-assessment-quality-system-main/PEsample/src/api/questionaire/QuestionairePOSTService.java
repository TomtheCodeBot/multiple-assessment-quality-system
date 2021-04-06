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
			PreparedStatement st = db.prepareStatement("call StoreQuestionnaireDetail(\""+Answers.getCname()+"\","
					+ "\""+Answers.getLname()+"\","
					+ "\""+Answers.getAttend()+"\","
					+ "\""+Answers.getGender()+"\","
					+ "\""+Answers.getQ1()+"\","
					+ "\""+Answers.getQ2()+"\","
					+ "\""+Answers.getQ3()+"\","
					+ "\""+Answers.getQ4()+"\","
					+ "\""+Answers.getQ5()+"\","
					+ "\""+Answers.getQ6()+"\","
					+ "\""+Answers.getQ7()+"\","
					+ "\""+Answers.getQ8()+"\","
					+ "\""+Answers.getQ9()+"\","
					+ "\""+Answers.getQ10()+"\","
					+ "\""+Answers.getQ11()+"\","
				    + "\""+Answers.getQ12()+"\","
					+ "\""+Answers.getQ13()+"\","
					+ "\""+Answers.getQ14()+"\","
					+ "\""+Answers.getQ15()+"\","
					+ "\""+Answers.getQ16()+"\","
					+ "\""+Answers.getQ17()+"\","
					+ "\""+Answers.getQ18()+"\")");
			System.out.print(st);
			st.executeQuery();
			return Response.ok().entity("New academic year successfully inserted").build();
		}catch(SQLException e) {
			e.printStackTrace();
			return Response.ok().entity(e.toString()).build();
			}
		finally {
			db.close();			
		}
	}
}