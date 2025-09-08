package com.example.game;

public abstract class CharacterDecorator implements Character {
    protected final Character wrappee;

    public CharacterDecorator(Character wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void move() {
        wrappee.move();
    }

    @Override
    public void attack() {
        wrappee.attack();
    }

    @Override
    public int getSpeed() {
        return wrappee.getSpeed();
    }

    @Override
    public int getDamage() {
        return wrappee.getDamage();
    }

    @Override
    public String getSprite() {
        return wrappee.getSprite();
    }
}
