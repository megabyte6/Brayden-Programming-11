import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    public TextField textField_productName, textField_quantity, textField_cost;
    public ListView<Product> listView_productList = new ListView<Product>();
    public Label label_name, label_quantity, label_cost;
    public Button btn_purchase;

    public void addProduct(ActionEvent actionEvent) {
        Product temp = new Product(textField_productName.getText(),
                Integer.parseInt(textField_quantity.getText()),
                Double.parseDouble(textField_cost.getText()));
        listView_productList.getItems().add(temp);
        textField_productName.clear();
        textField_quantity.clear();
        textField_cost.clear();
        btn_purchase.setDisable(true);
    }

    public void displayProductInfo(MouseEvent mouseEvent) {
        Product temp = listView_productList.getSelectionModel().getSelectedItem();
        label_name.setText(temp.getName());
        label_quantity.setText(Integer.toString(temp.getQuantity()));
        label_cost.setText("$" + Double.toString(temp.getCost()));
        btn_purchase.setDisable(false);
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
