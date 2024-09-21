import static java.lang.Math.abs;

public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        int diff = abs(a - b);
        return diff == N;
    }
}
