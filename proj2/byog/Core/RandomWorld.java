package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class RandomWorld {

    private static final int WIDTH = Game.WIDTH;
    private static final int HEIGHT = Game.HEIGHT;
    private static final long SEED = Long.MAX_VALUE;
    private static final Random RANDOM = new Random(SEED);

    /**初始化屏幕*/
    public static void init_screen(TETile[][] world) {
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }




}
