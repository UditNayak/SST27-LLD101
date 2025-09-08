package com.example.notifications;

public class SmsDecorator extends NotifierDecorator {
    private final String phoneNumber;

    public SmsDecorator(Notifier wrappee, String phoneNumber) {
        super(wrappee);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String text) {
        super.notify(text); // call wrapped notifier
        System.out.println("[SMS -> " + phoneNumber + "]: " + text);
    }
}