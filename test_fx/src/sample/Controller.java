package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    TextField my_text;
    String ss;
    public void on_button_clicked(ActionEvent actionEvent) {
        ss= my_text.getText();

        System.out.println(ss);
    }

    public void on_button2_clicked(ActionEvent actionEvent) {
        System.out.println(ss);
    }
}
