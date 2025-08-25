public class Demo10 {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger(); // Could also use new FileLogger()
        ReportService service = new ReportService(logger);
        service.generate();
    }
}