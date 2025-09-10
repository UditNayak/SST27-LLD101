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
[WHATSAPP -> +91-99999-11111]: Staging deployed 🟢

[EMAIL -> user@example.com]: Prod hotfix released 🔥
[SLACK #general]: Prod hotfix released 🔥

[EMAIL -> user@example.com]: Deployment completed 🚀
[WHATSAPP -> +91-99999-11111]: Deployment completed 🚀
[SLACK #deployments]: Deployment completed 🚀
```

## Factory 1
```java
public class NotifierFactory {
    public static Notifier createEmailAndSms(String email, String phone) {
        return new SmsDecorator(new EmailNotifier(email), phone);
    }

    public static Notifier createEmailAndSlack(String email, String channel) {
        return new SlackDecorator(new EmailNotifier(email), channel);
    }

    public static Notifier createFull(String email, String phone, String waUser, String channel) {
        return new SlackDecorator(
            new WhatsAppDecorator(
                new SmsDecorator(new EmailNotifier(email), phone),
                waUser),
            channel
        );
    }
}
```

### Usage
```java
Notifier n = NotifierFactory.createFull("user@example.com", "999", "wa_user", "general");
n.notify("Deployment done 🚀");
```

## Factory 2
```java
public class NotifierFactory {
    public enum Channel { SMS, WHATSAPP, SLACK }

    public static Notifier create(String email, 
                                  Map<Channel, String> params) {
        Notifier notifier = new EmailNotifier(email);

        if (params.containsKey(Channel.SMS)) {
            notifier = new SmsDecorator(notifier, params.get(Channel.SMS));
        }
        if (params.containsKey(Channel.WHATSAPP)) {
            notifier = new WhatsAppDecorator(notifier, params.get(Channel.WHATSAPP));
        }
        if (params.containsKey(Channel.SLACK)) {
            notifier = new SlackDecorator(notifier, params.get(Channel.SLACK));
        }
        return notifier;
    }
}
```

### Usage
```java
Map<NotifierFactory.Channel, String> config = new HashMap<>();
config.put(NotifierFactory.Channel.SMS, "+91-99999-11111");
config.put(NotifierFactory.Channel.SLACK, "general");

Notifier n = NotifierFactory.create("user@example.com", config);
n.notify("Factory with params 🚀");
```

## Builder
```java
public class NotifierBuilder {
    private Notifier notifier;

    public NotifierBuilder(String email) {
        this.notifier = new EmailNotifier(email);
    }

    public NotifierBuilder withSms(String phone) {
        this.notifier = new SmsDecorator(notifier, phone);
        return this;
    }

    public NotifierBuilder withWhatsApp(String waUser) {
        this.notifier = new WhatsAppDecorator(notifier, waUser);
        return this;
    }

    public NotifierBuilder withSlack(String channel) {
        this.notifier = new SlackDecorator(notifier, channel);
        return this;
    }

    public Notifier build() {
        return notifier;
    }
}
```

### Usage
```java
Notifier notifier = new NotifierBuilder("user@example.com")
                        .withSms("+91-99999-11111")
                        .withWhatsApp("user_wa")
                        .withSlack("general")
                        .build();

notifier.notify("Builder style 🚀");
```