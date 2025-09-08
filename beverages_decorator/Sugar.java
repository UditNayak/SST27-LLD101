package beverages_decorator;

public class Sugar extends AddonDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int cost() {
        return beverage.cost() + 2; // sugar costs extra 2
    }
}