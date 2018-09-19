package br.com.books.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.books.api.controller.request.BookRequest;
import br.com.books.api.entity.Book;
import br.com.books.api.exception.BusinessException;
import br.com.books.api.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepositoryMock;

	@Test
	public void findByIdTest() throws BusinessException {
		Book mock = new Book();
		mock.setId(1);
		mock.setDescription("desc");
		mock.setIsbn("123");
		mock.setLanguage("en");
		mock.setTittle("tittle");

		Mockito.when(bookRepositoryMock.findById(mock.getId())).thenReturn(Optional.of(mock));

		Book book = bookService.getById(mock.getId());

		Assert.assertEquals(mock.getId(), book.getId());
		Assert.assertEquals(mock.getDescription(), book.getDescription());
		Assert.assertEquals(mock.getIsbn(), book.getIsbn());
		Assert.assertEquals(mock.getLanguage(), book.getLanguage());
		Assert.assertEquals(mock.getTittle(), book.getTittle());
	}
	
	@Test(expected = BusinessException.class)
	public void bookNotFoundTest() throws BusinessException {
		Mockito.when(bookRepositoryMock.findById(1)).thenReturn(Optional.of(null));

		bookService.getById(1);
	}	
	
	@Test
	public void findAllTest() {
		List<Book> mock = Arrays.asList(new Book(), new Book(), new Book());
		Mockito.when(bookRepositoryMock.findAll()).thenReturn(mock);
		
		List<Book> list = bookService.findAll();
		
		Assert.assertEquals(mock.size(), list.size());
	}
	
	@Test
	public void createTest() {
		BookRequest mock = new BookRequest();
		mock.setDescription("desc");
		mock.setLanguagem("en");
		mock.setTittle("tittle");
		Book bookMock = new Book(mock);
		bookMock.setId(1);
		
		Mockito.when(bookRepositoryMock.save(bookMock)).thenReturn(bookMock);
		
		bookService.create(mock);
	}
	
	@Test
	public void updateSuccessTest() throws BusinessException {
		Book mock = new Book();
		mock.setId(1);
		mock.setDescription("desc");
		mock.setIsbn("123");
		mock.setLanguage("en");
		mock.setTittle("tittle");
		
		Mockito.when(bookRepositoryMock.save(mock)).thenReturn(mock);
		
		Book response = bookService.update(mock);
		
		Assert.assertEquals(mock, response);
	}
	
	@Test(expected = BusinessException.class)
	public void updateNoIdExceptionTest() throws BusinessException {
		Book mock = new Book();
		mock.setDescription("desc");
		mock.setIsbn("123");
		mock.setLanguage("en");
		mock.setTittle("tittle");
		
		Mockito.when(bookRepositoryMock.save(mock)).thenReturn(mock);
		
		bookService.update(mock);
	}
	
	
}
