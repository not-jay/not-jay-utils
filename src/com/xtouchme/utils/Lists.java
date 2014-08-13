package com.xtouchme.utils;

import java.util.List;

public class Lists {

	public enum DiffMode { NOT_IN_A, BOTH };
	
	/**
	 * Compares two lists and returns a new list containing elements not found in the other list<br />
	 * Overwrites diff's previous values
	 * @param a List A
	 * @param b List B
	 * @param diff List that would contain the diff
	 * @param mode NOT_IN_A,	would only return values not found in a<br />
	 * 			   BOTH,		returns values not found in the other list
	 * @return diff,				if both lists are empty or null<br />
	 * 		   A cloned to diff,	if b is empty or null<br />
	 * 		   B cloned to diff,	if a is empty or null<br />
	 * 		   diff containing both A and B's unique elements (LinkedList), otherwise
	 */
	public static <L extends List<T>, T> L diff(L a, L b, L diff, DiffMode mode) {
		if((a == null) && (b == null) || (a.isEmpty() && b.isEmpty())) return diff;
		if(a == null || a.isEmpty()) { diff = cloneValues(b, diff); return b; }
		if(b == null || b.isEmpty()) { diff = cloneValues(a, diff); return a; }
		
		if(diff == null) diff = (a != null) ? cloneValues(a, diff) : cloneValues(b, diff);
		diff.clear();
		
		for(T element : a) {
			if(!b.contains(element)) diff.add(element);
		}
		for(T element : b) {
			if(!a.contains(element)) diff.add(element);
		}
		
		return diff;
	}
	
	/**
	 * Performs a shallow copy of a list effectively replacing destination's list elements<br />
	 * Both lists should not be null
	 * @param source List containing values to be cloned
	 * @param destination List that would contain the values
	 * @return destination
	 */
	public static <L extends List<T>, T> L cloneValues(L source, L destination) {
		if(source == null || destination == null) return null;
		
		destination.clear();
		for(int i = 0; i < source.size(); i++) {
			destination.add(source.get(i));
		}
		
		return destination;
	}
}
