import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentiment {
	private Map<String, Integer> sentiments;

	public Sentiment() {
		sentiments = new HashMap<String, Integer>();
		fillSentimentMap();
	}

	public Sentiment(Map<String, Integer> sentiments) {
		this.sentiments = sentiments;
	}

	private void fillSentimentMap() {
		sentiments.put("cute", 1);
		// TODO: fill map from stored file
	}

	/**
	 * Outputs the amount of words that are determined to be of positive sentiment.
	 * 
	 * @param words
	 *            words tested for positivity
	 * @return Amount of positive words
	 */
	public int calcNumPositive(List<String> words) {
		int positives = 0;
		for (String word : words) {
			if (sentiments.containsKey(word) && sentiments.get(word) > 0) {
				positives++;
			} else if (!sentiments.containsKey(word)) {
				// TODO: use FeedbackHandler to ask for user input
			}
		}
		return positives;
	}
}
