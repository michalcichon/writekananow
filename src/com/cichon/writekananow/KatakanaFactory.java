package com.cichon.writekananow;

import java.util.ArrayList;
import java.util.Random;

public class KatakanaFactory {
	
	private Random randomGenerator;
	private ArrayList<String> dictionary;
	
	KatakanaFactory() {
		super();
		
		dictionary = new ArrayList<String>();
		dictionary.add("a");
		dictionary.add("i");
		dictionary.add("u");
		dictionary.add("e");
		dictionary.add("o");
		
		randomGenerator = new Random();
		
	}
	
	public String getRandomElement() {
		int index = randomGenerator.nextInt(dictionary.size());
        return dictionary.get(index);
	}
	
	public String getRandomElementUnlike(String kana) {
		int index = randomGenerator.nextInt(dictionary.size());
		String returnedKana = dictionary.get(index);
		
		while(kana.equals(returnedKana)) {
			index = randomGenerator.nextInt(dictionary.size());
			returnedKana = dictionary.get(index);
		}
		
		return returnedKana;
	}
}
