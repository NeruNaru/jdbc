package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	// field
	private Connection conn = null;
	PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String Url = "jdbc:mysql://localhost:3306/web_db";
	private String Id = "web";
	private String Pw = "web";

	// editor
	private void BookDAO() {
	}

	// method normal

	// connect database
	private void connect() {
		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(Driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(Url, Id, Pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // connect

	// resource close
	private void close() {
		// 5. 자원정리
		try {
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

	// ====================================================================================

	// book insert
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(null, ?, ?, ?, ?) ";
			System.out.println(query);
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // book insert end

	// book update
	public int bookUpdate(String title, String pubs, String pubDate, int bookId) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update book ";
			query += " set	title = ?, ";
			query += "		pubs = ?, ";
			query += "		pub_date = ? ";
			query += " where book_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, bookId);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 수정완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // book update end

	// book delete
	public int bookDelete(int bookId) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			// 실행
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println(count + "건 삭제완료");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	} // book delete end

	// book select
	public List<BookVO> bookSelect() {
		List<BookVO> bookList = new ArrayList<BookVO>();

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
			query += "        author_id ";
			query += " from book ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");

				// 데이터 객체로 만들기(묶기)
				BookVO bookVO = new BookVO(bookId, title, pubs, pubDate, authorId);

				// 묶은 데이터를 리스트에 달기
				bookList.add(bookVO);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return bookList;
	} // book select end

	// book select one
	public BookVO bookSelectOne(int id) {

		BookVO bookVO = null;

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
			query += "        author_id ";
			query += " from book ";
			query += " where book_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			rs.next();

			int bookId = rs.getInt("book_id");
			String title = rs.getString("title");
			String pubs = rs.getString("pubs");
			String pubDate = rs.getString("pub_date");
			int authorId = rs.getInt("author_id");

			bookVO = new BookVO(bookId, title, pubs, pubDate, authorId);


		} catch (

		SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return bookVO;
	}
}
