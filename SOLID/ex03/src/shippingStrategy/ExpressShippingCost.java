package shippingStrategy;

public class ExpressShippingCost implements ShippingCostStrategy {
    @Override
    public double calculateShippingCost(Shipment shipment) {
        return 80 + 8 * shipment.weightKg;
    }
}
