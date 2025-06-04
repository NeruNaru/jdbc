package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		List<AuthorVO> aList = new ArrayList<AuthorVO>();
		
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/web_db";
			conn = DriverManager.getConnection(url, "web", "web");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select	author_id, ";
			query += "			author_name, ";
			query += "			author_desc ";
			query += "from author ";
			System.out.println(query);
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			
			// 실행
			rs = pstmt.executeQuery();
			// 4.결과처리
			// rs 표가 들어가있다 (하지만 사용이 불편함)  ==> 이를 리스트 형식으로 변경
			
			while(rs.next()) {
				
				//ResultSet 의 데이터를 자바의 변수에 담는다
				int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");
				
				// 자바의 데이터를 VO로묶는다
				AuthorVO athorVO = new AuthorVO(author_id, author_name, author_desc);
				
				//VO를 aList에 추가하기
				aList.add(athorVO);
				
			}
			
			for(int i = 0 ; i<aList.size() ; i++) {
				System.out.println(aList.get(i).toString());
			}
			
			for(AuthorVO author : aList) {
				System.out.println(author.getAuthor_name());
			}
			
			System.out.println("----------------------------------------------------");
			for(int i = 0 ; i<aList.size() ; i++) {
				String name = aList.get(i).getAuthor_name();
				String desc = aList.get(i).getAuthor_desc();
				System.out.println((i+1) + "." + name + "(" + desc + ")");
			}
			System.out.println("----------------------------------------------------");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
