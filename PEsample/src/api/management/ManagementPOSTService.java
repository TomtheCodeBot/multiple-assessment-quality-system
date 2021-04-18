package api.management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.naming.NamingException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	public void insertDataManagements(
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
		switch(colName) {
		  case "class":
			  System.out.println(CName + "\n" + CCode + "\n" + CSize + "\n" + MCode + "\n" + SCode);
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertClass(?,?,?,?,?) }");
				st.setString(1, CName);
				st.setString(2, CCode);
				st.setInt(3, Integer.parseInt(CSize));
				st.setString(4, MCode);
				st.setString(5, SCode);
			  st.executeQuery();	
			  } finally{
				db.close();
			  }
		    break;
		  case "semester":
			  System.out.println(SCode + "\n" + SName + "\n" + AYCode );
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertSemester(?,?,?) }");
				st.setString(1, SCode);
				st.setString(2, SName);
				st.setString(3, AYCode);
				st.executeQuery();
			  } finally{
				db.close();
			  }
		    break;
		  case "lecturer":
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertLecturer(?,?,?) }");
				st.setString(1, LCode);
				st.setString(2, LName);
				st.setString(3, CCode);
			st.executeQuery();
			  } finally{
				db.close();
			  }
		    break;
		  case "AcadYear":
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertAcdemicYear(?,?) }");
				st.setString(1, AYCode);
				st.setString(2, AYName);
				st.executeQuery();
			  } finally{
				db.close();
			  }
			  break;
		  case "Faculty":
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertFaculty(?,?) }");
				st.setString(1, FCode);
				st.setString(2, FName);
				st.executeQuery();
			  } finally{
				db.close();
			  }
			  break;		 
		  case "Module":
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertModule(?,?) }");
				st.setString(1, MCode);
				st.setString(2, MName);
				st.executeQuery();
			  } finally{
				db.close();
			  }
			  break;		
		  case "Program":
			  try {
			  PreparedStatement st = db.prepareStatement("{ call InsertProgram(?,?) }");
				st.setString(1, PCode);
				st.setString(2, PName);
				st.executeQuery();
			  } finally{
				db.close();
			  }
			  break;	
		  default:
			  break;
		}				
	}
}
