package shopix;
//Factory design Pattern uygular.İstenen ödeme tipine göre uygun nesneyi oluşturup döndürür.
public class PaymentFactory {
	public static PaymentStrategy<?> getPayment(String type) throws Exception {

        // Kullanıcının girdiği tipi küçük harfe çevirerek kontrol eder
        switch (type.toLowerCase()) {
            case "card":
                return new CreditCardPayment(); // Kredi kartı nesnesi üret
            case "paypal":
                return new PayPalPayment();     // PayPal nesnesi üret
            case "cash":
                return new CashPayment();       // Kapıda ödeme nesnesi üret
            default:
                // Tanınmayan bir tip girilirse programı uyarır
                throw new Exception("Invalid payment type: " + type);
        }
    }
}