package client;

import org.springframework.web.client.RestTemplate;

import helper.LuceneHelper;
import server.model.Book;

public class MainClient {
	public static void main(String[] args) {
//		String uri = "http://localhost:8080/XMLProject/book/search/abc";
//	     
//		RestTemplate restTemplate = new RestTemplate();
//		String result = restTemplate.getForObject(uri, String.class);
//		     
//		System.out.println(result);
		LuceneHelper helper = new LuceneHelper();
		System.out.println(helper.getSuggestion("Handbook of popul"));
	}
}
