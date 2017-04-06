import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Sample setup

		double positiveThreshold = 0.5;
		Content content = new Content("reddit-r-all");
		// System.out.println("All headlines:");
		// content.getArticles().forEach((article) -> System.out.println(article.getHeader()));
		System.out.println("Headlines with positive sentiment:");
		List<Article> positiveArticles = new SentimentHandler(new Sentiment(), content, new POSTagger())
				.getPositiveArticles(positiveThreshold);
		positiveArticles.forEach((article) -> System.out.println(article.getHeader()));
		positiveArticles.forEach((article) -> WebsiteOpener.open(article.getUrl()));

	}
}
