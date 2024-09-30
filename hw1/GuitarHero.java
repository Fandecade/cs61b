import synthesizer.GuitarString;

public class GuitarHero {

    private static final double CONCERT = 440.0;

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] hero = new GuitarString[37];

        for(int i = 0; i < 37; i++) {
            double frequent = CONCERT * Math.pow(2, (i + 1 - 24) / 12.0);
            hero[i] = new GuitarString(frequent);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);  //获取key对应索引（没有则返回-1）
                if (index < 0) {
                    System.out.println("please enter vaild key:" + keyboard);
                    continue;
                }
                hero[index].pluck();
            }

            double sample = 0;
            for(GuitarString gs : hero) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString gs : hero) {
                gs.tic();
            }
        }

    }
}
