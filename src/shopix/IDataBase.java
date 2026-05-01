package shopix;

import java.util.List;

public interface IDataBase {
    // Nesneyi ve hangi tabloya kaydedileceğini alır
    void kaydet(Object veri, String tabloAdi);
    
    // Veri çekme işlemi
    Object veriCek(String tabloAdi);
}