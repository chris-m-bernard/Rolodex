package Rolodex;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
/*
 * Name: Chris Bernard
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */
class RolodexTest {

	@Test
	void test1() {
		Rolodex r = new Rolodex();
		r.addCard("Abby", "123");
		r.addCard("Ben", "456");
		r.addCard("Ben", "123");
		r.addCard("Ben", "789");
		r.addCard("Adam", "111");
		
		assertEquals(true,r.contains("Abby"));
		r.removeCard("Ben","456");
		assertEquals(true,r.contains("Ben"));
		
		r.removeAllCards("Ben");
		assertEquals(false,r.contains("Ben"));
		
		assertEquals(2,r.size());
		ArrayList<String> l = new ArrayList<String>();
		l.add("123");
		assertEquals(l,r.lookup("Abby"));
		
	}
	@Test
	void test2() {
		Rolodex r = new Rolodex();
		
		r.addCard("Denny", "156");
		r.addCard("Desmond", "263");
		r.addCard("Alex", "221");
		r.addCard("Bob", "924");
		r.addCard("Charlie", "518");
		r.initializeCursor();
		assertEquals("Separator A",r.currentEntryToString());
		r.nextEntry();
		assertEquals("Name: Alex, Cell: 221",r.currentEntryToString());
		r.nextEntry();
		assertEquals("Separator B",r.currentEntryToString());
		r.nextSeparator();
		assertEquals("Separator C",r.currentEntryToString());
		r.nextEntry();
		assertEquals("Name: Charlie, Cell: 518",r.currentEntryToString());
		
	}
}
