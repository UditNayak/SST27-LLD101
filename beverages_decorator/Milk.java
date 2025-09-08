package beverages_decorator;

public class Milk extends AddonDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 5; // milk costs extra 5
    }
}