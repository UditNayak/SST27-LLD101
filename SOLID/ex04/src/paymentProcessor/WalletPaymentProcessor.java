package paymentProcessor;

public class WalletPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(Payment payment) {
        return "Wallet debit: " + payment.getAmount();
    }
}