package edu.kh.bookprogram.model.vo;

public class Favorites {

	private String title;
	private String author;
	
	public Favorites() {
		
	}

	public Favorites(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return title + "\s" + author;
	}
	
	
}
