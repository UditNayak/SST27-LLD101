package com.example.video;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        Decoder dec = new Decoder();
        FilterEngine fe = new FilterEngine();
        Encoder enc = new Encoder();

        ISharpenFilter sharpenAdapter = new SharpenAdapter(new LegacySharpen());

        VideoPipelineFacade facade = new VideoPipelineFacade(dec, fe, enc, sharpenAdapter);

        Path out = facade.process(
                Path.of("in.mp4"),
                Path.of("out.mp4"),
                true,   // grayscale
                0.5,    // scale factor
                3       // sharpen strength
        );
        
        System.out.println("Wrote " + out);
    }
}
