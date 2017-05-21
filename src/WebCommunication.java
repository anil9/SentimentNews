import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebCommunication {

	public String readSiteResponse(URL url) {
		String response = "";
		try {
			InputStream siteResponseStream = GETRequestResponseStream(url);
			response = inputStreamToString(siteResponseStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private InputStream GETRequestResponseStream(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		return con.getInputStream();
	}

	private String inputStreamToString(InputStream in) {
		try (java.util.Scanner s = new java.util.Scanner(in)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
}
