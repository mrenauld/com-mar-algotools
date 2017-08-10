package com.mar.algotools.sorting;

public interface MyComparator {
	
	/**
	 * Returns
	 *   -1 if a < b
	 *   0  if a = b
	 *   1  if a > b
	 * 
	 * @param a the first object.
	 * @param b the second object.
	 * @return a comparison between the two objects.
	 */
	public int compareTo( Object a, Object b );
	
}
