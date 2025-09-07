package com.example.render;

/*
 * Renderer creates Glyphs and sums drawing cost
 * Uses a style factory to get shared TextStyle instances
 */
public class Renderer {
    private final ITextStyleFactory styleFactory;

    public Renderer(ITextStyleFactory styleFactory) {
        this.styleFactory = styleFactory;
    }

    public int render(String text) {
        int cost = 0;
        for (char c : text.toCharArray()) {
            // decide style per char (example: bold every 7th char)
            boolean bold = (c % 7 == 0);
            TextStyle style = styleFactory.getTextStyle("Inter", 14, bold);

            Glyph g = new Glyph(c, style); // reuse shared TextStyle
            cost += g.drawCost();
        }
        return cost;
    }
}