import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentHandler {

	private Sentiment sentiment;
	private NewsAPICommunication content;
	private POSTagger tagger;

	private SentimentHandler() {
	}

	public SentimentHandler(Sentiment sentiment, POSTagger tagger) {
		this.sentiment = sentiment;
		this.tagger = tagger;
	}

	/**
	 * Returns a list of articles that reaches at least the input threshold percentage
	 * 
	 * @param threshold
	 *            The percentage of positivity an article has to reach
	 * @return Returns the list of positive articles
	 */
	public List<Article> getPositiveArticles(List<Article> allArticles, double threshold) {
		List<Article> positiveArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			// List<String> adjectives = tagger.extractAdjectives(article.getHeader());
			List<String> allWords = Arrays.asList(article.getHeader().split(" "));
			double percentPositive = sentiment.calcPositivePercentage(allWords);
			// double percentPositive = sentiment.calcPositivePercentage(adjectives); // with adjectives
			// System.out.println("percentPositive was: " + percentPositive);
			if (percentPositive >= threshold) {
				positiveArticles.add(article);
			}
		}

		return positiveArticles;
	}

}
