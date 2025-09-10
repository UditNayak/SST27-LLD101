# Beverages Decorator

This project demonstrates the Decorator Design Pattern using a beverage example. It allows you to create different types of beverages and add various condiments to them dynamically.

## Files
```
beverages_decorator/
├── src/
│   └── beverages_decorator/
│       ├── Beverage.java          # Abstract base class
│       ├── Cappuccino.java        # Concrete base beverage
│       ├── Latte.java             # Concrete base beverage
│       ├── AddonDecorator.java    # Abstract decorator
│       ├── Milk.java              # Concrete addon
│       ├── Chocolate.java         # Concrete addon
│       ├── Sugar.java             # Concrete addon
│       ├── Cream.java             # Concrete addon
│       └── Client.java            # Client demo
└── README.md
```

## Output
```
Plain Cappuccino cost: 10
Cappuccino + Milk + Chocolate cost: 22
Latte + Sugar + Cream cost: 28
Cappuccino + 2x Sugar + Milk cost: 19
```

## Builder

Sometimes, chaining multiple decorators with `new` becomes hard to read:

```java
Beverage coffee = new Chocolate(new Milk(new Cappuccino()));
Beverage sweetLatte = new Cream(new Sugar(new Latte()));
```

### Usage
```java
// Example: Cappuccino with Milk and Chocolate
Beverage customCoffee = new BeverageBuilder(new Cappuccino())
        .addMilk()
        .addChocolate()
        .build();

System.out.println("Custom coffee cost: " + customCoffee.cost());

// Example: Latte with Sugar and Cream
Beverage customLatte = new BeverageBuilder(new Latte())
        .addSugar()
        .addCream()
        .build();

System.out.println("Custom latte cost: " + customLatte.cost());
```