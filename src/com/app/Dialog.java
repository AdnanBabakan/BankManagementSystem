package com.app;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Dialog {
    public static void show(String type, String message) {
        Alert.AlertType alertType;
        String title;

        switch (type) {
            case "error":
                alertType = Alert.AlertType.ERROR;
                title = "خطا";
                break;
            case "information":
                alertType = Alert.AlertType.INFORMATION;
                title = "اطلاعیه";
                break;
            case "warning":
            default:
                alertType = Alert.AlertType.WARNING;
                title = "توجه";
                break;
        }

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        ButtonType closeButton = new ButtonType("تمام", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(closeButton);

        alert.show();
    }

    public static String ask(String headerText, String contentText) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("عملیات");
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        dialog.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        ButtonType doneButton = new ButtonType("انجام", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButton = new ButtonType("انصراف", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().setAll(doneButton, closeButton);

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            return result.get();
        } else {
            return "";
        }
    }
}
