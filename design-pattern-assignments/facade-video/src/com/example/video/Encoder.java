package com.example.video;
import java.nio.file.Path;

public class Encoder {
    /*
     * Encode an array of frames into a video file at the specified output path.
     * In this toy example we simply return the output path without actual encoding.
     * In a real implementation, this would take the frames and encode them into a video format.
     *
     * @param frames The array of Frame objects to encode.
     * @param out The path where the encoded video file should be saved.
     * @return The path to the encoded video file.
     */
    public Path encode(Frame[] frames, Path out) { return out; }
}
