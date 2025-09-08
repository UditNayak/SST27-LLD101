package beverages_decorator;

public class Client {

    public static void main(String[] args) {

        // Base Cappuccino
        Beverage coffee = new Cappuccino();
        System.out.println("Plain Cappuccino cost: " + coffee.cost());

        // Cappuccino + Milk + Mocha
        Beverage fancyCoffee = new Mocha(new Milk(new Cappuccino()));
        System.out.println("Cappuccino + Milk + Mocha cost: " + fancyCoffee.cost());

        // Latte + Sugar + WhippedCream
        Beverage sweetLatte = new WhippedCream(new Sugar(new Latte()));
        System.out.println("Latte + Sugar + WhippedCream cost: " + sweetLatte.cost());

        // Cappuccino + Sugar + Sugar + Milk (stackable)
        Beverage extraSweet = new Milk(new Sugar(new Sugar(new Cappuccino())));
        System.out.println("Cappuccino + 2x Sugar + Milk cost: " + extraSweet.cost());
    }
}
