package com.sentiment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentiment {
	private Map<String, SentimentEnum> sentiments;
	private SentimentTranslator translator = new SentimentTranslator();

	public Sentiment() {
		sentiments = new HashMap<String, SentimentEnum>();
	}

	public Sentiment(Map<String, SentimentEnum> inputSentiments) {
		this.sentiments = mutateAllKeysToLowerCase(inputSentiments);

	}

	private Map<String, SentimentEnum> mutateAllKeysToLowerCase(Map<String, SentimentEnum> inputMap) {
		Map<String, SentimentEnum> sentimentMap = new HashMap<>();
		inputMap.forEach((k, v) -> sentimentMap.put(k.toLowerCase(), v));
		return sentimentMap;
	}

	public void fillSentimentMapFromFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
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
			e.printStackTrace();
		}
		sentiments = mutateAllKeysToLowerCase(sentiments);
	}

	public double percentPositiveWords(List<String> words) {
		int positives = 0;
		int negatives = 0;
		for (String word : words) {
			if (sentiments.containsKey(word)) {
				SentimentEnum sentiment = sentiments.get(word);
				if (sentiment == SentimentEnum.POS) {
					positives++;
				} else if (sentiment == SentimentEnum.NEG) {
					negatives++;
				} else {
					// neutrals
				}
			} else if (!sentiments.containsKey(word)) {
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
