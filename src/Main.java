import java.io.File;
import java.util.List;

public class Main {
	public static final String API_KEY_PATH = "C:/SentimentNews/key.txt";
	public static final String SENTIMENTS_FILE_PATH = "res/sentiments.txt";

	public static void main(String[] args) {
		// Sample setup

		double positiveThreshold = 0.8;
		NewsAPICommunication content = new NewsAPICommunication(new WebCommunication());
		URLFactory urlFactory = new URLFactory();
		List<Article> articles = content.getArticlesFromSite(urlFactory.newsAPIURL("google-news"));

		Sentiment sentiment = new Sentiment();
		sentiment.fillSentimentMapFromFile(new File(SENTIMENTS_FILE_PATH));
		SentimentHandler sentimentHandler = new SentimentHandler(sentiment, new POSTagger());
		sentimentHandler.setPositiveThreshold(positiveThreshold);

		System.out.println("Headlines with positive sentiment:");
		List<Article> positiveArticles = sentimentHandler.getPositiveArticles(articles);
		displayResult(positiveArticles);

		positiveArticles = sentimentHandler.getPositiveArticlesConsideringAdjectives(articles);
		displayResult(positiveArticles);

	}

	private static void displayResult(List<Article> articles) {
		articles.forEach((article) -> System.out.println(article.getHeader()));
		articles.forEach((article) -> WebsiteOpener.open(article.getUrl()));
	}

}
