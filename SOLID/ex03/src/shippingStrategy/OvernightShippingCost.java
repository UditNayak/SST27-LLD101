package shippingStrategy;

public class OvernightShippingCost implements ShippingCostStrategy {
    @Override
    public double calculateShippingCost(Shipment shipment) {
        return 120 + 10 * shipment.weightKg;
    }
}
