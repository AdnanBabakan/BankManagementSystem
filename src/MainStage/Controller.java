package MainStage;

import com.app.BankAccount;
import com.app.DBConnection;
import com.app.Dialog;
import com.app.MD5;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @FXML
    private TextField loginNationalID;
    @FXML
    private TextField loginPassword;

    private Main main;
    private Scene userScene;
    private UserSceneController userSceneController;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setUserSceneReference(Scene userScene) {
        this.userScene = userScene;
    }

    public void setUserSceneControllerReference(UserSceneController userSceneController) {
        this.userSceneController = userSceneController;
    }

    public void signUpButtonAction(MouseEvent mouseEvent) {
        if (!(signUpFirstName.getText().trim().isEmpty()
                || signUpLastName.getText().trim().isEmpty()
                || signUpNationalID.getText().trim().isEmpty()
                || signUpPassword.getText().isEmpty()
                || signUpPasswordRepeat.getText().isEmpty()
                || signUpBalance.getText().trim().isEmpty())) {
            if (signUpPassword.getText().equals(signUpPasswordRepeat.getText())) {
                try {
                    ResultSet accounts = DBConnection.query(String.format("SELECT * FROM accounts WHERE NationalID = %s", signUpNationalID.getText()));
                    int accountsCount = 0;
                    while (accounts.next()) {
                        accountsCount++;
                    }

                    if (accountsCount == 0) {
                        DBConnection.run(String.format("INSERT INTO accounts (AccountNumber, FirstName, LastName, NationalID, Password, Balance) VALUES (%s, '%s', '%s', %s, '%s', %s)",
                                BankAccount.generateAccountNumber(),
                                signUpFirstName.getText(),
                                signUpLastName.getText(),
                                signUpNationalID.getText(),
                                MD5.getMd5(signUpPassword.getText()),
                                signUpBalance.getText()
                        ));

                        ResultSet thisAccount = DBConnection.query(String.format("SELECT ID FROM accounts WHERE NationalID = '%s'", signUpNationalID.getText()));

                        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
                        Date date = new Date();

                        while (thisAccount.next()) {
                            DBConnection.run(String.format("INSERT INTO transactions (AccountID, Date, Amount, Description) VALUES (%s, '%s', %s, '%s')",
                                    thisAccount.getInt("ID"),
                                    df.format(date),
                                    signUpBalance.getText(),
                                    "افتتاح حساب"
                            ));
                        }

                        signUpFirstName.setText("");
                        signUpLastName.setText("");
                        signUpNationalID.setText("");
                        signUpPassword.setText("");
                        signUpPasswordRepeat.setText("");
                        signUpBalance.setText("");

                        Dialog.show("information", "حساب کاربری با موفقیت ایجاد شد. اکنون میتوانید وارد شوید.");
                    } else {
                        Dialog.show("error", "یک حساب با این کد ملی از قبل وجود دارد!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Dialog.show("error", "رمز عبور با تکرار آن یکسان نیست!");
            }
        } else {
            Dialog.show("error", "تمام فیلدها اجباری هستند!");
        }
    }

    public void loginButtonAction(MouseEvent mouseEvent) {
        if (!(loginNationalID.getText().trim().isEmpty()
                || loginPassword.getText().isEmpty())) {
            try {
                ResultSet account = DBConnection.query(String.format("SELECT * FROM accounts WHERE NationalID = '%s' AND Password = '%s'",
                        loginNationalID.getText(),
                        MD5.getMd5(loginPassword.getText())
                ));
                boolean isAccountTrue = false;
                int accountID = 0;
                String accountNumber = null;
                String accountFirstName = null;
                String accountLastName = null;
                String accountNationalID = null;
                String accountBalance = null;
                while (account.next()) {
                    isAccountTrue = true;
                    accountID = account.getInt("ID");
                    accountNumber = account.getString("AccountNumber");
                    accountFirstName = account.getString("FirstName");
                    accountLastName = account.getString("LastName");
                    accountNationalID = account.getString("NationalID");
                    accountBalance = account.getString("Balance");

                }

                if (isAccountTrue) {
                    loginNationalID.setText("");
                    loginPassword.setText("");

                    userSceneController.setAccountID(accountID);
                    userSceneController.setAccountNumber(accountNumber);
                    userSceneController.setAccountFirstName(accountFirstName);
                    userSceneController.setAccountLastName(accountLastName);
                    userSceneController.setAccountNationalID(accountNationalID);
                    userSceneController.setAccountBalance(accountBalance);

                    userSceneController.initialize();
                    main.setScene(userScene);
                } else {
                    Dialog.show("error", "حسابی با این اطلاعات یافت نشد!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Dialog.show("error", "ةمام فیلد ها اجباری هستند!");
        }
    }
}
