package server.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "book")
public class Book implements Serializable  {
//	@XmlAttribute
	@XmlElement
	private String title;
	
//	@XmlElement
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//	
//	@XmlElement
//	private int publishYear;
//	
	@XmlElement
	private String reviews;
	
	
	//constructor
	public Book (String title, String reviews){
		setTitle(title);
		setReviews(reviews);
	}
	public Book(){}
//	public String getReviews() {
//		return reviews;
//	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
//	
//	public String getTitle() {
//		return title;
//	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public String getIsbn() {
//		return isbn;
//	}
//	public void setIsbn(String isbn) {
//		this.isbn = isbn;
//	}
//	public int getPublishYear() {
//		return publishYear;
//	}
//	public void setPublishYear(int publishYear) {
//		this.publishYear = publishYear;
//	}
	
	
}
