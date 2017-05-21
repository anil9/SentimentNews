
public class SentimentTranslator {

	public SentimentEnum translateSentiment(String sentiment) {
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
			System.err.println("Error in sentiment file. Couldn't translate sentiment: " + sentiment);
			return null;
		}
	}
}
