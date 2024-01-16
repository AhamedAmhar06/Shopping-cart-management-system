public class Electronics extends Product {



    public String brand;
    public String warrantyPeriod;



    public Electronics(String productId,String productName,int numberOfItems,double price,String brand,String warrantyPeriod){
        super(productId,productName,numberOfItems,price);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;

    }
    @Override
    public void displayInfo() {
        System.out.println("Product id: "+ productId);
        System.out.println("Product name: "+ productName);
        System.out.println("No of items : "+ numberOfItems);
        System.out.println("price "+ price);
        System.out.println("Brand"+brand);
        System.out.println("warranty period "+warrantyPeriod);

    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    @Override
    public String toString() {
        return  brand +", "+ warrantyPeriod ;
    }



}

