package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {
	public static void main(String[] args) {
		
		List<BookVO> bList = new ArrayList<BookVO>();
		
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
			query += "select	book_id, ";
			query += "			title, ";
			query += "			pubs, ";
			query += "			pub_date, ";
			query += "			author_id ";
			//바인딩
			pstmt = conn.prepareStatement(query);
			// 4.결과처리
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				int author_id = rs.getInt("author_id");
				
				BookVO bookVO = new BookVO(book_id, title, pubs, pub_date, author_id);
				
				bList.add(bookVO);
			}
			
			for(BookVO bookVO : bList) {
				System.out.println(bookVO.getBook_id());
				System.out.println(bookVO.getTitle());
				System.out.println(bookVO.getPubs());
				System.out.println(bookVO.getPub_date());
				System.out.println(bookVO.getAuthor_id());
			}
			
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
