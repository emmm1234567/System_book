package com.book.pojo;

public class BookInfo {
    private int id;
    private String bookName;
    private String author;
    private int categoryId;
    private String publisher;
    private double price;
    private String photo;
    private BookCategory cate;       
	public BookCategory getCate() {
		return cate;
	}
	public void setCate(BookCategory cate) {
		this.cate = cate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public BookInfo(int id, String bookName, String author, int categoryId, String publisher, double price, String photo) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.categoryId = categoryId;
		this.publisher = publisher;
		this.price = price;
		this.photo = photo;
	}
	public BookInfo() {
		super();
	}
	public BookInfo(String bookName, String author, int categoryId, String publisher, double price, String photo) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.categoryId = categoryId;
		this.publisher = publisher;
		this.price = price;
		this.photo = photo;
	
}
}
