package paymentProcessor;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(Payment payment) {
        return "Paid via UPI: " + payment.getAmount();
    }
}