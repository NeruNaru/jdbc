package com.javaex.ex03;

import java.util.ArrayList;
import java.util.List;

import com.javaex.ex01.AuthorVO;

public class AuthorApp {

	public static void main(String[] args) {
		//메인 프로그램
		
		AuthorDAO authorDao = new AuthorDAO();
		
//		int count = authorDao.authorInsert("박경리", "토지");
//		if(count>0) {
//			System.out.println("등록되었습니다");
//		} if(count<0) {
//			System.out.println("알 수 없는 오류 발생");
//		} else {
//			System.out.println("등록되지 않았습니다");
//		}
		
		
		/*
		authorDao.authorInsert("박경리", "천호동 강풀거리");
		authorDao.authorInsert("강풀", "천호동 강풀거리");
		authorDao.authorInsert("강풀", "천호동 강풀거리");
		*/
		
		// authorDao.authorUpdate(3, "강호동", "운동선수");
		
//		authorDao.authorUpdate(3, "강호동", "운동선수");
		
		List<AuthorDAO> aList = new ArrayList<AuthorDAO>();
		for(int i = 0 ; i<aList.size() ; i++) {
			String result = aList.get(i).toString();
			System.out.println(result);
		}
		
	}

}
