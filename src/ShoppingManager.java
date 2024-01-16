import java.util.List;

public interface ShoppingManager {

     void addProduct(Product product, List<Product> productList);
     void deleteProduct(String productId,List<Product>productList);
     void listProduct(List<Product>productList);
     void saveProduct(List<Product>productList);


}
