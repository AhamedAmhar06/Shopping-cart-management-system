public class Clothing extends Product {

    public String size;
    public String color;

    public Clothing(String productId,String productName,int numberOfItems,double price,String size,String color){
        super(productId,productName,numberOfItems,price);
        this.size=size;
        this.color=color;

    }

    @Override
    public void displayInfo() {
        System.out.println("Product id: "+ productId);
        System.out.println("Product name: "+ productName);
        System.out.println("No of items : "+ numberOfItems);
        System.out.println("price "+ price);
        System.out.println("size"+size);
        System.out.println("color"+color);

    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  size +", "+ color ;
    }
}

