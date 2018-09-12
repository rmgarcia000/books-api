package br.com.books.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.books.api.entity.Book;

public interface BookRepository extends  JpaRepository<Book, Integer>{

}
