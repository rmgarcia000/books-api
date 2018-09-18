package br.com.books.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.books.api.controller.request.BookRequest;

@Entity
@Table(name = "tbl_book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tittle;
	private String description;
	private String isbn;
	private String language;

	public Book() {
		
	}
	
	public Book(BookRequest book) {
		super();
		this.tittle = book.getTittle();
		this.description = book.getDescription();
		this.isbn = book.getIsbn();
		this.language = book.getLanguagem();
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}
	
	public void concatDescription(String desc) {
		this.description = (this.description !=null)? this.description + desc : desc; 
	}

	
	public void setDescription(String description) {
		this.description = description;
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
