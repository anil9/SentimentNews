import java.util.List;

public class Article {

	private String header, url;
	private List<String> adjectives;

	public Article(String header, String url) {
		this.header = header;
		this.url = url;
	}

	public String getHeader() {
		return header;
	}

	public String getUrl() {
		return url;
	}

	public void setAdjectives(List<String> adjectives) {
		this.adjectives = adjectives;
	}

	public List<String> getAdjectives() {
		return adjectives;
	}
}
