package com.example.video;
import java.nio.file.Path;

public class Decoder {
    /*
     * Decode the source file into an array of frames.
     * In this toy example we return two dummy frames with fixed dimensions.
     * In a real implementation, this would read and decode the video file.
     * Each frame is represented as a Frame object with width and height.
     *
     * @param src The path to the source video file.
     * @return An array of decoded Frame objects.
     */
    public Frame[] decode(Path src) { 
        return new Frame[] { new Frame(1920,1080), new Frame(1920,1080) }; 
    }
}
