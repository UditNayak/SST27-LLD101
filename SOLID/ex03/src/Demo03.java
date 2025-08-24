import shippingStrategy.Shipment;
import shippingStrategy.ShippingCostStrategy;

public class Demo03 {
    public static void main(String[] args) {
        Shipment shipment = new Shipment("EXPRESS", 2.0);
        ShippingCostStrategy shippingCostStrategy = ShippingStrategyFactory.getShippingCostStrategy(shipment.type);
        ShippingCostCalculator calculator = new ShippingCostCalculator(shippingCostStrategy);
        System.out.println(calculator.cost(shipment));
    }
}
