package com.example.game.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class GameOutputStream {

    private final PrintStream out;
    private final int width;

    public GameOutputStream(OutputStream out, int width) {
        this.out = new PrintStream(out);
        this.width = width;
    }

    public void print(String str) {

        int currentWidth = 0;
        StringTokenizer tokenizer = new StringTokenizer(str);

        while (tokenizer.hasMoreTokens()) {

            String token = tokenizer.nextToken();

            if (currentWidth + token.length() >= width) {
                out.println();
                currentWidth = 0;
            }

            out.print(token + " ");
            currentWidth += token.length() + 1;
        }
        out.flush();
    }

    public void println(String str) {
        print(str);
        out.println();
    }

    public void println() {
        out.println();
    }
}
