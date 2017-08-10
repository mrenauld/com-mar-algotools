package com.mar.algotools.sorting;

public class StringComparator implements MyComparator {
	/**
	 * Compare the Strings a and b. Returns -1 if a is first in lexicographic
	 * order, 1 if b is first in lexicographic order or 0 if a is equal to b.
	 * 
	 * @param a an integer array.
	 * @param b an integer array.
	 * @return -1 if a < b, 0 if a = b and 1 if b < a.
	 */
	public int compareTo( Object ao, Object bo ) {
		String a = (String) (ao);
		String b = (String) (bo);
		
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		for( int i = 0; i < Math.max(a.length(), b.length()); ++i ) {
			if( i >= a.length() || i >= b.length() )
				break;
			if( a.charAt(i) < b.charAt(i) )
				return -1;
			if( a.charAt(i) > b.charAt(i) )
				return 1;
		}
		
		if( a.length() < b.length() )
			return -1;
		if( a.length() > b.length() )
			return 1;
		
		return 0;
	}
}