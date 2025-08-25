import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    @Override
    public void log(String msg) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write("[LOG] " + msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}