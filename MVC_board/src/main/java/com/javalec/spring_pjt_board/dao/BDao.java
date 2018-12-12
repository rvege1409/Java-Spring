package com.javalec.spring_pjt_board.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.javalec.spring_pjt_board.dto.BDto;
import com.javalec.spring_pjt_board.util.Constant;

import java.sql.DriverManager;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class BDao {
	
	public JdbcTemplate template =null;
	
	public BDao() {
		template = Constant.template;
	}
	
	public BDto contentView(String strbId) {
		
		//upHit(strID); 조회수 올리는 Dao 메서드
		String query = "select * from mvc_board where bId = ?";
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DriverManager.getConnection(URL, USER, PW);
			String query = "insert into mvc_board(bName,bTitle, bContent,bHit,bGroup,bStep, bIndent) values(?,?,?,0,0,0,0)";
			psmt = connection.prepareStatement(query);
			psmt.setString(1, bName);
			psmt.setString(2, bTitle);
			psmt.setString(3, bContent);
			int rn=psmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(psmt!=null) psmt.close();
				if(connection!=null) connection.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList <BDto> list() {
		String query = "select bId, bName,bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board";
		return (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
}
