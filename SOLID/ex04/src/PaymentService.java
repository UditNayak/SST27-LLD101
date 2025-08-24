import paymentProcessor.*;

public class PaymentService {
    public String pay(Payment payment) {
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(payment.getProvider());
        return processor.processPayment(payment);
    }
}
