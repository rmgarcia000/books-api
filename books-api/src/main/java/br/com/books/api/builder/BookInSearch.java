package br.com.books.api.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.books.api.entity.Book;

public class BookInSearch {
	private Book book = new Book();
	
	private List<String> urls = new ArrayList<>();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public void addUrl(String url) {
		if(!urls.contains(url)) {
			urls.add(url);
		}
	}

}
