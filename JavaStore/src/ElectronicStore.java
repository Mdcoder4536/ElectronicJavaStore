//Class representing an electronic store
//Has an array of products that represent the items the store can sell
public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  private String name;
  private Product[] stock; //Array to hold all products
  private double revenue;
  private double cartPrice = 0;
  private int sales=0;
  private Product[] cartProducts;

  public Product[] popularStock(){
    Product[] popularStock = new Product[3];
    popularStock[0] = stock[0];
    popularStock[1] = stock[1];
    popularStock[2] = stock[2];
    int countAvailable = 0;
    for (int i=0;i<stock.length;i++) {
      if (stock[i]!= null && stock[i].getSoldQuantity()>0) {
        countAvailable++;
      }
    }
    Product[] availableStocks = new Product[countAvailable];
    int curIndex = 0;
    for (int i=0; i< stock.length;i++){
      if(stock[i]!= null && stock[i].getSoldQuantity()>0){
        availableStocks[curIndex] = stock[i];
        curIndex++;
      }
    }
    for(int i=0; i<availableStocks.length;i++){
      if(availableStocks[i].getSoldQuantity()>popularStock[0].getSoldQuantity()){
        popularStock[2] = popularStock[1];
        popularStock[1] = popularStock[0];
        popularStock[0] = availableStocks[i];
        continue;
      }
      else if(availableStocks[i].getSoldQuantity()>popularStock[1].getSoldQuantity()){
        if(popularStock[0] == availableStocks[i]){
          continue;
        }
        popularStock[2] = popularStock[1];
        popularStock[1] = availableStocks[i];
        continue;
      }
      else if(availableStocks[i].getSoldQuantity()>popularStock[2].getSoldQuantity()){
        popularStock[2] = availableStocks[i];
        continue;
      }
    }
    return popularStock;
  }

  public Product[] availableStock(){
    int countAvailable = 0;
    for (int i=0;i<stock.length;i++) {
      if (stock[i]!= null && stock[i].isAvailable()) {
        countAvailable++;
      }
    }
    Product[] availableStocks = new Product[countAvailable];
    int curIndex = 0;
    for (int i=0; i< stock.length;i++){
      if(stock[i]!= null && stock[i].isAvailable()){
        availableStocks[curIndex] = stock[i];
        curIndex++;
      }
    }
    return availableStocks;
  }


  public String[] cartObjects(){
    int countCart = 0;
    for (int i=0;i<stock.length;i++) {
      if (stock[i]!= null && stock[i].getCartQuantity()>0) {
        countCart++;
      }
    }
    String[] cartObjects = new String[countCart];
    cartProducts = new Product[countCart];
    int curIndex = 0;
    for (int i=0; i< stock.length;i++) {
      if (stock[i]!= null && stock[i].getCartQuantity()>0) {
        cartObjects[curIndex] = stock[i].getCartQuantity() + " x " + stock[i];
        cartProducts[curIndex] = stock[i];
        curIndex++;
      }
    }
    return cartObjects;
  }

  public void completeSale(){
    for (int i=0; i< stock.length;i++) {
      if (stock[i]!= null && stock[i].getCartQuantity()>0) {
        revenue += stock[i].sellUnits(stock[i].getCartQuantity());
        stock[i].setCartQuantity(0);
      }
    }
    sales += 1;
  }

  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
  }

  public String getName(){
    return name;
  }

  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     return true;
    }
    return false;
  }
  
  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      revenue += stock[index].sellUnits(amount);
    }
  }
  
  public double getRevenue(){
    if(revenue <= 0){
      return 0.00;
    }
    else {
      return revenue;
    }
  }

  public Product[] getCartProducts() { return cartProducts; }

  public double getCartPrice() {
    cartPrice = 0;
    if(cartProducts.length<=0){
      cartPrice = 0;
    }
    else {
      for (int i=0;i < cartProducts.length;i++){
        cartPrice += cartProducts[i].getPrice() * cartProducts[i].getCartQuantity();
      }
    }
    return cartPrice;
  }

  public int getSales() { return sales; }


  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100,10, 3.0, 16, false, 250, "Compact",0);
    Desktop d2 = new Desktop(200,10, 4.0, 32, true, 500, "Server",0);
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15,0);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16,0);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false,0);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true,0);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false,0);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true,0);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 