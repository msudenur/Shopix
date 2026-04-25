package shopix;

import java.util.List;

public interface IDataBase {
	
	// veriyi kaydedecek
	void kaydet( Object veri, String dosyaYolu);
	
	// veriyi okur ve geri döner
	Object veriCek(String dosyaYolu);

}
