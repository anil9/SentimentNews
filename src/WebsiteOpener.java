import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class WebsiteOpener {

	public static void open(URL url) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse((url.toURI()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
