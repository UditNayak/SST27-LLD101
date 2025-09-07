package com.example.report;
import java.io.*; import java.nio.file.*; import java.util.zip.*;

public class Zipper {
    public Path zip(Path file, Path outZip) {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(outZip))) {
            // create a zip entry with the original filename
            zos.putNextEntry(new ZipEntry(file.getFileName().toString()));

            // copy the original file into the zip stream
            Files.copy(file, zos); 
            
            // close the entry
            zos.closeEntry();
        } catch (IOException e) { throw new UncheckedIOException(e); }
        return outZip;
    }
}
