import java.util.ArrayList;
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

	public List<Article> getPositiveArticles() {
		List<Article> allArticles = content.getArticles();
		List<Article> positiveArticles = new ArrayList<Article>();
		for (Article article : allArticles) {
			List<String> adjectives = tagger.extractAdjectives(article.getHeader());
			// adjectives.forEach((adjective) -> System.out.println(adjective));
			int positives = sentiment.calcNumPositive(adjectives);
			if (positives > 0) {
				positiveArticles.add(article);
			}
		}

		return positiveArticles;
	}

	public static void main(String[] args) {
		// Sample setup

		Content content = new Content("reddit-r-all");
		// System.out.println("All headlines:");
		// content.getArticles().forEach((article) -> System.out.println(article.getHeader()));
		System.out.println("Headlines with positive sentiment:");
		List<Article> positiveArticles = new SentimentHandler(new Sentiment(), content, new POSTagger())
				.getPositiveArticles();
		positiveArticles.forEach((article) -> System.out.println(article.getHeader()));
	}
}
