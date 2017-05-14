import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This application is receiving news from News API for free. Check them out at http://www.newsapi.org
 *
 */

public class ArticleContainer {

	private final String API_URL = "https://newsapi.org/v1/articles";
	private final String API_KEY_PATH = "C:/SentimentNews/key.txt";
	private List<Article> articles;
	private String APIKey;

	public ArticleContainer() {
		importAPIKey();
	}

	private void importAPIKey() {
		try {
			readAPIKeyFromPath(API_KEY_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void readAPIKeyFromPath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		APIKey = sc.nextLine().trim();
		sc.close();
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void importArticlesFromSite(String website) {
		articles = importArticles(website);
	}

	private List<Article> importArticles(String website) {
		String url = buildURL(website);
		String jsonResponse = readSiteResponse(url);
		List<Article> articles = populateArticleListFromJSON(jsonResponse);
		return articles;
	}

	private String buildURL(String website) {
		return API_URL + "?source=" + website + "&apikey=" + APIKey;
	}

	private String readSiteResponse(String url) {
		String response = "";
		try {
			InputStream siteResponseStream = websiteResponseStream(url);
			response = readFromStream(siteResponseStream);
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

	private String readFromStream(InputStream siteResponseStream) throws IOException {
		StringBuilder response = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(siteResponseStream, StandardCharsets.UTF_8));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
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

	public static void main(String[] args) {
		ArticleContainer c = new ArticleContainer();
		c.importArticlesFromSite("reddit-r-all");
		c.articles.forEach((a) -> System.out.println(a.getHeader()));
	}

}
