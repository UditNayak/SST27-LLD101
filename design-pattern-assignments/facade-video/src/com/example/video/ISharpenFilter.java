package com.example.video;

public interface ISharpenFilter {
    Frame[] sharpen(Frame[] frames, int strength);
}


// If later you want to replace LegacySharpen with another library, you just write a new adapter implementing SharpenFilter.