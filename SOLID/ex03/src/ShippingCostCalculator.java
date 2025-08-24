import shippingStrategy.Shipment;
import shippingStrategy.ShippingCostStrategy;

public class ShippingCostCalculator {
    private ShippingCostStrategy shippingCostStrategy;

    public ShippingCostCalculator(ShippingCostStrategy shippingCostStrategy) {
        this.shippingCostStrategy = shippingCostStrategy;
    }

    public double cost(Shipment shipment) {
        return shippingCostStrategy.calculateShippingCost(shipment);
    }
}
