package edu.kh.bookprogram.model.vo;

public class Program {
	
	private String title; //책이름
	private String author; //작가
	private int price; //가격
	private String publisher; //출판사
	
	
	public Program() { //기본 생성자
		
	}


	public Program(String name, String author, int price, String publisher) { //매개변수 생성자
		super();
		this.title = name;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
	}
	//getter / setter

	public String getName() {
		return title;
	}


	public void setName(String name) {
		this.title = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	//toString문 Override
	@Override
	public String toString() {
		return title + "\s" +  author + "\s" + price + "원"+ "\s" + publisher;
		
	}
	
	

	
}
