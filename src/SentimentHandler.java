import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentHandler {

	private Sentiment sentiment;
	private double positiveThreshold;
	private POSTagger tagger;

	public SentimentHandler(Sentiment sentiment, POSTagger tagger) {
		this.sentiment = sentiment;
		this.tagger = tagger;
	}

	public void setPositiveThreshold(double threshold) {
		this.positiveThreshold = threshold;
	}

	/**
	 * Returns a list of articles that reaches at least the positive threshold percentage
	 * 
	 * @return Returns the list of positive articles
	 */
	public List<Article> getPositiveArticles(List<Article> allArticles) {
		List<Article> positiveArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			List<String> allWords = Arrays.asList(article.getHeader().split(" "));
			double percentPositive = sentiment.percentPositiveWords(allWords);
			if (percentPositive >= positiveThreshold) {
				positiveArticles.add(article);
			}
		}

		return positiveArticles;
	}

	public List<Article> getPositiveArticlesConsideringAdjectives(List<Article> allArticles) {
		List<Article> positiveArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			List<String> adjectives = tagger.extractAdjectives(article.getHeader());
			double percentPositive = sentiment.percentPositiveWords(adjectives);
			if (percentPositive >= positiveThreshold) {
				positiveArticles.add(article);
			}
		}
		return positiveArticles;
	}

}
