public class Demo07 {
    public static void main(String[] args) {
        Printer basicPrinter = new BasicPrinter();
        basicPrinter.print("Hello");

        AdvancedPrinter advancedPrinter = new AdvancedPrinter();
        advancedPrinter.print("Hi");
        advancedPrinter.scan("/tmp/out");
        advancedPrinter.fax("1234567890");
    }
}