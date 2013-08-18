package com.cichon.writekananow;

import java.util.ArrayList;
import java.util.Random;

public class KatakanaFactory {
	
	private Random randomGenerator;
	private ArrayList<Tuple> dictionary;
	
	KatakanaFactory() {
		super();
		
		dictionary = new ArrayList<Tuple>();
		dictionary.add(new Tuple("a","a.png"));
		dictionary.add(new Tuple("i","i.png"));
		dictionary.add(new Tuple("u","u.png"));
		dictionary.add(new Tuple("e","e.png"));
		dictionary.add(new Tuple("o","o.png"));
		
		randomGenerator = new Random();
		
	}
	
	public Tuple getRandomElement() {
		int index = randomGenerator.nextInt(dictionary.size());
        return dictionary.get(index);
	}
	
	public Tuple getRandomElementUnlike(Tuple tuple) {
		int index = randomGenerator.nextInt(dictionary.size());
		Tuple returnedTuple = dictionary.get(index);
		
		while(tuple.equals(returnedTuple)) {
			index = randomGenerator.nextInt(dictionary.size());
			returnedTuple = dictionary.get(index);
		}
		
		return returnedTuple;
	}
}
