package com.example.report;
import java.io.*; import java.nio.file.*; import java.util.Map;

public class JsonWriter {
    public Path write(Map<String,Object> data, Path outDir, String fileName) {
        try {
            // ensure output directory exists
            Files.createDirectories(outDir);

            // create file path: outDir/report.json
            Path p = outDir.resolve(fileName + ".json");

            // open writer and write dummy JSON
            try (BufferedWriter w = Files.newBufferedWriter(p)) {
                w.write("{\"ok\":true,\"name\":\"" + data.get("name") + "\"}");
            }
            return p;  // return path to created file
        } catch (IOException e) { throw new UncheckedIOException(e); }
    }
}
