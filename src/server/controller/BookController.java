package server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import server.model.Book;
import server.response.BookReviewResponse;
import server.service.BookService;

@RestController
public class BookController {
	private BookService bookservice = new BookService();
	
	@RequestMapping(value="/book/search/{searchTitle}", method=RequestMethod.GET)
	public ResponseEntity<BookReviewResponse> searchBook(@PathVariable("searchTitle") String searchTitle){
		BookReviewResponse brr = bookservice.getBookReviewByTitle(searchTitle);
		return new ResponseEntity<BookReviewResponse>(brr, HttpStatus.OK);
	}
	
	@RequestMapping(value="/book/comments/{title}", method=RequestMethod.POST)
	public ResponseEntity<String> addComment(@RequestBody String review, @PathVariable("title") String title){
//		title = "Harry Potter 1";
		bookservice.addReview(title, review);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
}
