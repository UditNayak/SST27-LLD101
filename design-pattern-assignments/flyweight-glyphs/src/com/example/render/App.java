package com.example.render;
public class App {
    public static void main(String[] args) {
        // Inject concrete factory into Renderer
        Renderer r = new Renderer(new TextStyleFactory());
        System.out.println("Cost=" + r.render("Hello Flyweight! ".repeat(2000)));
    }
}
