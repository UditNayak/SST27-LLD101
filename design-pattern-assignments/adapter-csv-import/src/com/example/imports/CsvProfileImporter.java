package com.example.imports;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class CsvProfileImporter implements ProfileImporter {
    private final NaiveCsvReader reader;
    private final ProfileService service;

    public CsvProfileImporter(NaiveCsvReader reader, ProfileService service) {
        this.reader = Objects.requireNonNull(reader);
        this.service = Objects.requireNonNull(service);
    }

    @Override
    public int importFrom(Path csvFile) {
        List<String[]> rows = reader.read(csvFile);
        int successCount = 0;

        for (String[] row : rows) {
            if (row.length < 3) {
                System.out.println("Invalid row (missing columns): " + String.join(",", row));
                continue;
            }

            String id = row[0].trim();
            String email = row[1].trim();
            String displayName = row[2].trim();

            if (id.isEmpty() || email.isEmpty() || !email.contains("@")) {
                System.out.println("Invalid row (invalid data): " + String.join(",", row));
                continue;
            }

            try{
                service.createProfile(id, email, displayName);
                successCount++;
            } catch (Exception e) {
                System.out.println("Failed to create profile for row: " + String.join(",", row) + " due to " + " (" + e.getMessage() + ")");
            }
        }

        return successCount;
    }
}
