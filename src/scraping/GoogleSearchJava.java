package scraping;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearchJava {

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	public static void main(String[] args) throws IOException {
		//Taking search term input from console
		String searchTerm = "carta el tenedor tako daruma madrid";

		String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm.replace(' ', '-') + "&num=" + 1;
		//without proper User-Agent, we will get 403 error
		Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

		//below will print HTML data, save it to a file and open in browser to compare
		//System.out.println(doc.html());

		//If google search results HTML change the <h3 class="r" to <h3 class="r1"
		//we need to change below accordingly
		Elements results = doc.select("h3.r > a");

		for (Element result : results) {
			String linkHref = result.attr("href");
			System.out.println(linkHref.substring(7, linkHref.indexOf("&")) + "\n");
			String linkText = result.text();
			System.out.println("Text::" + linkText + ", \nURL::" + linkHref.substring(6, linkHref.indexOf("&")));
		}
	}
}