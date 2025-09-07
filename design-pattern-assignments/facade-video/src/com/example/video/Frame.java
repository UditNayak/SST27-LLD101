package com.example.video;

// Simple data holder for a single video frame.
// Fields are final to indicate immutability of dimensions.
public class Frame {
    public final int w, h;

    public Frame(int w, int h) {
        this.w = w;
        this.h = h;
    }
}