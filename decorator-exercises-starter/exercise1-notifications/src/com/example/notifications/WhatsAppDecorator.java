package com.example.notifications;

public class WhatsAppDecorator extends NotifierDecorator {
    private final String whatsappId;

    public WhatsAppDecorator(Notifier wrappee, String whatsappId) {
        super(wrappee);
        this.whatsappId = whatsappId;
    }

    @Override
    public void notify(String text) {
        super.notify(text);
        System.out.println("[WHATSAPP -> " + whatsappId + "]: " + text);
    }
}