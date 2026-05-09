package shopix;

/* PaymentStrategy arayüzü, farklı ödeme yöntemleri için ortak bir şablon sunar.
 * <T> Ödeme detaylarının tipini temsil eden Generic parametre (Örn: CardDetails, IbanInfo)
 */
public interface PaymentStrategy<T> {
	void pay(T details) throws Exception; //Ödeme işlemi gerçekleştiren method.
}
