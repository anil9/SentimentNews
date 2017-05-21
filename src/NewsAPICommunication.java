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

public class NewsAPICommunication {

	public List<Article> getArticlesFromSite(URL url) {
		String jsonResponse = new WebCommunication().readSiteResponse(url);
		List<Article> articles = translateJSONToArticles(jsonResponse);
		return articles;
	}

	private List<Article> translateJSONToArticles(String jsonResponse) {
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
