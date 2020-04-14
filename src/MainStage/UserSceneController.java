package MainStage;

import com.app.DBConnection;
import com.app.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSceneController {
    private Main main;
    private Scene mainScene;

    @FXML
    private int accountID;
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
    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> transactionCodeColumn;
    @FXML
    private TableColumn<Transaction, String> transactionDateColumn;
    @FXML
    private TableColumn<Transaction, String> transactionAmountColumn;
    @FXML
    private TableColumn<Transaction, String> transactionDescriptionColumn;

    public void initialize() {
        transactionCodeColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("ID"));
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Amount"));
        transactionDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Description"));

        updateTransactions();
    }

    public void updateTransactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        try {
            ResultSet transactionsQuery = DBConnection.query(String.format("SELECT * FROM transactions WHERE AccountID = '%s'", accountID));
            while (transactionsQuery.next()) {
                transactions.add(new Transaction(transactionsQuery.getString("ID"),
                        transactionsQuery.getString("Date"),
                        transactionsQuery.getString("Amount"),
                        transactionsQuery.getString("Description")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        transactionsTable.setItems(transactions);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setAccountID(int id) {
        accountID = id;
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
