class EmailNotifier implements Notifier {
    @Override
    public void send(String to, String message) {
        System.out.println("[EMAIL to=" + to + "] " + message);
    }
}
