package com.api.questionaire;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/example1")
public class example {
	@GET
	public String getMessage() throws SQLException, NamingException   {
		return "Hello World!";
	}
}