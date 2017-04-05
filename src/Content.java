import java.util.ArrayList;
import java.util.List;

public class Content {

	private List<Article> articles;

	public Content() {
		articles = new ArrayList<Article>();
		articles.add(new Article("This is a cute example", "www.anything.com"));
	}

}
