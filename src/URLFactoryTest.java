import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class URLFactoryTest {

	@Test
	public void testnewsAPIURL() {

		URLFactory urlFactory = new URLFactory();
		URL constructedUrl = urlFactory.newsAPIURL("reddit-r-all");
		assertTrue(constructedUrl.toString().startsWith("http"));
		assertTrue(constructedUrl.toString().contains("reddit-r-all"));
	}

}
