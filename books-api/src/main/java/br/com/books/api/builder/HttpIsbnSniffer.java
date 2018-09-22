package br.com.books.api.builder;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.books.api.entity.Book;

public class HttpIsbnSniffer {
	private List<BookInSearch> books;

	private final String keyWord = "ISBN";
	private final String initElement = "body";
	private final String notFindIsbn = "Unavailable";
	private final String internationalSbn = "^[0-9]{3}\\-[0-9]{10}$";
	private final String patternIsbnBr = "[0-9]{13}";
	private List<String> patterns = Arrays.asList(internationalSbn, patternIsbnBr);	

	public HttpIsbnSniffer(List<BookInSearch> books) {
		this.books = books;
	}
	
	public void addPatter(String regex) {
		this.patterns.add(regex);
	}
	
	public void clearPatter() {
		this.patterns.clear();
	}	

	public void findAllIsbnByUrl() {
		for (BookInSearch bookIS : books) {
			searchOne(bookIS);
		}
	}
	
	public List<Book> getBooks() {
		 return books.stream().map(book -> book.getBook()).collect(Collectors.toList());
	}

	private boolean searchOne(BookInSearch bookIS) {
		Book book = bookIS.getBook();
		for (String url : bookIS.getUrls()) {
			if (book.getIsbn() == null) {
				book.setIsbn(sniffByUrl(url));
				return true;
			}
		}

		return false;
	}

	private String sniffByUrl(String url) {
		try {
			Document doc = Jsoup.connect(url.trim()).get();
			return searchOnDoc(doc);
		} catch (Exception ex) {
			return this.notFindIsbn;
		}
	}
	
	public String searchOnDoc(Document doc) {
		Elements elements = doc.select(this.initElement);

		String isbn = recursivePatternFind(elements);

		if (isbn == null) {
			return this.notFindIsbn;
		}

		return isbn;
	}
	
	private String recursivePatternFind(Elements listOfElements) {
		boolean isbnAround = false;
		if (listOfElements != null) {
			for (Element element : listOfElements) {
				
				if(element.childNodeSize() > 0) {
					String isbn = recursivePatternFind(element.children());
					if(isbn != null) {
						return isbn;
					}
				}
				
				String textElement = (element.text()).toUpperCase();		
				if (textElement.contains(this.keyWord)) {
					isbnAround = true;
				}
				if(isbnAround) {
					String isbnSearch = searchIsbnOnText(textElement);
					if(isbnSearch != null) {
						return isbnSearch;
					}
				}
			}
		}
		
		return null;
	}
	
	private String searchIsbnOnText(String text) {
		for(String patter: patterns) {
			Matcher m = Pattern.compile(patter, Pattern.CASE_INSENSITIVE).matcher(text);
	        while (m.find()) {
	            return cleanIsbn(m.group(0));
	        }
		}       
		
		return null;
	}
	
	private String cleanIsbn(String isbn) {
		return isbn.replace("-", "");
	}

}
