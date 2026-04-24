package shopix;
//E-posta üzerinden ödeme yapılmasını sağlar.
public class PayPalPayment extends Payment<String> {
	//Ödeme işlemini gerçekleştirir.
	 @Override
	 public void pay(String email) {
		 logPayment();
		 //Ödeme işleminin simülasyonu.
	     System.out.println("PayPal ödeme başarılı: " + email);
	 }
	//sınıf  sürüm damgası
    private static final long serialVersionUID = 1L;
}
