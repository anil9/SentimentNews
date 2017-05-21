package com.sentiment;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sentiment.POSTagger;

public class POSTaggerTest {

	private POSTagger tagger = new POSTagger();

	@Test
	public void testExtractAdjectives() {
		String input = "Although the cat was cute, it was sad that it couldn't run quick";
		List<String> correct = Arrays.asList("cute", "sad", "quick");

		assertEquals(correct, tagger.extractAdjectives(input));
	}
}
