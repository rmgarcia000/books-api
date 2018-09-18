package br.com.books.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.books.api.builder.HttpBookSniffer;
import br.com.books.api.builder.HttpIsbnSniffer;
import br.com.books.api.controller.request.BookRequest;
import br.com.books.api.entity.Book;
import br.com.books.api.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("{id}")
	public ResponseEntity<Book> getById(@PathVariable int id) {
		return ResponseEntity.ok(bookService.getById(id));
	}

	@GetMapping("search-and-save")
	public ResponseEntity<List<Book>> searchBooks() throws Exception {
		HttpBookSniffer converter = new HttpBookSniffer("https://kotlinlang.org/docs/books.html");
		
		if (!converter.searchBooks().getBooks().isEmpty()) {
			HttpIsbnSniffer isbnSniffer = new HttpIsbnSniffer(converter.getBooks());
			isbnSniffer.findAllIsbnByUrl();

			for (Book book : isbnSniffer.getBooks()) {
				bookService.create(book);
			}
			//return ResponseEntity.ok(isbnSniffer.getBooks());
		}

		//return null;
		return ResponseEntity.ok(bookService.findAll());
	}

	@GetMapping()
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}

	@PostMapping
	public ResponseEntity<Book> save(@RequestBody BookRequest book) {
		return ResponseEntity.ok(bookService.create(book));
	}

	@PutMapping
	public ResponseEntity<Book> update(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.update(book));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		bookService.delete(id);
	}

}
