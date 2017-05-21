import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class WebCommunicationTest {

	@Test
	public void testReadSiteResponse() throws MalformedURLException {
		WebCommunication comm = new WebCommunication();
		String response = comm.readSiteResponse(new URL("http://httpbin.org/get"));
		assertTrue(response.contains("args"));
		assertTrue(response.contains("\"Host\": \"httpbin.org\","));
	}

}
