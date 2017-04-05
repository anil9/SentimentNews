import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class Content {

	private List<Article> articles;
	private String APIKey, website;
	private final String API_URL = "https://newsapi.org/v1/articles";

	public Content() {
		APIKey = readAPIKey();
		website = "techcrunch";
		articles = importArticles();
	}

	public Content(String website) {
		APIKey = readAPIKey();
		this.website = website;
		articles = importArticles();
	}

	private List<Article> importArticles() {
		String url = API_URL + "?source=" + website + "&apikey=" + APIKey;
		String jsonResult = null;
		List<Article> returnList = new ArrayList<Article>();
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			jsonResult = response.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JSON: " + jsonResult);

		JSONObject json;
		try {
			json = new JSONObject(jsonResult);

			JSONArray jsonArticles = json.getJSONArray("articles");

			for (int i = 0; i < jsonArticles.length(); i++) {
				JSONObject jsonArticle = jsonArticles.getJSONObject(i);
				returnList.add(new Article(jsonArticle.getString("title"), jsonArticle.getString("url")));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnList;
	}

	private String readAPIKey() {
		String key = "";
		try {
			Scanner sc = new Scanner(new File("C:/SentimentNews/key.txt"));
			key = sc.nextLine().trim();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return key;
	}

	public static void main(String[] args) {
		Content c = new Content("reddit-r-all");
		c.articles.forEach((a) -> System.out.println(a.getHeader()));
	}

}
