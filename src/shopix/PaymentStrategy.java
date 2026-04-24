package shopix;
import java.io.Serializable;

/* PaymentStrategy arayüzü, farklı ödeme yöntemleri için ortak bir şablon sunar.
 * Strategy Design Pattern kullanılarak ödeme mantığının esnek olmasını sağlar.
 * <T> Ödeme detaylarının tipini temsil eden Generic parametre (Örn: CardDetails, IbanInfo)
 */
public interface PaymentStrategy<T> extends Serializable{
	void pay(T details) throws Exception; //Ödeme işlemi gerçekleştiren method.
}
