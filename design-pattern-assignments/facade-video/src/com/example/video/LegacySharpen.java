package com.example.video;

/*
 * Legacy sharpening API with an odd design:
 * - Works with "handles" (strings) instead of real frame objects.
 * - Returns a new handle after applying sharpen.
 * 
 * This makes it incompatible with our modern pipeline, which uses Frame[].
 * We will need an Adapter (SharpenAdapter) to bridge this gap.
 */
public class LegacySharpen {

    /*
     * Apply sharpening to a set of frames represented by a "handle" string.
     *
     * @param framesHandle   a string representing frame data (not actual Frame[])
     * @param strength       integer strength of sharpening
     * @return               a new handle string after sharpening
     */
    public String applySharpen(String framesHandle, int strength) { 
        return "HANDLE:" + strength; // Simulated sharpened handle
    }
}
