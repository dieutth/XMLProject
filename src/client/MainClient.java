package client;

import org.springframework.web.client.RestTemplate;

public class MainClient {
	public static void main(String[] args) {
		String uri = "http://localhost:8080/XMLProject/book/isbn/0441172717";
	     
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		     
		System.out.println(result);
	}
}
