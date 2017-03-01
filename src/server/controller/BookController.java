package server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.model.Book;
import server.service.BookService;

@RestController
public class BookController {
	private BookService bookservice = new BookService();
	
	@RequestMapping(value="/book/isbn/{isbn}")
	public ResponseEntity<Book> getBookByISBN(@PathVariable("isbn") String isbn){
		return new ResponseEntity<Book>(bookservice.getBookByISBN(isbn), HttpStatus.OK);
	}
}
