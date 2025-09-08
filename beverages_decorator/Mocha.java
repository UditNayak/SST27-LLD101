package beverages_decorator;

public class Mocha extends AddonDecorator {
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 7; // mocha costs extra 7
    }
}