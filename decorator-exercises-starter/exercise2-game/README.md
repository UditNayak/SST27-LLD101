# Game Character Power-ups with Decorator Pattern

## File Structure
```
exercise2-game/
├── src/
│   └── com/example/game/
│       ├── Character.java
│       ├── BaseCharacter.java
│       ├── CharacterDecorator.java
│       ├── SpeedBoost.java
│       ├── DamageBoost.java
│       ├── GoldenAura.java
│       └── GameDemo.java
└── README.md
```

## Output
```
--- Base ---
Moving at speed 5 with sprite base.png
Attacking with damage 10 using sprite base.png

--- Buffed (Speed + Damage) ---
Moving at boosted speed 8 with sprite base.png
Attacking with boosted damage 25 using sprite base.png

--- With Golden Aura ---
Golden Aura active
Moving at boosted speed 8 with sprite base.png
Golden Aura active
Attacking with boosted damage 25 using sprite base.png

--- Without Golden Aura ---
Moving at boosted speed 8 with sprite base.png
Attacking with boosted damage 25 using sprite base.png
```

## Factory 1 (Predefined Combos)
```java
public class CharacterFactory {
    public static Character createSpeedAndDamage(Character base, int speed, int damage) {
        return new DamageBoost(new SpeedBoost(base, speed), damage);
    }

    public static Character createFull(Character base, int speed, int damage) {
        return new GoldenAura(
                   new DamageBoost(
                       new SpeedBoost(base, speed), damage));
    }
}
```

### Usage
```java
Character hero = new BaseCharacter("Hero");
hero = CharacterFactory.createFull(hero, 3, 15);

hero.move();
hero.attack();
```

---

## Factory 2 (Config-driven)
```java
public class CharacterFactory {
    public enum Buff { SPEED, DAMAGE, GOLDEN_AURA }

    public static Character create(Character base, Map<Buff, Integer> config) {
        Character c = base;

        if (config.containsKey(Buff.SPEED)) {
            c = new SpeedBoost(c, config.get(Buff.SPEED));
        }
        if (config.containsKey(Buff.DAMAGE)) {
            c = new DamageBoost(c, config.get(Buff.DAMAGE));
        }
        if (config.containsKey(Buff.GOLDEN_AURA)) {
            c = new GoldenAura(c); // aura doesn’t need an int, just wrap
        }
        return c;
    }
}
```

### Usage
```java
Character hero = new BaseCharacter("Hero");
Map<CharacterFactory.Buff, Integer> config = new HashMap<>();
config.put(CharacterFactory.Buff.SPEED, 3);
config.put(CharacterFactory.Buff.DAMAGE, 20);
config.put(CharacterFactory.Buff.GOLDEN_AURA, 1); // presence = apply

hero = CharacterFactory.create(hero, config);
hero.move();
hero.attack();
```

---

## Builder
```java
public class CharacterBuilder {
    private Character character;

    public CharacterBuilder(String name) {
        this.character = new BaseCharacter(name);
    }

    public CharacterBuilder withSpeed(int speed) {
        this.character = new SpeedBoost(character, speed);
        return this;
    }

    public CharacterBuilder withDamage(int damage) {
        this.character = new DamageBoost(character, damage);
        return this;
    }

    public CharacterBuilder withGoldenAura() {
        this.character = new GoldenAura(character);
        return this;
    }

    public Character build() {
        return character;
    }
}
```

### Usage
```java
Character hero = new CharacterBuilder("Hero")
                     .withSpeed(3)
                     .withDamage(20)
                     .withGoldenAura()
                     .build();

hero.move();
hero.attack();
```