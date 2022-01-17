import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ElectronicStoreApp extends Application {
    private  ElectronicStore store;
    public void start(Stage primaryStage) {
        //Create the pane
        Pane aPane = new Pane();
        //Create instance of ElectronicStore and the view
        store = ElectronicStore.createStore();
        ElectronicStoreView  view = new ElectronicStoreView();
        view.update(store);
        //Event Handling for Stock view
        view.getStock().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                Product selectedProduct = view.getStock().getSelectionModel().getSelectedItem();
                view.getAdd().setDisable(selectedProduct == null);
            }
        });
        //Event Handling for the add button
        view.getAdd().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = view.getStock().getSelectionModel().getSelectedItem();
                selectedProduct.setCartQuantity(selectedProduct.getCartQuantity()+1);
                view.update(store);
            }
        });
        //Event Handling for Cart view
        view.getCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.getRemove().setDisable(false);
            }
        });
        //Event Handling for the Remove button
        view.getRemove().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                int index = view.getCart().getSelectionModel().getSelectedIndex();
                store.getCartProducts()[index].setCartQuantity(store.getCartProducts()[index].getCartQuantity()-1);
                view.update(store);
            }
        });
        //Event Handling for the complete button
        view.getComplete().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                store.completeSale();
                view.update(store);
            }
        });
        //reset Store
        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                store = ElectronicStore.createStore();
                view.getAdd().setDisable(true);
                view.update(store);
            }
        });
        // set up the view
        aPane.getChildren().add(view);
        primaryStage.setTitle("Electronic Store Application-"+ store.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
