package com.example.render;

/*
 * Intrinsic state for a glyph (immutable)
 * Shared among multiple Glyphs with same style
 * 
 * Immutable Class:
 * - private final fields
 * - no setters
 * - Constructor injeection (with deep copy / defensive copy if field is mutable)
 * - return deep copy for getters (if field is mutable)
 * - mark class as final to prevent subclassing
 */
public final class TextStyle {
    private final String font;
    private final int size;
    private final boolean bold;

    public TextStyle(String font, int size, boolean bold) {
        this.font = font;
        this.size = size;
        this.bold = bold;
    }

    // getters (no setters)
    public String getFont() { return font; }
    public int getSize() { return size; }
    public boolean isBold() { return bold; }

    // cost for drawing (style-dependent)
    public int drawCost() { return size + (bold ? 10 : 0); }
}