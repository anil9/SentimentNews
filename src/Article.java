import java.net.URL;
import java.util.List;

public class Article {

	private String header;
	private URL url;
	private List<String> adjectives;

	private Article() {
	}

	public Article(String header, URL url) {
		this.header = header;
		this.url = url;
	}

	public String getHeader() {
		return header;
	}

	public URL getUrl() {
		return url;
	}

	public void setAdjectives(List<String> adjectives) {
		this.adjectives = adjectives;
	}

	public List<String> getAdjectives() {
		return adjectives;
	}
}
