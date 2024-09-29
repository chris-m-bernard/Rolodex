package Rolodex;

/*
 * Name: Chris Bernard
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */
public abstract class Entry {
	protected Entry prev;
	protected Entry next;
	
	public Entry(Entry prev, Entry next) {
		super();
		this.prev = prev;
		this.next = next;
	}
	
	public abstract Boolean isSeparator();
	
	public abstract int size();
	
	public abstract String getName();
	
}
