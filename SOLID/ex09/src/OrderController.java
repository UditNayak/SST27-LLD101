public class OrderController {
    private final OrderRepository orderRepository;

    // Constructor Injection
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(String id) {
        orderRepository.save(id);
        System.out.println("Created order: " + id);
    }
}
