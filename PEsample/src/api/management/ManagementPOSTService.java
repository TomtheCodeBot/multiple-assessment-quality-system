package api.management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import api.configuration.Configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.String;

@Path("/management")
public class ManagementPOSTService extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Path("/post")
	@GET
	public String getMessage(){
		return "Hello Package Api.Management POST Service!";
	}
	
	@Path("/insert/{colName}")
	@POST
	public void doPost(
			@PathParam("colName") String colName,
			HttpServletRequest req,
            HttpServletResponse res) throws IOException, SQLException, NamingException{
		Connection db = (Connection) Configuration.getAcademiaConnection(); 
		switch(colName) {
		  case "class":
			  String CName = req.getParameter("CName");
			  String CCode = req.getParameter("Ccode");
			  String CSize = req.getParameter("CSize");
			  String MCode = req.getParameter("MCode");
			  String SCode = req.getParameter("SCode");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertClass(?,?,?,?,?) }");
				st.setString(1, CName);
				st.setString(2, CCode);
				st.setString(3, CSize);
				st.setString(4, MCode);
				st.setString(5, SCode);
			  st.executeQuery();	
			  } finally{
				db.close();
			  }
		    break;
		  case "semester":
			  String SCode1 = req.getParameter("SCode");
			  String SName = req.getParameter("SName");
			  String AYCode = req.getParameter("AYCode");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertClass(?,?,?) }");
				st.setString(1, SCode1);
				st.setString(2, SName);
				st.setString(3, AYCode);
			  } finally{
				db.close();
			  }
		    break;
		  case "lecturer":
			  String LCode = req.getParameter("LCode");
			  String LName = req.getParameter("LName");
			  String CCode1 = req.getParameter("CCode");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertLecturer(?,?,?) }");
				st.setString(1, LCode);
				st.setString(2, LName);
				st.setString(3, CCode1);
			  } finally{
				db.close();
			  }
		    break;
		  case "AcadYear":
			  String AYCode1 = req.getParameter("AYCode");
			  String AYName = req.getParameter("AYName");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertAcdemicYear(?,?) }");
				st.setString(1, AYCode1);
				st.setString(2, AYName);
			  } finally{
				db.close();
			  }
			  break;
		  case "Faculty":
			  String FCode = req.getParameter("FCode");
			  String FName = req.getParameter("FName ");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertFaculty(?,?) }");
				st.setString(1, FCode);
				st.setString(2, FName);
			  } finally{
				db.close();
			  }
			  break;		 
		  case "Module":
			  String MCode1 = req.getParameter("MCode");
			  String MName = req.getParameter("MName");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertModule(?,?) }");
				st.setString(1, MCode1);
				st.setString(2, MName);
			  } finally{
				db.close();
			  }
			  break;		
		  case "Program":
			  String PCode = req.getParameter("PCode");
			  String PName = req.getParameter("PName");
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertProgram(?,?) }");
				st.setString(1, PCode);
				st.setString(2, PName);
			  } finally{
				db.close();
			  }
			  break;	
		  default:
			  break;
		}				
	}
}
