package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    public TextField textField_productName;
    public TextField textField_quantity;
    public TextField textField_cost;
    public ListView<Product> listView_productList = new ListView<>();
    public Label label_name;
    public Label label_quantity;
    public Label label_cost;
    public Button btn_purchase;

    public void addProduct(ActionEvent actionEvent) {
        Product temp = new Product(
                textField_productName.getText(),
                Integer.parseInt(textField_quantity.getText()),
                Double.parseDouble(textField_cost.getText()));
        listView_productList.getItems().add(temp);
        textField_productName.clear();
        textField_cost.clear();
        textField_quantity.clear();
    }

    public void displayProductInfo(MouseEvent mouseEvent) {
        Product temp = listView_productList.getSelectionModel().getSelectedItem();
        label_cost.setText("$" + Double.toString(temp.getCost()));
        label_name.setText(temp.name);
        label_quantity.setText(Integer.toString(temp.getQuantity()));
    }

    public void purchaseProduct(ActionEvent actionEvent) {
        Product temp = listView_productList.getSelectionModel().getSelectedItem();
        if (temp.getQuantity() < 1) {
            btn_purchase.setDisable(true);
        } else {
            if (temp.getQuantity() == 1) {
                btn_purchase.setDisable(true);
            }
            temp.purchase();
            label_quantity.setText(Integer.toString(temp.getQuantity()));
        }
    }
}
