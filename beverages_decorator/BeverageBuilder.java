package beverages_decorator;

public class BeverageBuilder {
    private Beverage beverage;

    public BeverageBuilder(Beverage base) {
        this.beverage = base;
    }

    public BeverageBuilder addMilk() {
        beverage = new Milk(beverage);
        return this;
    }

    public BeverageBuilder addChocolate() {
        beverage = new Chocolate(beverage);
        return this;
    }

    public BeverageBuilder addSugar() {
        beverage = new Sugar(beverage);
        return this;
    }

    public BeverageBuilder addCream() {
        beverage = new Cream(beverage);
        return this;
    }

    public Beverage build() {
        return beverage;
    }
}