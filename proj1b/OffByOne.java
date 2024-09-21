import static java.lang.Math.abs;

public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char a, char b) {
        int diff = abs(a - b);
        return diff == 1;
    }
}
