package com.example.game;

public class GoldenAura extends CharacterDecorator {
    public GoldenAura(Character wrappee) {
        super(wrappee);
    }

    @Override
    public void move() {
        System.out.println("✨ Golden Aura active ✨");
        super.move();
    }

    @Override
    public void attack() {
        System.out.println("✨ Golden Aura active ✨");
        super.attack();
    }

    @Override
    public int getSpeed() {
        return super.getSpeed() + 2; // small buff
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 5; // small buff
    }

    @Override
    public String getSprite() {
        return "golden_" + super.getSprite();
    }
}
