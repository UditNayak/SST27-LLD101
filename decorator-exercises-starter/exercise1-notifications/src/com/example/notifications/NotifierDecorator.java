package com.example.notifications;

public abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void notify(String text) {
        wrappee.notify(text); // delegate to wrapped notifier
    }
}