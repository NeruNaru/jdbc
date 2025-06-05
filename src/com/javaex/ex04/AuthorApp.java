package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDAO authorDAO = new AuthorDAO();
		
		//int co1 = authorDAO.authorInsert("김종국", "런닝맨");
//		
//		int co2 = authorDAO.authorUpdate(9,"조세호","유퀴즈");
//		
//		int co3 = authorDAO.authorDelete(2);
//		
//		List<AuthorVO> authorList = authorDAO.authorSelect();
//		System.out.println(authorList);
		
		AuthorVO authorVO = authorDAO.authorSelectOne(5);

		
	}

}
