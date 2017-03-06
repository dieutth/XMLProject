package server.service;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import helper.LuceneHelper;
import server.dao.BookDAOImpl;
import server.model.Book;
import server.model.Review;
import server.response.BookReviewResponse;

public class BookService {
	private LuceneHelper lucene = new LuceneHelper();
	private BookDAOImpl bookImpl = new BookDAOImpl();
	
	public Book parse(String xmlText){
		Book b = new Book();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		String title = "";
		String review = "";
		String description = "";
		try {
		
			builder = factory.newDocumentBuilder();
			Document document;
			document = builder.parse(new InputSource(new StringReader(xmlText)));
			title = document.getElementsByTagName("title").item(0).getTextContent();
			review = document.getElementsByTagName("reviews_widget").item(0).getTextContent();
			description = document.getElementsByTagName("description").item(0).getTextContent();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.setTitle(title);
		b.setDescription(description);
		b.setGoodReadReviews(review);
		return b;
		
	}
	
	private void getReviews(Book b){
		String title = b.getTitle();
		List<Review> ls = bookImpl.getReviews(title);
//		List<Review> ls = new ArrayList<Review>();
//		Review r = new Review();
//		r.setContent("test review");
//		r.setTime(new Date(2017, 5, 3));
//		ls.add(r);
		b.setReviews(ls);
	}
	
	public BookReviewResponse getBookReviewByTitle(String searchTitle){
		List<String> titleList = getBookTitlesFromSearchTitle(searchTitle);
		BookReviewResponse brr = new BookReviewResponse();
		List<Book> bookList = new ArrayList<Book>();
		brr.setBooks(bookList);
		
		for (String title : titleList){
			System.out.println(title);
			String uri = "https://www.goodreads.com/book/title.xml?&key=1ceGv5zbND7H0OmgSdmkA&title="+ title;
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);
			Book b = parse(result);
			getReviews(b);
			bookList.add(b);
		}    
		return brr; 
	}
	
	public void addReview(String title, String review){
		bookImpl.insertReview(title, review);
	}
	public void updateReview(String title, int reviewID, String review){
		bookImpl.updateReview(title, reviewID, review);
	}
	private List<String> getBookTitlesFromSearchTitle(String searchTitle){
		List<String> result = lucene.getSuggestion(searchTitle);
		return result;
	}
	
	public void deleteReview(String title, int reviewID){
		bookImpl.deleteReview(title, reviewID);
	}
	
	
}
