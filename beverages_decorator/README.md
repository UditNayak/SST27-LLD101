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
│       ├── Mocha.java             # Concrete addon
│       ├── Sugar.java             # Concrete addon
│       ├── WhippedCream.java      # Concrete addon
│       └── Client.java            # Client demo
└── README.md
```

## Output
```
Plain Cappuccino cost: 10
Cappuccino + Milk + Mocha cost: 22
Latte + Sugar + WhippedCream cost: 28
Cappuccino + 2x Sugar + Milk cost: 19
```