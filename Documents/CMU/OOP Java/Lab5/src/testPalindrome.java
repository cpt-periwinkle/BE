import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class testPalindrome {

	Palindromer pd = new Palindromer();
	
	@Test
	void testCleanString1() {
		assertEquals("DOG", pd.cleanString("dog"));
	}

	@Test
	void testCleanString2() {
		assertEquals("NOWISTHETIME", pd.cleanString("Now is the time"));
	}

	@Test
	void testCleanString3() {
		assertEquals("", pd.cleanString("??"));
	}

	@Test
	void testIsPalindrome1() {
		assertTrue( pd.isPalindrome("eve"));
	}

	@Test
	void testIsPalindrome2() {
		assertTrue( pd.isPalindrome("madamimadam"));
	}

	@Test
	void testIsPalindrome3() {
		assertTrue(!pd.isPalindrome("dog"));
	}

	@Test
	void testIsPalindrome4() {
		assertTrue(pd.isPalindrome(pd.cleanString("Eve")));
	}

	@Test
	void testIsPalindrome5() {
		assertTrue(!pd.isPalindrome(pd.cleanString("Eve Adam")));
	}

	@Test
	void testisPalindrome6() {
		assertTrue(pd.isPalindrome(pd.cleanString("Madam, I'm Adam")));
	}

	@Test
	void testGetPcount() {
		assertEquals(0, pd.getPcount());
	}


}
