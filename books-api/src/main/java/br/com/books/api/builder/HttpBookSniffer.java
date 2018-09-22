package br.com.books.api.builder;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.books.api.entity.Book;

public class HttpBookSniffer {

	public HttpBookSniffer(String url) {
		this.url = url;
	}

	private String url;

	private List<BookInSearch> books = new ArrayList<>();

	public List<BookInSearch> getBooks() {
		return this.books;
	}

	private HttpBookSniffer generateNewBook() {
		books.add(new BookInSearch());
		return this;
	}

	private Book getNewBook() {
		generateNewBook();
		return getCurrentBook();
	}

	private Book getCurrentBook() {
		if (books.isEmpty()) {
			return getNewBook();
		}
		return books.get(books.size() - 1).getBook();
	}

	private HttpBookSniffer addUrlForCuurentBook(String url) {
		if (books.isEmpty()) {
			generateNewBook();
		}
		books.get(books.size() - 1).addUrl(url);

		return this;
	}

	public HttpBookSniffer searchBooksByUrl() throws Exception {
		Document doc = Jsoup.connect(this.url).get();
		searchByJsoupDocument(doc);

		return this;
	}

	public void searchByJsoupDocument(Document doc) {
		Elements newsHeadlines = doc.select("article");
		Element article = newsHeadlines.first();
		if (article != null && article.getAllElements() != null) {
			article.getAllElements().forEach(el -> {
				String currentElement = el.toString();

				if (currentElement.startsWith("<h2") && currentElement.endsWith("</h2>")) {
					generateNewBook();
					generateTittle(el);

				} else if (currentElement.startsWith("<div class=\"event-lang\"")
						&& currentElement.endsWith("</div>")) {
					generateLanguage(el);

				} else if (currentElement.startsWith("<a href=\"") && currentElement.endsWith("</a>")) {
					keepUrls(el);

				} else if (currentElement.startsWith("<p>") && currentElement.endsWith("</p>")) {
					generateDescription(el);
				}

			});
		}
	}

	private void generateDescription(Element el) {
		if (getCurrentBook().getDescription() != null && getCurrentBook().getDescription().length() > 0) {
			getCurrentBook().concatDescription(System.getProperty("line.separator"));
		}

		el.select("a").unwrap();
		getCurrentBook().concatDescription(el.text());
	}

	private void keepUrls(Element el) {
		addUrlForCuurentBook(el.select("a").attr("abs:href"));
	}

	private void generateLanguage(Element el) {
		getCurrentBook().setLanguage(el.select("div").text().trim());
	}

	private void generateTittle(Element el) {
		getCurrentBook().setTittle(el.select("h2").text().trim());
	}

}
