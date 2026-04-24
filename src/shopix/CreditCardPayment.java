package shopix;
//Kredi kartı ile ödeme mantığını uygular
public class CreditCardPayment extends Payment<CreditCard> {

	//Ödeme işleminin somut uygulaması.
	 @Override
	    public void pay(CreditCard details) {
	        logPayment();
	        //ödeme işleminin simülasyomu.
	        System.out.println("Kredi kartı ile ödeme başarılı: " + details.getCardNo());
	    }
	 
	//sınıf  sürüm damgası
	 private static final long serialVersionUID = 1L;
}
