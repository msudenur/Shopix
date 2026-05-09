package shopix;

//Kullanıcı eğer credi cartı ile ödemeyi seçerse girilen kart bilgilerini tutar.
public class CreditCard {
	 private String cardNo;
	 private String cvv;
	 private String date;
	 
	 public CreditCard(String cardNo, String cvv, String date) throws Exception {
		 if (cardNo.length() != 16) {
			    throw new Exception("Kart numarası 16 haneli olmalı!");
			}

			if (cvv.length() != 3) {
			    throw new Exception("CVV 3 haneli olmalı!");
			}
	        this.cardNo = cardNo;
	        this.cvv = cvv;
	        this.date = date;
	 }
	 //sadece kkart numarası dışarıya açık hale getirildi, diğer bilgiler güvenlik nedeniyle okunmamalı.
	 public String getCardNo() {
	        return cardNo;
	 }
	 
}