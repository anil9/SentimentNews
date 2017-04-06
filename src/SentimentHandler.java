import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentHandler {

	private Sentiment sentiment;
	private Content content;
	private POSTagger tagger;

	public SentimentHandler(Sentiment sentiment, Content content, POSTagger tagger) {
		this.sentiment = sentiment;
		this.content = content;
		this.tagger = tagger;
	}

	public List<Article> getPositiveArticles(double positiveThreshold) {
		List<Article> allArticles = content.getArticles();
		List<Article> positiveArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			// List<String> adjectives = tagger.extractAdjectives(article.getHeader());
			List<String> allWords = Arrays.asList(article.getHeader().split(" "));
			double percentPositive = sentiment.calcPositivePercentage(allWords);
			// double percentPositive = sentiment.calcPositivePercentage(adjectives); // with adjectives
			// System.out.println("percentPositive was: " + percentPositive);
			if (percentPositive >= positiveThreshold) {
				positiveArticles.add(article);
			}
		}

		return positiveArticles;
	}

	public static void main(String[] args) {
		// Sample setup

		double positiveThreshold = 0.5;
		Content content = new Content("hacker-news");
		// System.out.println("All headlines:");
		// content.getArticles().forEach((article) -> System.out.println(article.getHeader()));
		System.out.println("Headlines with positive sentiment:");
		List<Article> positiveArticles = new SentimentHandler(new Sentiment(), content, new POSTagger())
				.getPositiveArticles(positiveThreshold);
		positiveArticles.forEach((article) -> System.out.println(article.getHeader()));
	}
}
