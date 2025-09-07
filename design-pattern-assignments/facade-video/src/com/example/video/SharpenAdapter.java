package com.example.video;

import java.util.Arrays;

public class SharpenAdapter implements ISharpenFilter {
    private final LegacySharpen legacySharpen;

    public SharpenAdapter(LegacySharpen legacySharpen) {
        this.legacySharpen = legacySharpen;
    }

    @Override
    public Frame[] sharpen(Frame[] frames, int strength) {
        String handle = framesToHandle(frames);
        String newHandle = legacySharpen.applySharpen(handle, strength);
        return handleToFrames(newHandle, frames);
    }

    private String framesToHandle(Frame[] frames) {
        return "HANDLE:" + frames.length;
    }

    private Frame[] handleToFrames(String handle, Frame[] original) {
        return Arrays.copyOf(original, original.length);
    }
}
