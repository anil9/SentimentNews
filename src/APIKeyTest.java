import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class APIKeyTest {

	@Test
	public void testGet() {
		APIKey apiKey = new APIKey("src/APIKeyTest.txt");
		assertEquals("test for apikey", apiKey.get());
	}

}
