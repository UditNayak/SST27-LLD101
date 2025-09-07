package com.example.render;

/*
 * Abstraction for a style factory.
 * Allows different implementations (e.g., caching, non-caching, remote lookup).
 */
public interface ITextStyleFactory {
    TextStyle getTextStyle(String font, int size, boolean bold);
}