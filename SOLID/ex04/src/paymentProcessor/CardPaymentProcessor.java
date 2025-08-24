package paymentProcessor;

public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(Payment payment) {
        return "Charged card: " + payment.getAmount();
    }
}