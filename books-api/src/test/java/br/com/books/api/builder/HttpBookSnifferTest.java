package br.com.books.api.builder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpBookSnifferTest {

	public HttpBookSniffer bookSniffer = new HttpBookSniffer("");
	
	@Test
	public void noContentTest() throws Exception {
		Document doc = Jsoup.parse("");
		bookSniffer.searchByJsoupDocument(doc);
		
		Assert.assertTrue(bookSniffer.getBooks().isEmpty());
	}
	
	private final String noInitTag = 
			  "<html>"
			+ "	<body>"
			+ "		hello"
			+ "	</body>"
			+ "</html>";
	
	@Test
	public void noInitTag() throws Exception {
		Document doc = Jsoup.parse(noInitTag);
		bookSniffer.searchByJsoupDocument(doc);

		Assert.assertTrue(bookSniffer.getBooks().isEmpty());
	}
	
	private final String noBooks = 
			  "<html>"
			+ "	<body>"
			+ "		<article>"
			+ "			<div>Articles of article</div>"
			+ "			<div>Articles of article</div>"
			+ "		</article>"
			+ "	</body>"
			+ "</html>";
	
	@Test
	public void noBooksTest() throws Exception {
		Document doc = Jsoup.parse(noBooks);
		bookSniffer.searchByJsoupDocument(doc);

		Assert.assertTrue(bookSniffer.getBooks().isEmpty());
	}
	
	private final String oneBook = 
			  "<html>"
			+ "	<body>"
			+ "		<article>"
	        + "			<h2 style=\"clear: left\">Kotlin for Android Developers</h2>"
	        + "<div class=\"event-lang\">en</div>"
	        + " <a href=\"https://leanpub.com/kotlin-for-android-developers\"><img src=\"/assets/images/kotlin-for-android-developers.png\" class=\"book-cover-image\"></a>"
	        + "<p><a href=\"https://leanpub.com/kotlin-for-android-developers\">Kotlin for Android Developers</a> is a book by"
	        + "    Antonio Leiva showing"
	        + "how Kotlin can be used for creating an Android application from scratch.</p>"
			+ "		</article>"
			+ "	</body>"
			+ "</html>";
	
	@Test
	public void oneBookBooksTest() throws Exception {
		Document doc = Jsoup.parse(oneBook);
		bookSniffer.searchByJsoupDocument(doc);

		Assert.assertEquals(1, bookSniffer.getBooks().size());
	}
	
	private final String books = 
			  "<html>"
			+ "	<body>"
			+ "		<article>"
	        + "			<h2 style=\"clear: left\">Kotlin for Android Developers</h2>"
	        + "<div class=\"event-lang\">en</div>"
	        + " <a href=\"https://leanpub.com/kotlin-for-android-developers\"><img src=\"/assets/images/kotlin-for-android-developers.png\" class=\"book-cover-image\"></a>"
	        + "<p><a href=\"https://leanpub.com/kotlin-for-android-developers\">Kotlin for Android Developers</a> is a book by"
	        + "    Antonio Leiva showing"
	        + "how Kotlin can be used for creating an Android application from scratch.</p>"
	        + "<h2>Kotlin in Action</h2>"
	        + "<div class=\"event-lang\">en</div>"
	        + "<a href=\"https://manning.com/books/kotlin-in-action\"><img src=\"/assets/images/kotlin-in-action.png\" class=\"book-cover-image\"></a>"
	        + "<p><a href=\"https://manning.com/books/kotlin-in-action\">Kotlin in Action</a> teaches you to use the Kotlin"
	        + "  language for"
	        + "  production-quality applications. Written for experienced Java developers, this example-rich book goes"
	        + " further than"
	        + "    most language books, covering interesting topics like building DSLs with natural language syntax.</p>"
	        + "<p>The book is written by Dmitry Jemerov and Svetlana Isakova, developers on the Kotlin team.</p>"
	        + "<p>Chapter 6, covering the Kotlin type system, and chapter 11, covering DSLs, are available as a free preview on"
	        + "    the"
	        + "    <a href=\"https://www.manning.com/books/kotlin-in-action#downloads\">publisher Web site</a>.</p>"
	        + "                 "
	        + "<h2 style=\"clear: left\">Kotlin for Android Developers</h2>"
	        + "<div class=\"event-lang\">en</div>"
	        + "<a href=\"https://leanpub.com/kotlin-for-android-developers\"><img src=\"/assets/images/kotlin-for-android-developers.png\" class=\"book-cover-image\"></a>"
	        + "<p><a href=\"https://leanpub.com/kotlin-for-android-developers\">Kotlin for Android Developers</a> is a book by"
	        + "    Antonio Leiva showing"
	        + "    how Kotlin can be used for creating an Android application from scratch.</p>"
			+ " </article>"
			+ "	</body>"
			+ "</html>";
	
	@Test
	public void BooksAndMoreDescriptonTest() throws Exception {
		Document doc = Jsoup.parse(books);
		bookSniffer.searchByJsoupDocument(doc);

		Assert.assertEquals(3, bookSniffer.getBooks().size());
	}	
}
