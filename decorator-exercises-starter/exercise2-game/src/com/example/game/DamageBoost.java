package com.example.game;

public class DamageBoost extends CharacterDecorator {
    private final int boost;

    public DamageBoost(Character wrappee, int boost) {
        super(wrappee);
        this.boost = boost;
    }

    @Override
    public void attack() {
        System.out.println("Attacking with boosted damage " + getDamage() + " using sprite " + getSprite());
    }

    @Override
    public int getDamage() {
        return super.getDamage() + boost;
    }
}