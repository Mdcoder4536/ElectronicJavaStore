import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

import java.util.Arrays;

public class ElectronicStoreView extends Pane{
    private ListView<Product>       popularItems,Stock;
    private ListView<String>        Cart;
    private TextField           sales, revenue, $sale;
    private Button              reset, Add, Remove,Complete;
    private Label label7 = new Label("");


    public ListView<Product> getPopularItems() { return popularItems; }
    public ListView<Product> getStock() { return Stock; }
    public ListView<String> getCart() { return Cart; }
    public TextField getSales() { return sales; }
    public TextField getRevenue() { return revenue; }
    public TextField get$Sale() { return $sale; }
    public Button getAdd() { return Add; }
    public Button getComplete() { return Complete; }
    public Button getRemove() { return Remove; }
    public Button getReset() { return reset; }

    public void update(ElectronicStore store){
        //set default values
        getRemove().setDisable(true);
        g
        double sale = store.getRevenue()/store.getSales();
        Product[] stocks = store.availableStock();
        String[] cartObjects = store.cartObjects();
        Product[] popularObjects = store.popularStock();
        Stock.setItems(FXCollections.observableArrayList(stocks));
        Cart.setItems(FXCollections.observableArrayList(cartObjects));
        popularItems.setItems(FXCollections.observableArrayList(popularObjects));
        sales.setText(String.valueOf(store.getSales()));
        revenue.setText(String.format("%.2f",store.getRevenue()));
        if(store.getCartPrice()>0){
            label7.setText("Current Cart($"+ String.valueOf(store.getCartPrice()) +")");
            Complete.setDisable(false);
        }
        else {
            label7.setText("Current Cart($0.00)");
            Complete.setDisable(true);
            Remove.setDisable(true);
        }
        if(sale > 0){
            $sale.setText(String.format("%.2f", sale));
        }
        else{
            $sale.setText("N/A");
        }
    }



    public ElectronicStoreView() {
        // Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(60, 20);
        Label label2 = new Label("# Sales:");
        label2.relocate(50, 60);
        Label label3 = new Label("Revenue:");
        label3.relocate(40, 100);
        Label label4 = new Label("$ / Sale:");
        label4.relocate(50, 140);
        Label label5 = new Label("Most Popular Items:");
        label5.relocate(50, 180);
        Label label6 = new Label("Store Stocks:");
        label6.relocate(320, 20);
        //Label label7 = new Label("Current Cart ($):");
        label7.relocate(600, 20);
        ;

        // Create the TextFields
        sales = new TextField();
        sales.relocate(100, 55);
        sales.setPrefSize(110, 30);
        sales.setEditable(false);
        revenue = new TextField();
        revenue.relocate(100, 95);
        revenue.setPrefSize(110, 30);
        revenue.setEditable(false);
        $sale = new TextField();
        $sale.relocate(100, 135);
        $sale.setPrefSize(110, 30);
        $sale.setEditable(false);

        // Create the lists
        Stock = new ListView<Product>();
        Stock.relocate(220, 55);
        Stock.setPrefSize(300, 295);
        Cart = new ListView<String>();
        Cart.relocate(530, 55);
        Cart.setPrefSize(250, 295);
        popularItems = new ListView<Product>();
        popularItems.relocate(10, 200);
        popularItems.setPrefSize(200, 150);

        // Create the buttons
        reset = new Button("Reset Store");
        reset.relocate(30,355);
        reset.setPrefSize(120,40);
        Add = new Button("Add to Cart");
        Add.setDisable(true);
        Add.relocate(300,355);
        Add.setPrefSize(120,40);
        Remove = new Button("Remove from Cart");
        Remove.setDisable(true);
        Remove.relocate(530,355);
        Remove.setPrefSize(120,40);
        Complete = new Button("Complete Sale");
        Complete.setDisable(true);
        Complete.relocate(660,355);
        Complete.setPrefSize(120,40);



        // Add all the components to the Pane
        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7,popularItems,Stock,Cart,sales, revenue, $sale,reset, Add, Remove,Complete);

        setPrefSize(800, 400);
    }
}
