package MainStage;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class UserSceneController {
    private Main main;
    private Scene mainScene;

    @FXML
    private Label accountNumber;
    @FXML
    private Label accountFirstName;
    @FXML
    private Label accountLastName;
    @FXML
    private Label accountNationalID;
    @FXML
    private Label accountBalance;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setAccountNumber(String number) {
        accountNumber.setText(number);
    }

    public void setAccountFirstName(String firstName) {
        accountFirstName.setText(firstName);
    }

    public void setAccountLastName(String lastName) {
        accountLastName.setText(lastName);
    }

    public void setAccountNationalID(String nationalID) {
        accountNationalID.setText(nationalID);
    }

    public void setAccountBalance(String balance) {
        accountBalance.setText(balance + " ریال");
    }

    public void exitButtonAction(MouseEvent mouseEvent) {
        main.setScene(mainScene);
    }
}
