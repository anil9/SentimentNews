import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class TestSentiment {

	@Test
	public void testCalcNumPositive() {
		HashMap<String, Integer> sentiments = new HashMap<String, Integer>();
		sentiments.put("pos1", 1);
		sentiments.put("Pos2", 1);
		sentiments.put("neutral", 0);
		sentiments.put("neg1", -1);
		Sentiment sentiment = new Sentiment(sentiments);

		assertEquals(2, sentiment.calcNumPositive(Arrays.asList("Pos1", "pos2", "neutral", "null")));
		assertEquals(0, sentiment.calcNumPositive(Arrays.asList("")));
		assertEquals(0, sentiment.calcNumPositive(Arrays.asList("neutral", "null", "neg1")));
	}
}
