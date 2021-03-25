import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public TextField textField_name, textField_add, textField_subtract;
    public Button btn_getName, btn_add, btn_subtract;
    public Label label_total;
    public double total = 0;

    public void printName(ActionEvent actionEvent) {
        String name = textField_name.getText();
        System.out.println(name);
    }

    public void add(ActionEvent actionEvent) {
        try {
            double num = Double.parseDouble(textField_add.getText());
            total += num;
            label_total.setText(Double.toString(total));
            textField_add.clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void subtract(ActionEvent actionEvent) {
        try {
            double num = Double.parseDouble(textField_subtract.getText());
            total -= num;
            label_total.setText(Double.toString(total));
            textField_subtract.clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
