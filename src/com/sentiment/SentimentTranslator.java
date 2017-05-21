package com.sentiment;

import java.io.IOException;

public class SentimentTranslator {

	public SentimentEnum translateSentiment(String sentiment) throws IOException {
		sentiment = sentiment.toLowerCase();
		switch (sentiment) {
		case "neg":
			return SentimentEnum.NEG;
		case "pos":
			return SentimentEnum.POS;
		case "neu":
			return SentimentEnum.NEU;
		case "both":
			return SentimentEnum.NEU;
		default:
			throw new IOException("Error in sentiment file. Couldn't translate sentiment: " + sentiment);
		}
	}
}
