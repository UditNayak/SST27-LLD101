public class SqlOrderRepository implements OrderRepository {
    @Override
    public void save(String id) {
        System.out.println("Saved order " + id + " to SQL");
    }
}
