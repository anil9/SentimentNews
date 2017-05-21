import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class SentimentTest {

	Sentiment sentiment;

	@Before
	public void setup() {
		HashMap<String, Integer> sentiments = new HashMap<String, Integer>();
		sentiments.put("pos1", 1);
		sentiments.put("Pos2", 1);
		sentiments.put("neutral", 0);
		sentiments.put("neg1", -1);
		sentiment = new Sentiment(sentiments);
	}

	@Test
	public void testCalcNumPositive() {

		assertEquals(2, sentiment.calcNumPositive(Arrays.asList("Pos1", "pos2", "neutral", "null")));
		assertEquals(0, sentiment.calcNumPositive(Arrays.asList("")));
		assertEquals(0, sentiment.calcNumPositive(Arrays.asList("neutral", "null", "neg1")));
	}

	@Test
	public void testCalcPositivePercentage() {
		assertEquals(1, sentiment.calcPositivePercentage(Arrays.asList("Pos1", "pos2", "neutral", "null")), 0.01);
		assertEquals(0, sentiment.calcPositivePercentage(Arrays.asList("")), 0.01);
		assertEquals(0, sentiment.calcPositivePercentage(Arrays.asList("neg1")), 0.01);
		assertEquals(0.66, sentiment.calcPositivePercentage(Arrays.asList("Pos1", "pos2", "neutral", "neg1")), 0.01);
		assertEquals(0.5, sentiment.calcPositivePercentage(Arrays.asList("Pos1", "neg1")), 0.01);

	}

}