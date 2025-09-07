package com.example.render;

/*
 * Represents a character in text
 * Holds extrinsic state (char) and intrinsic shared state (TextStyle)
 */
public class Glyph {
    private final char ch;         // extrinsic: unique per glyph
    private final TextStyle style; // intrinsic: shared

    public Glyph(char ch, TextStyle style) {
        this.ch = ch;
        this.style = style;
    }

    public char getCh() { return ch; }

    public int drawCost() { return style.drawCost(); }
}