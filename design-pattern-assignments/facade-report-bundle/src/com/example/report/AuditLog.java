package com.example.report;
import java.time.*; 
import java.nio.file.*; 
import java.io.*;

public class AuditLog {
    /**
     * Append a log entry to audit.log with current timestamp
     * @param msg the message to log
     */
    public void log(String msg) {
        try {
            // create path to audit log file
            Path logFile = Path.of("audit.log");
            
            // format log entry with timestamp and message
            String logEntry = Instant.now() + " " + msg + "\n";
            
            // write to log file, creating it if needed and appending to existing content
            Files.writeString(logFile, logEntry,
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND);
        } catch (IOException e) { 
            throw new UncheckedIOException(e); 
        }
    }
}