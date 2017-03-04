package server.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import server.model.Book;

@XmlRootElement(name = "BookReviewResponse")
public class BookReviewResponse {
	private List<Book> ls;

	public List<Book> getLs() {
		return ls;
	}

	public void setLs(List<Book> ls) {
		this.ls = ls;
	}
	
}
