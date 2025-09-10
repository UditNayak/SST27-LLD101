package beverages_decorator;

public class Cream extends AddonDecorator {
    public Cream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 6; // whipped cream costs extra 6
    }
}