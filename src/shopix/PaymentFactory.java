package shopix;
//Factory design Pattern uygular.İstenen ödeme tipine göre uygun nesneyi oluşturup döndürür.
public class PaymentFactory {
	//Kredi kartı ile ödeme stratejisini oluşturur ve döndürür
	 public static PaymentStrategy<CreditCard> card() {
		// CreditCardPayment nesnesi oluşturulup üst tip olarak döndürülür.
	        return new CreditCardPayment();
	    }
	 
	//E-posta ile ödeme stratejisini oluşturur ve döndürür
	    public static PaymentStrategy<String> paypal() {
	    	// PayPalPayment nesnesi oluşturulup üst tip olarak döndürülür.
	        return new PayPalPayment();
	    }
	  //Kapıda ödeme stratejisini oluşturur ve döndürür
	    public static PaymentStrategy<Double> cash() {
	    	// CashPayment nesnesi oluşturulup üst tip olarak döndürülür.
	        return new CashPayment();
	    }
 }
