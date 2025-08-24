import paymentProcessor.Payment;

public class Demo04 {
    public static void main(String[] args) {
        Payment payment = new Payment("UPI", 499);
        PaymentService service = new PaymentService();
        System.out.println(service.pay(payment));
    }
}