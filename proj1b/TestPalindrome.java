import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("asgdfgdfga"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testisPalindrome2() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("rq", obo));
        assertFalse(palindrome.isPalindrome("ae", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
    }

    @Test
    public void testisPalindrome3() {
        OffByN obo = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", obo));
        assertTrue(palindrome.isPalindrome("fa", obo));
        assertFalse(palindrome.isPalindrome("fh", obo));
    }
}
