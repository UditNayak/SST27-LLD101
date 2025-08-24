public class Demo09 {
    public static void main(String[] args) {
        OrderRepository repo = new SqlOrderRepository();
        OrderController controller = new OrderController(repo);
        controller.create("ORD-1");
    }
}
