package server.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import server.model.Book;

@XmlRootElement(name = "BookReviewResponse")
public class BookReviewResponse {
	private List<Book> books;

	@XmlElementWrapper
	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> ls) {
		this.books = ls;
	}
	
}
