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
		
		dictionary.add("ka");
		dictionary.add("ki");
		dictionary.add("ku");
		dictionary.add("ke");
		dictionary.add("ko");
		
		dictionary.add("sa");
		dictionary.add("shi");
		dictionary.add("su");
		dictionary.add("se");
		dictionary.add("so");
		
		dictionary.add("ta");
		dictionary.add("chi");
		dictionary.add("tsu");
		dictionary.add("te");
		dictionary.add("to");
		
		dictionary.add("na");
		dictionary.add("ni");
		dictionary.add("nu");
		dictionary.add("ne");
		dictionary.add("no");
		
		dictionary.add("ha");
		dictionary.add("hi");
		dictionary.add("fu");
		dictionary.add("he");
		dictionary.add("ho");
		
		dictionary.add("ma");
		dictionary.add("mi");
		dictionary.add("mu");
		dictionary.add("me");
		dictionary.add("mo");
		
		dictionary.add("ya");
		dictionary.add("yu");
		dictionary.add("yo");
		
		dictionary.add("ra");
		dictionary.add("ri");
		dictionary.add("ru");
		dictionary.add("re");
		dictionary.add("ro");
		
		dictionary.add("wa");
		dictionary.add("wi");
		dictionary.add("we");
		dictionary.add("wo");
		
		dictionary.add("n");
		
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
