package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
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
			query += "insert into book ";
			query += "values(null, ?, ?, ?, ?) ";
			System.out.println(query);
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "유시민의 글쓰기 특강");
			pstmt.setString(2, "생각의길");
			pstmt.setString(3, "2015-04-01");
			pstmt.setInt(4, 3);
			// 실행
			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다");
			
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
