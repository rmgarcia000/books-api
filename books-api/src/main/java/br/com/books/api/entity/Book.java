package br.com.books.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.books.api.controller.request.BookRequest;

@Entity
@Table(name = "tbl_book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tittle;
	private StringBuilder description;
	private String isbn;
	private String language;
	@Transient
	public List<String> urls = new ArrayList<>();

	public Book() {
		
	}
	
	public Book(BookRequest book) {
		super();
		this.tittle = book.getTittle();
		this.description = new StringBuilder(book.getDescription());
		this.isbn = book.getIsbn();
		this.language = book.getLanguagem();
	}	
	
	public Integer getId() {
		return id;
	}

	public Book setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description.toString();
	}
	
	public StringBuilder getBuilderDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = new StringBuilder(description);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String languagem) {
		this.language = languagem;
	}

}
