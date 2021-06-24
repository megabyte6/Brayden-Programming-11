package com.github.megabyte6.utils.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputBox {

    protected String msg, title, defaultText = "";
    protected int height = 200;
    protected int width = 300;
    protected Insets padding = new Insets(30);
    
    protected Stage window = new Stage();
    private Label text = new Label();
    private TextField input = new TextField();
    private Button okButton = new Button("OK");
    private VBox rootLayout = new VBox(15, text, input, okButton);
    
    public InputBox(String message, String title) {
        this.msg = message;
        this.title = title;

        this.text.setWrapText(true);

        this.okButton.setPrefWidth(150);
        this.okButton.setOnAction(e -> {
            this.window.close();
        });

        this.rootLayout.setAlignment(Pos.CENTER);
        this.rootLayout.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.ENTER ||
                    key.getCode() == KeyCode.ESCAPE) this.window.close();
        });

        this.window.setScene(new Scene(rootLayout));
    }
    
    public String getResult() {
        return this.input.getText();
    }
    
    public void show() {
        this.text.setText(this.msg);
        this.window.setTitle(this.title);
        this.window.setHeight(this.height);
        this.window.setWidth(this.width);

        if (!this.defaultText.equals("")) {
            this.input = new TextField(this.defaultText);
        }

        this.rootLayout.setPadding(this.padding);

        this.window.showAndWait();
    }

    public void close() {
        this.window.close();
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPadding(Insets arg0) {
        this.padding = arg0;
    }

    public void setDefaultText(String text) {
        this.defaultText = text;
    }

}
