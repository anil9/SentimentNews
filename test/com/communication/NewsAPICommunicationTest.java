package com.communication;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.article.Article;
import com.communication.NewsAPICommunication;
import com.communication.WebCommunication;

public class NewsAPICommunicationTest {

	private WebCommunication mockedWeb;
	private String threeArticlesJSON = "{\"status\":\"ok\",\"source\":\"google-news\",\"sortBy\":\"top\",\"articles\":[{\"title\":\"Trump to hold press conference on IS fight\",\"url\":\"https://trumpsfightonis.com\"},{\"title\":\"Trump to hold press conference on Russia fight\",\"url\":\"https://trumpsfightonrussia.com\"},{\"title\":\"Trump to hold press conference on Sweden fight\",\"url\":\"https://trumpsfightonsweden.com\"}]}";

	private NewsAPICommunication newsAPICommunication;

	@Before
	public void setUp() throws Exception {
		mockedWeb = Mockito.mock(WebCommunication.class);
		newsAPICommunication = new NewsAPICommunication(mockedWeb);
	}

	@Test
	public void testGetArticlesFromSite() throws MalformedURLException {
		Mockito.when(mockedWeb.readSiteResponse(null)).thenReturn(threeArticlesJSON);

		List<Article> articles = newsAPICommunication.getArticlesFromSite(null);

		assertEquals(3, articles.size());
		assertEquals("Trump to hold press conference on IS fight", articles.get(0).getHeader());
		assertEquals(new URL("https://trumpsfightonis.com").toString(), articles.get(0).getUrl().toString());
		assertEquals("Trump to hold press conference on Russia fight", articles.get(1).getHeader());
		assertEquals(new URL("https://trumpsfightonrussia.com").toString(), articles.get(1).getUrl().toString());
		assertEquals("Trump to hold press conference on Sweden fight", articles.get(2).getHeader());
		assertEquals(new URL("https://trumpsfightonsweden.com").toString(), articles.get(2).getUrl().toString());
	}

}
