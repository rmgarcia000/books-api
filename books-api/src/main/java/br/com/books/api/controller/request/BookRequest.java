package br.com.books.api.controller.request;

public class BookRequest {

	private Integer id;
	private String tittle;
	private String description;
	private String isbn;
	private String languagem;

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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguagem() {
		return languagem;
	}

	public void setLanguagem(String languagem) {
		this.languagem = languagem;
	}
}
