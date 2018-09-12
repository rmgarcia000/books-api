package br.com.books.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.books.api.entity.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@GetMapping("{id}")
	public ResponseEntity<Book> getById(@PathVariable int id) {
		return ResponseEntity.ok(new Book());
	}
	
	@GetMapping()
	public ResponseEntity<List<Book>> findAll() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book());

		return ResponseEntity.ok(books);
	}
	
	@PostMapping
	public ResponseEntity<Book> save(@RequestBody Book book) {
		return ResponseEntity.ok(new Book());
	}
	
	@PutMapping
	public ResponseEntity<Book> update(@RequestBody Book book) {
		return ResponseEntity.ok(new Book());
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
	}	
	
	
	

}
