package byog.lab5;

public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position p) {
        new Position(p.x, p.y);
    }
}
