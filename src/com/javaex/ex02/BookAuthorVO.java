package com.javaex.ex02;

public class BookAuthorVO {
	//field
	private int book_id;
	private String title;
	private String pubs;
	private String pub_date;
	private int author_id;
	private String author_name;
	private String author_desc;
	//editor
	public BookAuthorVO() {
		super();
	}
	public BookAuthorVO(int book_id, String title, String pubs, String pub_date, int author_id, String author_name,
			String author_desc) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_desc = author_desc;
	}
	//method g/s
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_desc() {
		return author_desc;
	}
	public void setAuthor_desc(String author_desc) {
		this.author_desc = author_desc;
	}
	//method normal
	@Override
	public String toString() {
		return "BookAuthorVO [book_id=" + book_id + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
				+ ", author_id=" + author_id + ", author_name=" + author_name + ", author_desc=" + author_desc + "]";
	}
}
