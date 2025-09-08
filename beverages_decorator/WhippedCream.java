package beverages_decorator;

public class WhippedCream extends AddonDecorator {
    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 6; // whipped cream costs extra 6
    }
}