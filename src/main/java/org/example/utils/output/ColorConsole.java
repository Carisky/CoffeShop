package org.example.utils.output;

public class ColorConsole {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void red(String line) {
        System.out.println(ANSI_RED + line + ANSI_RESET);
    }

    public static void green(String line) {
        System.out.println(ANSI_GREEN + line + ANSI_RESET);
    }

    public static void yellow(String line) {
        System.out.println(ANSI_YELLOW + line + ANSI_RESET);
    }

    public static void blue(String line) {
        System.out.println(ANSI_BLUE + line + ANSI_RESET);
    }

    public static void purple(String line) {
        System.out.println(ANSI_PURPLE + line + ANSI_RESET);
    }

    public static void cyan(String line) {
        System.out.println(ANSI_CYAN + line + ANSI_RESET);
    }

    public static void white(String line) {
        System.out.println(ANSI_WHITE + line + ANSI_RESET);
    }
}
