package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * 特点：
 * 1. 上半部空格规则（第一行空 side_length - 1 格子，之后每次少空一格，直到不空格（表示到达最长地方，即上半部分））
 * 2. 上半部分每次输出个数（开始side_length  每次+2,直到 不空格）
 * 3.下半部分,开始不空格，直到空side_lengh - 1格，表示到达最底部
 * 4. 下半部分每次输出个数（开始cal_length(side_length)每次减少2个，直到空side_length - 1 格子）
 *
 * 对于Position p 而言（hexagonal的左下角）
 * 1. hexagonal 由上半部和下半部对称组成（通过计算位置，同时绘制上下部分）
 * 2. p位置对应的上半部位置为 q（p.x ,p.y - side_length * 2 - 1）
 * 3. 从p位置开始绘制，绘制长度开始side_length 每次+2（q位置同理）
 * 4. 更新p和q位置，p位置为(p.x - 1, p.y - 1),q位置为(p.x -1 , p.y + 1)
 * 5. 判断绘画结束 当q.y - p.y == 1 时，表示绘画最长部分，完成后即退出
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /*calculate the upper position q of hexagon*/
    private static Position cal_position(Position p, int side_length) {
        return new Position(p.x, p.y - side_length * 2 + 1 );
    }

    //**update the position p and q*/
    private static void update_position(Position p, Position q) {
        p.x -= 1;
        p.y -= 1;
        q.x -= 1;
        q.y += 1;
    }

    /**draw one line*/
    private static void draw_line(TETile[][] world, Position p, int side_length, TETile t) {
        int x = p.x;
        for(int i = 0; i < side_length; i++) {
            world[x][p.y] = t;
            x += 1;
        }
    }

    /**init the world*/
    public static void init_world(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**Random tetile*/
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return  new TETile('▲', Color.orange, Color.white, "horse");
            case 3: return new TETile('@',Color.green, Color.red, "triangle");
            case 4: return new TETile('█',Color.blue, Color.yellow, "ox");
            default: return Tileset.FLOOR;
        }
    }

    /**find the position of topneighbor*/
    private static  Position find_top(Position p) {
        return new Position(p.x, p.y - 6);
    }

    /**find Symmetrical position*/
    private static Position find_sym_p(Position p, int num) {
        if(num == 3){
            return new Position(p.x + 20, p.y);
        } else if(num == 4) {
            return new Position(p.x + 10, p.y);
        }
        return p;
    }

    /**find the position of Next column*/
    private static Position find_right(Position p) {
        return new Position(p.x + 5, p.y + 3);
    }

    /**draw the hexagon*/
    public static  void addHxagon(TETile[][] world, Position p, int s, TETile t) {
        Position draw = new Position(p.x, p.y);
        Position q = cal_position(draw, s);
        while(draw.y - q.y >= 1) {
            draw_line(world, q, s, t);
            draw_line(world, draw, s, t);
            HexWorld.update_position(draw, q);
            s += 2;
        }
    }

    /**draw random num verticalhexes*/
    public static void drawRandowVertialHexes(TETile[][] world, Position p, int s, TETile t, int num) {
        for(int i = 0; i < num; i++) {
            Position q = find_sym_p(p, num);    //定位对称

            addHxagon(world, p, s, randomTile());
            addHxagon(world, q, s, randomTile());

            p = find_top(p);      //定位上一个(接下来要画的)
            q = find_top(q);

        }

    }

    /**drawing a tesslation of hexagons*/
    public static void tesslation_hex(TETile[][] world, Position p, int s, TETile t) {
        for(int i = 3; i < 6; i++) {
            drawRandowVertialHexes(world, p, s, t, i);
            p = find_right(p);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        Position p = new Position(7,25);
        int side_length = 3;

        init_world(world);





        tesslation_hex(world, p, side_length, randomTile());
        ter.renderFrame(world);
    }
}
