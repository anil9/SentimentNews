import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This application is receiving news from News API for free. Check them out at http://www.newsapi.org
 *
 */

public class NewsAPICommunicator {

	private final String API_URL = "https://newsapi.org/v1/articles";
	private APIKey key;

	public NewsAPICommunicator(APIKey key) {
		this.key = key;
	}

	public List<Article> getArticlesFromSite(String website) {
		String url = buildURL(website);
		String jsonResponse = readSiteResponse(url);
		List<Article> articles = populateArticleListFromJSON(jsonResponse);
		return articles;
	}

	private String buildURL(String website) {
		return API_URL + "?source=" + website + "&apikey=" + key.get();
	}

	private String readSiteResponse(String url) {
		String response = "";
		try {
			InputStream siteResponseStream = websiteResponseStream(url);
			response = inputStreamToString(siteResponseStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private InputStream websiteResponseStream(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		return con.getInputStream();
	}

	private String inputStreamToString(InputStream in) {
		java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private List<Article> populateArticleListFromJSON(String jsonResponse) {
		List<Article> articles = new ArrayList<Article>();
		try {
			List<JSONObject> jsonArticles = ConvertToJSONObjects(jsonResponse);
			for (JSONObject jsonArticle : jsonArticles) {
				articles.add(new Article(jsonArticle.getString("title"), jsonArticle.getString("url")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return articles;
	}

	private List<JSONObject> ConvertToJSONObjects(String response) throws JSONException {
		List<JSONObject> jsonArticles = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject(response);

		JSONArray jsonArticleArray = json.getJSONArray("articles");

		for (int i = 0; i < jsonArticleArray.length(); i++) {
			JSONObject jsonArticle = jsonArticleArray.getJSONObject(i);
			jsonArticles.add(jsonArticle);
		}
		return jsonArticles;
	}

}
