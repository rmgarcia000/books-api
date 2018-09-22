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
public class HttpIsbnSnifferTest {

	HttpIsbnSniffer isbnSnifer = new HttpIsbnSniffer(null);

	@Test
	public void emptyDocTest() {
		Document doc = Jsoup.parse("");
		String isbn = isbnSnifer.searchOnDoc(doc);

		Assert.assertEquals("Unavailable", isbn);
	}
	
	private String pageWithOutIsbn = "<html>"
			+ "			<head>"
			+ "				<title>Tittle</title>"
			+ "			</head>"
			+ "			<body><p>Html</p></body>"
			+ "	   </html>";
	
	@Test
	public void pageWithoutIsbnTest() {
		Document doc = Jsoup.parse(pageWithOutIsbn);
		String isbn = isbnSnifer.searchOnDoc(doc);

		Assert.assertEquals("Unavailable", isbn);
	}
	
	
	String pageWithIsbnOnSameElement = "<html>"
			+ "			<head>"
			+ "				<title>Tittle</title>"
			+ "			</head>"
			+ "			<body>"
			+ "				<p>Html.</p>"
			+ "				<div> ISBN: 9922337766112</div>"	
			+ "			</body>"
			+ "	   </html>";	
	@Test
	public void pageWithIsbnOnSameElementTest() {
		Document doc = Jsoup.parse(pageWithIsbnOnSameElement);
		String isbn = isbnSnifer.searchOnDoc(doc);

		Assert.assertEquals("9922337766112", isbn);
	}
	
	String pageWithIsbnAround = "<html>"
			+ "			<head>"
			+ "				<title>Tittle</title>"
			+ "			</head>"
			+ "			<body>"
			+ "				<p>Html.</p>"
			+ "				<div> ISBN </div>"
			+ "				<div>9922337766112</div>"
			+ "			</body>"
			+ "	   </html>";	
	@Test
	public void pageWithIsbnAroundTest() {
		Document doc = Jsoup.parse(pageWithIsbnAround);
		String isbn = isbnSnifer.searchOnDoc(doc);

		Assert.assertEquals("9922337766112", isbn);
	}	

}
