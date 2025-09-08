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
✨ Golden Aura active ✨
Moving at boosted speed 8 with sprite base.png
✨ Golden Aura active ✨
Attacking with boosted damage 25 using sprite base.png

--- Without Golden Aura ---
Moving at boosted speed 8 with sprite base.png
Attacking with boosted damage 25 using sprite base.png
```