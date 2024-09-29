package Rolodex;

import java.util.ArrayList;
/*
 * Name: Chris Bernard
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Rolodex {
	private Entry cursor;
	private final Entry[] index;

	// Constructor

	Rolodex() {
		index = new Separator[26];
		index[0] = new Separator(null, null, 'A');
		Separator prev = (Separator)index[0];
		for(int i = 1; i<index.length; i++) {
			char letter = (char)(65+i);
			index[i] = new Separator(prev, null, letter);
			prev.next = index[i];
			prev = (Separator)index[i];
		}
		index[0].prev = index[index.length-1];
		index[0].next = index[1];
		index[index.length-1].next = index[0];
	}

	public Boolean contains(String name) {
	    int ind = (int)(name.charAt(0))-65;
	    Entry curr = index[ind].next;
	    while(!curr.isSeparator()) {
	    	if(curr.getName().equals(name)) return true;
	    	curr = curr.next;
	    }
	    return false;
	}
	
	public int size() {
		int size = 0;
		Entry curr = index[0];
		while(curr!=index[index.length-1]) {
			if(!curr.isSeparator()) size+=1;
			curr = curr.next;
		}
		return size;
	}

	public ArrayList<String> lookup(String name) {
		if(!this.contains(name)) throw new IllegalArgumentException("lookup: name not found");
		ArrayList<String> nums = new ArrayList<String>();
		int ind = (int)(name.charAt(0))-65;
		Card curr = (Card)index[ind].next;
		while(curr.getName()!=name) {
			curr = (Card)curr.next;
		}
		while(curr.getName().equals(name)) {
			nums.add(curr.getCell());
			if(curr.next.isSeparator()) break;
			curr = (Card)curr.next;
		}
		
		return nums;
	}


	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}




	public void addCard(String name, String cell) {
		if(this.contains(name)) {
			if(this.lookup(name).contains(cell)) {
				throw new IllegalArgumentException("addCard: duplicate entry");
			
			}
		}
		int ind = (int)(name.charAt(0))-65;
		
		//if card is the first for its letter
		if(index[ind].next.isSeparator()) {
			Card newCard = new Card(index[ind], index[ind].next, name, cell);
			index[ind].next = newCard;
			index[ind+1].prev = newCard;
			return;
		}
		
		Card curr = (Card)index[ind].next;
		
		while(curr.getName().compareTo(name)<0 && !curr.next.isSeparator()) {
			curr = (Card)curr.next;
		}
		Card newCard = null;
		if(curr.next.isSeparator() && curr.getName().compareTo(name)<0) {
			newCard = new Card(curr, curr.next, name, cell);
			curr.next.prev = newCard;
			curr.next = newCard;
			return;
		}
		if(curr.next.getName().equals(curr.getName())) {
			
			while(curr.getCell().compareTo(cell)>0 && curr.getName()==name) {
				curr = (Card)curr.next;
			}
		}
		
		newCard = new Card(curr.prev, curr, name, cell);
		curr.prev.next = newCard;
		curr.prev = newCard;
		
	}

	public void removeCard(String name, String cell) {
		if(!this.contains(name)) throw new IllegalArgumentException("removeCard: name does not exist");
		if(!this.lookup(name).contains(cell)) throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		int ind = (int)(name.charAt(0))-65;
		Card curr = (Card)index[ind].next;
		while(curr.getName()!=name) {
			curr = (Card)curr.next;
		}
		while(curr.getName()==name) {
			if(curr.getCell()==cell) {
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev;
				return;
			}
			else {
				curr = (Card)curr.next;
			}
		}
	}
	
	public void removeAllCards(String name) {
		if(!this.contains(name)) throw new IllegalArgumentException("removeAllCards: name does not exist");
		ArrayList<String> list = this.lookup(name);
		for(String str: list) {
			this.removeCard(name, str);
		}
	}

	// Cursor operations

	public void initializeCursor() {
		cursor = index[0];
	}

	public void nextSeparator() {
		cursor=cursor.next;
		while(!cursor.isSeparator()) {
			cursor = cursor.next;
		}
	}

	public void nextEntry() {
		cursor = cursor.next;
	}

	public String currentEntryToString() {
		if(cursor.isSeparator()) {
			Separator s = (Separator)cursor;
			return s.toString();
		}
		Card c = (Card)cursor;
		return c.toString();
	}
}
