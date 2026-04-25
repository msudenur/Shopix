package shopix;

import java.io.*;
import java.util.*;

public class DosyaYonetimi implements IDataBase {
	
	//1. adım: Singleton için örnek (instance) oluştur
	private static DosyaYonetimi instance;
	
	//2. adım: Constructorı private yap ki dışarıdan newlenemesin
	private DosyaYonetimi() {}
	
	//3. adım: örneğe erişim sağla
	public static DosyaYonetimi getInstance() {
		if (instance == null) {
			instance = new DosyaYonetimi();
		}
		return instance;
	}

	@Override
	public void kaydet(Object veri, String dosyaYolu) {
		// TODO Auto-generated method stub
		
		// try-with-resources kullanarak dosyayı otomatik kapatır
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dosyaYolu))) {
			oos.writeObject(veri);
			System.out.println("Başarıyla kaydedildi: " + dosyaYolu);
		}catch (IOException e) {
			System.err.println("Dosyaya yazma hatası: " + e.getMessage());
		}

	}

	@Override
	public Object veriCek(String dosyaYolu) {
		// TODO Auto-generated method stub
		
		File dosya = new File(dosyaYolu);
		if (!dosya.exists())
			return null; //dosya bulamazsa null döner
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dosyaYolu))) {
			return ois.readObject();
		}catch (IOException | ClassNotFoundException e) {
			System.err.println("Dosyadan okuma hatası: " + e.getMessage());
			return null;
		}
		
	}

}
