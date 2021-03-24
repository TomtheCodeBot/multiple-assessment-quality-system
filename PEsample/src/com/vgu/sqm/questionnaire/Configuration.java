package com.vgu.sqm.questionnaire;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Configuration {
	public static Connection getAcademiaConnection() throws SQLException, NamingException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env"); 
		DataSource ds = (DataSource) envContext.lookup("jdbc/schema2");
		return ds.getConnection();
	} 
}