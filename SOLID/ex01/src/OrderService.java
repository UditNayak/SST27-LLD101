class OrderService {
    private Notifier notifier;
    private TaxCalculator taxCalculator;
    private OrderRepository orderRepository;

    public OrderService(Notifier notifier, TaxCalculator taxCalculator, OrderRepository orderRepository) {
        this.notifier = notifier;
        this.taxCalculator = taxCalculator;
        this.orderRepository = orderRepository;
    }

    public void checkout(String customerEmail, double subtotal) {
        double total = taxCalculator.totalWithTax(subtotal);
        notifier.send(customerEmail, "Thanks! Your total is " + total);
        orderRepository.saveOrder();
    }
}