package shopix;
//kapıda nakit ödeme seçeneğini temsil eder.
public class CashPayment extends Payment<Double> {
	//Kapıda ödeme işlemini simüle eder.
	@Override
    public void pay(Double amount) {
        logPayment();
        //Özel bilgilendirme mesaji
        System.out.println("Kapıda ödeme seçildi. Tutar: " + amount + " TL");
    }
	
	//sınıf  sürüm damgası
	private static final long serialVersionUID = 1L;
}
