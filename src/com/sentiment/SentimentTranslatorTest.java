package com.sentiment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class SentimentTranslatorTest {

	@Test
	public void testTranslateSentiment() {
		SentimentTranslator t = new SentimentTranslator();
		try {
			assertEquals(SentimentEnum.POS, t.translateSentiment("pos"));
			assertEquals(SentimentEnum.NEG, t.translateSentiment("neg"));
			assertEquals(SentimentEnum.NEU, t.translateSentiment("neu"));
			assertEquals(SentimentEnum.NEU, t.translateSentiment("both"));
		} catch (IOException e) {
			fail("should not throw exception on these inputs");
			e.printStackTrace();
		}
		try {
			t.translateSentiment(".-.-dsfkmklg");
			fail("should have thrown exception");
		} catch (IOException e) {
			assertTrue(e.getMessage().contains("Couldn't translate sentiment"));
		}

	}

}
