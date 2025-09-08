package beverages_decorator;

public abstract class AddonDecorator extends Beverage {
    protected final Beverage beverage;

    public AddonDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}