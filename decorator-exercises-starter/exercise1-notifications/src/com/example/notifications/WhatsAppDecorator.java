package com.example.notifications;

public class WhatsAppDecorator extends NotifierDecorator {
    private final String phoneNumber;

    public WhatsAppDecorator(Notifier wrappee, String phoneNumber) {
        super(wrappee);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String text) {
        super.notify(text);
        System.out.println("[WHATSAPP -> " + phoneNumber + "]: " + text);
    }
}
