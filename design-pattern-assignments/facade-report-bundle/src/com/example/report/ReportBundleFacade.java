package com.example.report;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

public class ReportBundleFacade {
    private final JsonWriter writer = new JsonWriter();
    private final Zipper zipper = new Zipper();
    private final AuditLog log = new AuditLog();

    /*
     * Export the given data into a JSON, zip it, and log success.
     * @param data: report data
     * @param outDir: output directory
     * @param baseName: base name (without extension) for files
     * @return path: to the created .zip
     */
    public Path export(Map<String, Object> data, Path outDir, String baseName) {
        Objects.requireNonNull(data, "data must not be null");
        Objects.requireNonNull(outDir, "outDir must not be null");
        Objects.requireNonNull(baseName, "baseName must not be null");

        try {
            // Step 1: write JSON file
            Path json = writer.write(data, outDir, baseName);

            // Step 2: zip JSON → baseName.zip
            Path zip = zipper.zip(json, outDir.resolve(baseName + ".zip"));

            // Step 3: log success
            log.log("exported " + zip);

            return zip;
        } catch (UncheckedIOException e) {
            throw e; // already unchecked
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while exporting report bundle", e);
        }
    }
}