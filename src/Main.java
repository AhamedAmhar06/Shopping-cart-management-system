import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        WestminsterShoppingManager manager=new WestminsterShoppingManager();
        //List<Product> productList = new ArrayList<>();
        List<Product> productList=manager.loadProduct();



        while(true) {
            System.out.println();
            System.out.println("1.Add a product");
            System.out.println("2.Delete a product");
            System.out.println("3.Print the product list");
            System.out.println("4.Save the product list");
            System.out.println("5.Exit\n");
            System.out.println("Enter your choice");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        //add product
                        System.out.println("Enter Product type(1 for Clothing,2 for Electronics");
                        int type = sc.nextInt();
                        System.out.println("Enter Product Id");
                        String productId = sc.next();

                        System.out.println("Enter Product Name");
                        while (!sc.hasNext("[a-zA-Z]+")) {
                            System.out.println("Invalid input. Please enter a valid product Name: ");
                            sc.next();
                        }
                        String productName = sc.next();
                        // Now, productName contains a valid non-empty value
                        System.out.println("Enter Number of items");
                        int numberOfItems = sc.nextInt();
                        System.out.println("Enter product price");
                        int price = sc.nextInt();
                        if (type == 1) {
                            System.out.println("Enter size");
                            String size = sc.next();
                            System.out.println("Enter color");
                            String color = sc.next();
                            Clothing clothing = new Clothing(productId, productName, numberOfItems, price, size, color);
                            manager.addProduct(clothing, productList);


                        } else if (type == 2) {
                            System.out.println("Enter brand");
                            String brand = sc.next();
                            System.out.println("Enter warranty period");
                            String warrantyPeriod = sc.next();
                            Electronics electronics = new Electronics(productId, productName, numberOfItems, price, brand, warrantyPeriod);
                            manager.addProduct(electronics, productList);

                        } else {
                            System.out.println("Wrong type please try again");


                        }
                        break;
                    case 2:
                        System.out.print("Enter product ID: ");
                        productId = sc.next();
                        manager.deleteProduct(productId, productList);
                        break;
                    case 3:
                        manager.listProduct(productList);

                        break;
                    case 4:
                        manager.saveProduct(productList);


                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice please try again");

                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();  // Consume the invalid input

            }
        }




    }
}