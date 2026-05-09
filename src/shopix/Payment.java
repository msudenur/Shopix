package shopix;

/*Tüm ödeme yöntemleri için ortak olan davranışları barındırır. */
public abstract class Payment<T> implements PaymentStrategy<T> {
	//Tüm ödeme yöntemlerinde ortak olarak kullanılacak metot.
	protected void logPayment() {
        System.out.println("Ödeme işlemi başlatıldı...");
    }
}
