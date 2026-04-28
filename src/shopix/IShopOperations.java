package shopix;

public interface IShopOperations {

    void addProduct(Product product);

    void removeProduct(int productId);

    void updateProduct(Product product);
}