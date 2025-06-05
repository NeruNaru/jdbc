package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
	// 0. import java.sql.*;

	// field
	private Connection conn = null;
	PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String Url = "jdbc:mysql://localhost:3306/web_db";
	private String Id = "web";
	private String Pw = "web";

	// editor
	public AuthorDAO() {
	}
	// method g/s
	// method normal

	// db 연결
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

	// 자원정리
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

	// author insert
	public int authorInsert(String name, String desc) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name); // 메소드의 파라미터
			pstmt.setString(2, desc); // 메소드의 파라미터

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		System.out.println("저장");

		return count;
	} // author insert

	// author update
	public int authorUpdate(int id, String name, String desc) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += " author_desc = ? ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name); // 메소드 파라미터
			pstmt.setString(2, desc); // 메소드 파라미터
			pstmt.setInt(3, id); // 메소드 파라미터

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		System.out.println("수정");

		return count;
	} // author update

	// author delete
	public int authorDelete(int id) {
		int count = -1;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		System.out.println("삭제");

		return count;
	} // author delete

	// author select(List)
	public List<AuthorVO> authorSelect() {

		// 리스트
		List<AuthorVO> authorList = new ArrayList<AuthorVO>();

		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (MySQL) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리 (java 리스트로 만든다)
			while (rs.next()) {

				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				// 데이터 객체로 만들기(묶기)
				AuthorVO authorVO = new AuthorVO(authorId, authorName, authorDesc);

				// 묶은 데이터를 리스트에 달기
				authorList.add(authorVO);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		this.close();

		return authorList;

	}

	// author select one
	public AuthorVO authorSelectOne(int id) {

		AuthorVO authorVo = null;

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	author_id ";
			query += " from author ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			rs.next();
			
			int authorId = rs.getInt("author_id");
			String authorName = rs.getString("author_name");
			String authorDesc = rs.getString("author_desc");

			authorVo = new AuthorVO(authorId, authorName, authorDesc);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return authorVo;
	}
}
