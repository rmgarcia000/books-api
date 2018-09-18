package br.com.books.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.books.api.controller.request.BookRequest;
import br.com.books.api.entity.Book;
import br.com.books.api.repository.BookRepository;
import br.com.books.api.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book getById(int id) {
		Optional<Book> book =  bookRepository.findById(id);
		
		if(!book.isPresent()) {
			//TO DO implements Exception and Exception handler
		}
		
		return book.get();
	}
	
	public List<Book> findAll() {
		return  bookRepository.findAll();
	}
	
	public Book create(BookRequest bookRequest) {
		Book book = new Book(bookRequest);
		return create(book);
	}
	
	public Book create(Book book) {
		return bookRepository.save(book);
	}	
	
	public Book update(Book book) {
		if(book.getId() == null || book.getId() == 0) {
			//TO DO implements Exception and Exception handler
		}
		return bookRepository.save(book);
	}
	
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

}
