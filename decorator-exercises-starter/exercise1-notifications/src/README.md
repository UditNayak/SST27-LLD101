# Notifications System - Decorator Pattern

## File Structure
```
exercise1-notifications/
├── src/
│   └── com/example/notifications/
│       ├── Demo.java
│       ├── Notifier.java               (Interface)
│       ├── EmailNotifier.java          (Concrete Component)   
│       ├── NotifierDecorator.java      (Abstract base Decorator)
│       ├── SmsDecorator.java           (Concrete Decorator)
│       ├── WhatsAppDecorator.java      (Concrete Decorator)
│       └── SlackDecorator.java         (Concrete Decorator)
└── README.md
```

## Result

```
[EMAIL -> user@example.com]: Baseline email only.

[EMAIL -> user@example.com]: Build green ✅
[SMS -> +91-99999-11111]: Build green ✅

[EMAIL -> user@example.com]: Staging deployed 🟢
[WHATSAPP -> user_wa]: Staging deployed 🟢

[EMAIL -> user@example.com]: Prod hotfix released 🔥
[SLACK #general]: Prod hotfix released 🔥

[EMAIL -> user@example.com]: Deployment completed 🚀
[WHATSAPP -> user_wa]: Deployment completed 🚀
[SLACK #deployments]: Deployment completed 🚀
```