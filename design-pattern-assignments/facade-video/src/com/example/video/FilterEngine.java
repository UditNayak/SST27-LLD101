package com.example.video;

public class FilterEngine {
    // Apply grayscale to frames. This is a no operation placeholder in the example.
    // In a real implementation, this would modify pixel data.
    public Frame[] grayscale(Frame[] frames){
        return frames; // pretend work
    }

    // Scale frames by a factor. This is a no-op placeholder in the example.
    // In a real implementation, this would resize the frame dimensions.
    public Frame[] scale(Frame[] frames, double factor){
        return frames; // pretend work
    }
}
