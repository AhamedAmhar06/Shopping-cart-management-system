public abstract class Product{



    public String productId;

    public String productName;

    public int numberOfItems;
    public double price;



//    public Product(String productId,String productName){
//
//        this.productId=productId;
//        this.productName=productName;
//
//    }
    public Product(String productId,String productName,int numberofItems,double price){

        this.productId=productId;
        this.productName=productName;
        this.numberOfItems =numberofItems;
        this.price=price;

    }


    public abstract void displayInfo();

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }












}

