package shopix;
import java.io.Serializable;
/*Tüm ödeme yöntemleri için ortak olan davranışları barındırır. */
public abstract class Payment<T> implements PaymentStrategy<T>, Serializable {
	//Tüm ödeme yöntemlerinde ortak olarak kullanılacak metot.
	protected void logPayment() {
        System.out.println("Ödeme işlemi başlatıldı...");
    }
	
	//Sınıf sürüm damgası
	private static final long serialVersionUID = 1L;
}
