package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		
		List<BookAuthorVO> baList = new ArrayList<BookAuthorVO>();
		
		/*
		 * select * from bo, author au where bo.author_id = au.author_id
		 */

		// List BookAuthorVO
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
			query += "select	bo.book_id, ";
			query += "			bo.title, ";
			query += "			bo.pubs, ";
			query += "			bo.pub_date, ";
			query += "			au.author_id, ";
			query += "			au.author_name, ";
			query += "			au.author_desc ";
			query += "from book bo, author au " ;
			query += "where bo.author_id = au.author_id ";
			// 바인딩
			pstmt = conn.prepareStatement(query);
			// 실행
			rs = pstmt.executeQuery();
			// 4.결과처리
			
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");
				
				BookAuthorVO bookauthorVO = new BookAuthorVO(book_id, title, pubs, pub_date, author_id, author_name, author_desc);
				
				baList.add(bookauthorVO);
			}
			
			for(BookAuthorVO bookauthorVO : baList) {
				System.out.print(bookauthorVO.getBook_id() + " / ");
				System.out.print(bookauthorVO.getTitle() + " / ");
				System.out.print(bookauthorVO.getPubs() + " / ");
				System.out.print(bookauthorVO.getPub_date() + " / ");
				System.out.print(bookauthorVO.getAuthor_id() + " / ");
				System.out.print(bookauthorVO.getAuthor_name() + " / ");
				System.out.print(bookauthorVO.getAuthor_desc() + "\n");
				System.out.println("========================================================================================================");
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
