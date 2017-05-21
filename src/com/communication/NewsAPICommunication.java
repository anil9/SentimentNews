package com.communication;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.article.Article;

/**
 * This application is receiving news from News API for free. Check them out at http://www.newsapi.org
 *
 */

public class NewsAPICommunication {

	private WebCommunication webCommunication;

	public NewsAPICommunication(WebCommunication webCommunication) {
		this.webCommunication = webCommunication;
	}

	public List<Article> getArticlesFromSite(URL url) {
		String jsonResponse = webCommunication.readSiteResponse(url);
		List<Article> articles = translateJSONToArticles(jsonResponse);
		return articles;
	}

	private List<Article> translateJSONToArticles(String jsonResponse) {
		List<Article> articles = new ArrayList<Article>();
		try {
			List<JSONObject> jsonArticles = ConvertToJSONObjects(jsonResponse);
			for (JSONObject jsonArticle : jsonArticles) {
				articles.add(new Article(jsonArticle.getString("title"), new URL(jsonArticle.getString("url"))));
			}
		} catch (JSONException | MalformedURLException e) {
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
