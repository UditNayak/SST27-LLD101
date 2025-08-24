

public class Demo01 {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        OrderRepository orderRepository = new OrderRepository();

        OrderService orderService = new OrderService(notifier, taxCalculator, orderRepository);
        orderService.checkout("a@shop.com", 100.0);
    }
}
