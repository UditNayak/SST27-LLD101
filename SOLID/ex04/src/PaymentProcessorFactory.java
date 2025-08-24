import paymentProcessor.*;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(String provider) {
        switch (provider.toUpperCase()) {
            case "CARD":
                return new CardPaymentProcessor();
            case "UPI":
                return new UpiPaymentProcessor();
            case "WALLET":
                return new WalletPaymentProcessor();
            default:
                throw new IllegalArgumentException("No processor available for provider: " + provider);
        }
    }
}