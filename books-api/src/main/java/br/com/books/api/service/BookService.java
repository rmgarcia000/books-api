package br.com.books.api.service;

import java.util.List;

import br.com.books.api.controller.request.BookRequest;
import br.com.books.api.entity.Book;
import br.com.books.api.exception.BusinessException;

public interface BookService {
	
	Book getById(int id) throws BusinessException;
	
	List<Book> findAll();
	
	public Book create(BookRequest bookRequest);
	
	Book create(Book book);
	
	public Book update(Book book) throws BusinessException;
	
	void delete(int id);
}
