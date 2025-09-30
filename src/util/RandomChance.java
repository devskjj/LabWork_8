package util;

import java.util.Random;

public class RandomChance {
    private static final Random rnd = new Random();
    public static boolean getRandom(int num) {
        int value = rnd.nextInt(100) + 1;
        return (value <= num);
    }
}
