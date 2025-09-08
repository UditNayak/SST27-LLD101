package com.example.game;

public class GameDemo {
    public static void main(String[] args) {
        Character base = new BaseCharacter();

        System.out.println("--- Base ---");
        base.move();
        base.attack();

        // a) Base + SpeedBoost + DamageBoost
        System.out.println("\n--- Buffed (Speed + Damage) ---");
        Character buffed = new DamageBoost(new SpeedBoost(base, 3), 15);
        buffed.move();
        buffed.attack();

        // b) Add GoldenAura
        System.out.println("\n--- With Golden Aura ---");
        Character shiny = new GoldenAura(buffed);
        shiny.move();
        shiny.attack();

        // c) Remove GoldenAura by recomposition
        System.out.println("\n--- Without Golden Aura ---");
        Character withoutAura = buffed; // just reuse buffed chain
        withoutAura.move();
        withoutAura.attack();
    }
}