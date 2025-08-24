package shippingStrategy;

public class StandardShippingCost implements ShippingCostStrategy {
    @Override
    public double calculateShippingCost(Shipment shipment) {
        return 50 + 5 * shipment.weightKg;
    }
}
