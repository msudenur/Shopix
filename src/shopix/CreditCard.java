package shopix;
import java.io.Serializable;

//Kullanıcı eğer credi cartı ile ödemeyi seçerse girilen kart bilgilerini tutar.
public class CreditCard implements Serializable {
	 private String cardNo;
	 private String cvv;
	 private String date;
	 
	 public CreditCard(String cardNo, String cvv, String date) {
	        this.cardNo = cardNo;
	        this.cvv = cvv;
	        this.date = date;
	 }
	 //sadece kkart numarası dışarıya açık hale getirildi, diğer bilgiler güvenlik nedeniyle okunmamalı.
	 public String getCardNo() {
	        return cardNo;
	 }
	
	//sınıf  sürüm damgası
	private static final long serialVersionUID = 1L;
	 
}
