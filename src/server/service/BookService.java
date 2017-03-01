package server.service;

import org.springframework.web.client.RestTemplate;

import server.model.Book;

public class BookService {
	
	public Book parse(String xmlText){
		int startInd = xmlText.indexOf("<title>");
		int endInd = xmlText.indexOf("</title>");
		
		String title = xmlText.substring(startInd, endInd);
//		String reviews = xmlText.subString/
		return new Book(title, "");
		
	}
	public Book getBookByISBN(String isbn){
		String uri = "https://www.goodreads.com/book/isbn/"+ isbn + "?key=1ceGv5zbND7H0OmgSdmkA";
	     
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		    
		return parse(result); 
	}
}
