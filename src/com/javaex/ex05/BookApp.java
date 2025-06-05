package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		/*
		 * book 테이블만
		 * bookDAO.bookInsert();
		 * bookDAO.bookUpdate();
		 * bookDAO.bookDelete();
		 * bookDAO.bookSelect();
		 * bookDAO.bookSelectOne();
		 * 
		 * 
		 * BookAuthorVO		book, author 테이블 조인
		 * bookDAO.bookSelectList();	==> 전체조회
		 */
		
		BookDAO bookDAO = new BookDAO();
		
//		int no1 = bookDAO.bookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 4);
		
//		int no2 = bookDAO.bookUpdate("패션왕", "중앙북스(books)", "2012-02-22", 2);
		
//		int no3 = bookDAO.bookDelete(1);
		
//		List<BookVO> bookList = bookDAO.bookSelect();
//		System.out.println(bookList);
		
		BookVO bookVO = bookDAO.bookSelectOne(3);
		System.out.println(bookVO);
	}

}
