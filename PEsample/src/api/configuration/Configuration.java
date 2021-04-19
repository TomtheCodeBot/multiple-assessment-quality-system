package api.configuration;

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
		DataSource ds = (DataSource) envContext.lookup("jdbc/pedatabase");
		return ds.getConnection();
	} 
	public static String getSecretKey() {
		return "aisjdnglk12jnm,slkjdJJKKL;;LKDAJJF03-2-9348JIEWREKJLDF";					
	}
}