package me.cooperzilla.trimssmp.utils;

public class NumUtils {
    public static int getNum(int num) {
        if (num-1 > 0) {
            return (num-1) * 10 + 1;
        } else {
            return 1;
        }
    }

    public static long seconds(int num) {
        return num * 20L;
    }

    public static long minutes(int num) {
        return (long) num * 60 * 20;
    }
}
