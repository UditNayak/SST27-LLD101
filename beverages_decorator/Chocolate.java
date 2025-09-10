package beverages_decorator;

public class Chocolate extends AddonDecorator {
    public Chocolate(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 7; // chocolate costs extra 7
    }
}