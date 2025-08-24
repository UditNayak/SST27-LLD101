import shippingStrategy.*;

public class ShippingStrategyFactory {
    public static ShippingCostStrategy getShippingCostStrategy(String type) {
        switch (type) {
            case "STANDARD": return new StandardShippingCost();
            case "EXPRESS": return new ExpressShippingCost();
            case "OVERNIGHT": return new OvernightShippingCost();
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
