import java.net.MalformedURLException;
import java.net.URL;

public class URLFactory {
	private final String NEWS_API_URL = "https://newsapi.org/v1/articles";
	private final String API_KEY_PATH = "C:/SentimentNews/key.txt";
	private APIKey newsAPIKey;

	public URL constructNewsAPISite(String nameOfSite) {
		if (newsAPIKey == null) {
			newsAPIKey = new APIKey(API_KEY_PATH);
		}
		return constructNewsAPIUrl(nameOfSite);

	}

	private URL constructNewsAPIUrl(String website) {
		URL url = null;
		String strUrl = NEWS_API_URL + "?source=" + website + "&apikey=" + newsAPIKey.get();
		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
