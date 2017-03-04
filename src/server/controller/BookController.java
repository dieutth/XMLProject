package server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.model.Book;
import server.response.BookReviewResponse;
import server.service.BookService;

@RestController
public class BookController {
	private BookService bookservice = new BookService();
	
	@RequestMapping(value="/book/search/{searchTitle}")
	public ResponseEntity<BookReviewResponse> getBookByISBN(@PathVariable("searchTitle") String searchTitle){
		BookReviewResponse brr = bookservice.getBookReviewByTitle(searchTitle);
		return new ResponseEntity<BookReviewResponse>(brr, HttpStatus.OK);
	}
}
