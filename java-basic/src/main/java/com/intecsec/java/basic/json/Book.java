package com.intecsec.java.basic.json;

public class Book {
    private String category;
    private String title;
    private String author;
    private String year;
    private int price;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [category=" + category + ", title=" + title + ", author=" + author 
				+ ", year=" + year + ", price=" + price + "]";
	}   
}

