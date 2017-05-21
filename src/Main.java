import java.util.List;

public class Main {
	public static final String API_KEY_PATH = "C:/SentimentNews/key.txt";

	public static void main(String[] args) {
		// Sample setup

		double positiveThreshold = 0.5;
		NewsAPICommunication content = new NewsAPICommunication(new WebCommunication());
		URLFactory urlFactory = new URLFactory();
		List<Article> articles = content.getArticlesFromSite(urlFactory.constructNewsAPISite("google-news"));
		// System.out.println("All headlines:");
		// content.getArticles().forEach((article) -> System.out.println(article.getHeader()));
		System.out.println("Headlines with positive sentiment:");
		List<Article> positiveArticles = new SentimentHandler(new Sentiment(), new POSTagger())
				.getPositiveArticles(articles, positiveThreshold);
		positiveArticles.forEach((article) -> System.out.println(article.getHeader()));
		positiveArticles.forEach((article) -> WebsiteOpener.open(article.getUrl()));

	}

}
