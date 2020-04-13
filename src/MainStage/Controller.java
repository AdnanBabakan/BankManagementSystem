package MainStage;

import com.app.DBConnection;
import com.app.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    @FXML
    private Button closeButton;
    @FXML
    private TextField signUpFirstName;
    @FXML
    private TextField signUpLastName;
    @FXML
    private TextField signUpNationalID;
    @FXML
    private TextField signUpPassword;
    @FXML
    private TextField signUpPasswordRepeat;
    @FXML
    private TextField signUpBalance;

    public void closeButtonAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void signUpButtonAction(MouseEvent mouseEvent) {
        try {
            ResultSet accounts = DBConnection.query(String.format("SELECT * FROM accounts WHERE NationalID = %s", signUpNationalID.getText()));
            int accountsCount = 0;
            while (accounts.next()) {
                accountsCount++;
            }

            if (accountsCount == 0) {
                DBConnection.run(String.format("INSERT INTO accounts (FirstName, LastName, NationalID, Password, Balance) VALUES ('%s', '%s', %s, '%s', %s)",
                        signUpFirstName.getText(),
                        signUpLastName.getText(),
                        signUpNationalID.getText(),
                        signUpPassword.getText(),
                        signUpBalance.getText()
                ));
            } else {
                Dialog.show("error", "یک حساب با این کد ملی از قبل وجود دارد!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
