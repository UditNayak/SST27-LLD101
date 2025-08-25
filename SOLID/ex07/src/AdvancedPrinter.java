public class AdvancedPrinter implements Printer, Scanner, Fax {
    public void print(String text) {
        System.out.println("Print: " + text);
    }

    public void scan(String dstPath) {
        System.out.println("Scanning to " + dstPath);
    }

    public void fax(String number) {
        System.out.println("Faxing to " + number);
    }
}