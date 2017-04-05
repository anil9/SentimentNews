import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTagger {
	private final String MATCH_UNWANTED_CHARS = "[^a-zA-Z\\-\\' ]";
	private String posModel = "lib/stanford-postagger-2016-10-31/models/english-left3words-distsim.tagger";
	private MaxentTagger tagger;

	public POSTagger() {
		tagger = new MaxentTagger(posModel);

	}

	/**
	 * Extracts all the adjectives from the given sentence.
	 * 
	 * @param sentence
	 *            The sentence to extract from.
	 * @return Returns the adjectives from the sentence.
	 */
	public List<String> extractAdjectives(String sentence) {
		List<String> returnList = new ArrayList<String>();
		sentence = sentence.replaceAll(MATCH_UNWANTED_CHARS, "");
		List<HasWord> words = SentenceUtils.toWordList(sentence.split(" "));
		List<TaggedWord> taggedSentence = tagger.tagSentence(words);

		for (TaggedWord tw : taggedSentence) {

			if (tw.tag().startsWith("JJ")) { // adjective
				returnList.add(tw.word());
			}
		}
		return returnList;
	}
}
