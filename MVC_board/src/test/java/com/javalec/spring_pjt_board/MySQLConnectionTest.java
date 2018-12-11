package com.javalec.spring_pjt_board;
import java.sql.Connection;

import java.sql.DriverManager;



import org.junit.Test;
import org.springframework.core.env.SystemEnvironmentPropertySource;



public class MySQLConnectionTest {

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false";

	private static final String USER ="root";

	private static final String PW = "ckddn123";

	

	@Test
	public void testConnection() throws Exception{

		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(URL, USER, PW))	{

			System.out.println(con);
			System.out.println("¼º°ø");

			

		}catch(Exception e){

			System.err.println(e);

		}

	}

}

