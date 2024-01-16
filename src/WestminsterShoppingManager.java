import java.util.*;
import java.io.*;
public class WestminsterShoppingManager implements ShoppingManager {
   public List<Product> productList;

   public WestminsterShoppingManager() {
      this.productList = new ArrayList<>();
   }

   public void addProduct(Product Product, List<Product> productList) {
      if (productList.size() > 50) {
         System.out.println("Cannot add product maximum");
      } else {
         productList.add(Product);
         System.out.println("Product added succesfully");
      }


   }

   public void deleteProduct(String productId, List<Product> productList) {
      for (Product product : productList) {
         if (product.getProductId().equals(productId)) {
            productList.remove(product);
            System.out.println("product removed sucessfully");
            return;
         } else {
            System.out.println("product not found");
         }

      }


   }


   public void listProduct(List<Product> productList) {
      if (productList.isEmpty()) {
         System.out.println("No product in the list");
      } else {
         for (Product product : productList) {
            if (product instanceof Electronics) {
               product.displayInfo();
            } else {
               product.displayInfo();
            }

         }
      }


   }

   public void saveProduct(List<Product> productList) {
      try {
         // FileWriter object with the filename, it will automatically create a new file
         // or overwrite an existing file with the same name.
         FileWriter writer = new FileWriter("Products.txt");
         for (Product product : productList) {
            if (product instanceof Electronics) {
               writer.write("\nElectronics:");
            } else if (product instanceof Clothing) {
               writer.write("\nClothing:");
            }
            writer.write("\nProduct ID: " + product.getProductId() +
                    "\nProduct Name: " + product.getProductName() +
                    "\nAvailable Items: " + product.getNumberOfItems() +
                    "\nPrice: " + product.getPrice());
            if (product instanceof Electronics electronics) {
               writer.write("\nBrand: " + electronics.getBrand() +
                       "\nWarranty Period: " + electronics.getWarrantyPeriod());
            } else if (product instanceof Clothing clothing) {
              writer.write("\nSize: " + clothing.getSize() +
                      "\nColour: " + clothing.getColor());
            }
            // separator between products
            writer.write("\n=================");
         }
         writer.close();
         System.out.println("Products saved to the file.");

      } catch (IOException e) {
         System.out.println("An error occurred while saving the file.");
         //e.printStackTrace(); check and put
      }
   }

   public List<Product> loadProduct() {
      // Clear the list before loading products
      try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
         productList.clear();
         String line;

         while ((line = reader.readLine()) != null) {
            if (line.equals("Electronics:")) {
               String productId = getValueFromLine(reader.readLine());
               String productName = getValueFromLine(reader.readLine());
               int numberOfItems = Integer.parseInt(getValueFromLine(reader.readLine()));
               double price = Double.parseDouble(getValueFromLine(reader.readLine()));
               String brand = getValueFromLine(reader.readLine());
               String warranty = getValueFromLine(reader.readLine());
               productList.add(new Electronics(productId, productName, numberOfItems, price, brand, warranty));
            } else if (line.equals("Clothing:")) {
               String productId = getValueFromLine(reader.readLine());
               String productName = getValueFromLine(reader.readLine());
               int numberOfItems = Integer.parseInt(getValueFromLine(reader.readLine()));
               double price = Double.parseDouble(getValueFromLine(reader.readLine()));
               String size = getValueFromLine(reader.readLine());
               String color = getValueFromLine(reader.readLine());
               productList.add(new Clothing(productId, productName, numberOfItems, price, size, color));
            }
         }
         System.out.println("Products loaded from file.");
      } catch (IOException e) {
         System.out.println("An error occurred while trying to load from file: " + e.getMessage());
      }
      return productList;
   }
   private String getValueFromLine(String line) {
      return line.split(":")[1].trim();
   }
}



