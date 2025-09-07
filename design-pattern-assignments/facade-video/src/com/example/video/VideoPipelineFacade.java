package com.example.video;

import java.nio.file.Path;

/*
 * Facade that hides the complexity of wiring Decoder, FilterEngine, Encoder,
 * and the legacy sharpening filter (via an adapter).
 */
public class VideoPipelineFacade {
    private final Decoder decoder;
    private final FilterEngine filterEngine;
    private final Encoder encoder;
    private final ISharpenFilter sharpenFilter;

    public VideoPipelineFacade(Decoder decoder, FilterEngine filterEngine, Encoder encoder, ISharpenFilter sharpenFilter) {
        this.decoder = decoder;
        this.filterEngine = filterEngine;
        this.encoder = encoder;
        this.sharpenFilter = sharpenFilter;
    }

    /*
     * Process a video with optional filters.
     * 
     * @param src              input file path
     * @param out              output file path
     * @param gray             apply grayscale if true
     * @param scale            scaling factor (e.g., 0.5 for half size), or null for no scaling
     * @param sharpenStrength  strength of sharpening filter, or null for no sharpening
     * @return the path to the processed video file
     */
    public Path process(Path src, Path out, boolean gray, Double scale, Integer sharpenStrength) {
        // Step 1: Decode the video file into frames
        Frame[] frames = decoder.decode(src);

        // Step 2: Apply filters as requested
        if (gray) frames = filterEngine.grayscale(frames);
        if (scale != null) frames = filterEngine.scale(frames, scale);

        // Step 3: Apply sharpening via the adapter if requested
        if (sharpenStrength != null) frames = sharpenFilter.sharpen(frames, sharpenStrength);

        // Step 4: Encode the processed frames back into a video file
        return encoder.encode(frames, out);
    }
}
