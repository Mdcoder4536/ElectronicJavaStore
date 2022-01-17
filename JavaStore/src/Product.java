//Base class for all products the store will sell
public class Product{
 private double price;
 private int stockQuantity;
 private int soldQuantity;
 private int cartQuantity;
 
 public Product(double initPrice,int initQuantity,int initCart){
   price = initPrice;
   stockQuantity = initQuantity;
   cartQuantity = initCart;
 }

 public void setCartQuantity(int cartQuantity) { this.cartQuantity = cartQuantity; }

 public int getStockQuantity(){
   return stockQuantity;
 }
 
 public int getSoldQuantity(){
   return soldQuantity;
 }
 
 public double getPrice(){
   return price;
 }


 public int getCartQuantity() { return cartQuantity; }

 public Boolean isAvailable(){
     if(this.cartQuantity >= this.stockQuantity){
         return false;
     }
     else{
         return true;
     }
 }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
 //Return 0 otherwise (i.e., there is no sale completed)
 public double sellUnits(int amount){
   if(amount > 0 && stockQuantity >= amount){
     stockQuantity -= amount;
     soldQuantity += amount;
     return price * amount;
   }
   return 0.0;
 }
}