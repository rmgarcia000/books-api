package br.com.books.api.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.books.api.entity.Book;

public class HttpToJava {

	private List<Book> books = new ArrayList<>();

	int id = 0;

	public List<Book> getBooks() {
		return this.books;
	}

	private void generateNewBook() {
		id++;
		books.add(new Book().setId(id));
	}

	private Book getNewBook() {
		generateNewBook();
		return getCurrentBook();
	}

	private Book getCurrentBook() {
		if (books.isEmpty()) {
			return getNewBook();
		}
		return books.get(books.size() - 1);
	}

	public void htmlToJava() throws IOException {
		Document doc = Jsoup.connect("https://kotlinlang.org/docs/books.html").get();
		Elements newsHeadlines = doc.select("article");
		Element article = newsHeadlines.first();

		// for (Element el : article.getAllElements()) {
		article.getAllElements().forEach(el -> {
			String currentElement = el.toString();

			if (currentElement.startsWith("<h2") && currentElement.endsWith("</h2>")) {
				generateNewBook();
				getCurrentBook().setTittle(el.select("h2").text().trim());

			} else if (currentElement.startsWith("<div class=\"event-lang\"") && currentElement.endsWith("</div>")) {
				getCurrentBook().setLanguage(el.select("div").text().trim());

			} else if (currentElement.startsWith("<a href=\"") && currentElement.endsWith("</a>")) {
				getCurrentBook().urls.add(el.select("a").attr("abs:href"));
				getCurrentBook().setIsbn(findIsbn(currentElement));

			} else if (currentElement.startsWith("<p>") && currentElement.endsWith("</p>")) {
				if (getCurrentBook().getBuilderDescription() == null) {
					getCurrentBook().setDescription("");
				}
				if (getCurrentBook().getBuilderDescription().length() > 0) {
					getCurrentBook().getBuilderDescription().append(System.getProperty("line.separator"));
				}

				el.select("a").unwrap();
				getCurrentBook().getBuilderDescription().append(el.text());
			}

		});

		findIsbnByUrl();
	}

	/// NEW CLASS

	private void findIsbnByUrl() {
		this.books.forEach(book -> {
			if (book.getIsbn() == null) {
				Set<String> uniqueSet = new HashSet<String>(book.urls);
				uniqueSet.forEach(url -> {
					try {
						if (book.getIsbn() == null) {
							book.setIsbn(getNavigateAndGetValueOf(url, "isbn"));
						}
					} catch (IOException e) {
					}
				});
			}

		});

	}

	private String getNavigateAndGetValueOf(String url, String pattern) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements elements = doc.select("body");

		String isbn = recursivePatternFind(pattern, elements);
		
		if(isbn == null) {
			return "Unavailable";
		}
		
		return isbn;
	}

	public int innerLoop = 0;
	private String recursivePatternFind(String pattern, Elements listOfElements) {
		boolean isbnAround = false;
		if (listOfElements != null) {
			for (Element element : listOfElements) {
				
				if(element.childNodeSize() > 0) {
					innerLoop++;
					System.out.println(innerLoop);
					String isbn = recursivePatternFind(pattern, element.children());
					if(isbn != null) {
						return isbn;
					}
				}
				
				String textElement = (element.text()).toUpperCase();		
				if (textElement.contains("ISBN")) {
					isbnAround = true;
				}
				if(isbnAround) {
					String isbnSearch = searchIsbnOnText(textElement, element);
					if(isbnSearch != null) {
						return isbnSearch;
					}
				}
			}
		}
		
		return null;
	}

	private String searchIsbnOnText(String text, Element element) {
		String regex_num = "[0-9]{13}";
		Matcher m = Pattern.compile(regex_num, Pattern.CASE_INSENSITIVE).matcher(text);
        while (m.find()) {
            return m.group(0);
        }
		
		return null;
	}

	private String findIsbn(String currentElement) {
		// TODO Auto-generated method stub
		return null;
	}
}
