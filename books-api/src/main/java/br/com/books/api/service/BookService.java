package br.com.books.api.service;

import java.util.List;

import br.com.books.api.controller.request.BookRequest;
import br.com.books.api.entity.Book;

public interface BookService {
	
	Book getById(int id);
	
	List<Book> findAll();
	
	public Book create(BookRequest bookRequest);
	
	Book create(Book book);
	
	public Book update(Book book);
	
	void delete(int id);
}
