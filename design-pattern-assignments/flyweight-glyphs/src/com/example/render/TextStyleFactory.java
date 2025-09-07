package com.example.render;

import java.util.HashMap;
import java.util.Map;

/*
 * Concrete factory that caches TextStyle objects (flyweights).
 * Returns shared immutable TextStyle instances
 */
public class TextStyleFactory implements ITextStyleFactory {
    private final Map<String, TextStyle> cache = new HashMap<>();

    @Override
    public TextStyle getTextStyle(String font, int size, boolean bold) {
        String key = font + "|" + size + "|" + (bold ? "B" : "N");
        
        if (!cache.containsKey(key)) {
            cache.put(key, new TextStyle(font, size, bold));
        }
        
        return cache.get(key);
    }
}