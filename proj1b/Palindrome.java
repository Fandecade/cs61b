public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int size = word.length();
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < size; i++) {
            deque.addLast(word.charAt(i));      //charAt(index)
        }
        return deque;
    }

    public static boolean isPalindrome(String word) {
        if(word.length() == 0 || word.length() == 1) {
            return true;
        }
        int size = word.length();
        for(int i = 0; i < size / 2; i++) {
            if(word.charAt(i) != word.charAt(size - 1 -i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if(word.length() == 0 || word.length() == 1) {
            return true;
        }
        int size = word.length();
        for(int i = 0; i < size / 2; i++) {
            if(!cc.equalChars(word.charAt(i), word.charAt(size - 1 -i))) {
                return false;
            }
        }
        return true;
    }

}
