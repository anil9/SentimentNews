import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentiment {
	private Map<String, SentimentEnum> sentiments;
	private String filepath = "res/sentiments.txt";
	private SentimentTranslator translator = new SentimentTranslator();

	public Sentiment() {
		sentiments = new HashMap<String, SentimentEnum>();
		fillSentimentMap();
	}

	public Sentiment(Map<String, SentimentEnum> inputSentiments) {
		this.sentiments = new HashMap<String, SentimentEnum>();
		inputSentiments.forEach((k, v) -> this.sentiments.put(k.toLowerCase(), v));
	}

	private void fillSentimentMap() {
		// fill map from stored file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			String line = "";
			reader.readLine(); // skip first line
			while ((line = reader.readLine()) != null) {
				String values[] = line.trim().split("\\s+");
				if (values.length == 2) {
					sentiments.put(values[0], translator.translateSentiment(values[1]));
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			word = word.toLowerCase();
			if (sentiments.containsKey(word) && sentiments.get(word).equals(SentimentEnum.POS)) {
				positives++;
			} else if (!sentiments.containsKey(word)) {
				// TODO: use FeedbackHandler to ask for user input
			}
		}
		return positives;
	}

	public double calcPositivePercentage(List<String> words) {
		int positives = 0;
		int negatives = 0;
		int neutrals = 0;
		for (String word : words) {
			String lowerCaseWord = word.toLowerCase();
			if (sentiments.containsKey(lowerCaseWord)) {
				SentimentEnum sentiment = sentiments.get(lowerCaseWord);
				if (sentiment == SentimentEnum.POS) {
					positives++;
				} else if (sentiment == SentimentEnum.NEG) {
					negatives++;
				} else {
					neutrals++;
				}
			} else if (!sentiments.containsKey(lowerCaseWord)) {
				// TODO: use FeedbackHandler to ask for user input
				// if (Character.isUpperCase(word.charAt(0)) || word.length() < 3) {
				// // probably a name, skip.
				// } else {
				// System.out.println("Don't recognize the word [" + word + "] Please provide feedback");
				// }
			}
		}
		if (positives + negatives == 0)
			return 0;
		return positives / (double) (positives + negatives);
	}
}
