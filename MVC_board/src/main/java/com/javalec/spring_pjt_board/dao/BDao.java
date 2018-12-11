package com.javalec.spring_pjt_board.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.context.annotation.Configuration;

import com.javalec.spring_pjt_board.dto.BDto;

import java.sql.DriverManager;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class BDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false";
	private static final String USER ="root";
	private static final String PW = "ckddn123";
	
	public BDao() {
		
		/*Class.forName(DRIVER);
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb, USER, PW")){
			System.out.println(conn);
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
	}
	public ArrayList <BDto> list() {
		//database 접근해서 가져오기
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(URL, USER, PW);
			String query = "select bid, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIntent from mvc_board order by bGroup desc, bStep asc";
			ps = connection.prepareStatement(query);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName=resultSet.getString("bName");
				String bTitle=resultSet.getString("bTitle");
				String bContent=resultSet.getString("bContent");
				java.sql.Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit=resultSet.getInt("bId");
				int bGroup=resultSet.getInt("bId");
				int bStep=resultSet.getInt("bId");
				int bIndent=resultSet.getInt("bId");
				BDto dto = new BDto(bId,bName,bContent,bTitle,bDate,bHit,bGroup,bStep,bIndent);
				dtos.add(dto);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(resultSet!=null) resultSet.close();
			if(ps!=null) ps.close();
			if(connection != null) connection.close();
			}
			catch (Exception e) {
				
			}
		}
		
		return dtos;
	}
}
