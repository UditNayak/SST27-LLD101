package beverages_decorator;

public class Client {

    public static void main(String[] args) {

        // Base Cappuccino
        Beverage coffee = new Cappuccino();
        System.out.println("Plain Cappuccino cost: " + coffee.cost());

        // Cappuccino + Milk + Chocolate
        Beverage fancyCoffee = new Chocolate(new Milk(new Cappuccino()));
        System.out.println("Cappuccino + Milk + Chocolate cost: " + fancyCoffee.cost());

        // Latte + Sugar + Cream
        Beverage sweetLatte = new Cream(new Sugar(new Latte()));
        System.out.println("Latte + Sugar + Cream cost: " + sweetLatte.cost());

        // Cappuccino + Sugar + Sugar + Milk (stackable)
        Beverage extraSweet = new Milk(new Sugar(new Sugar(new Cappuccino())));
        System.out.println("Cappuccino + 2x Sugar + Milk cost: " + extraSweet.cost());
    }
}