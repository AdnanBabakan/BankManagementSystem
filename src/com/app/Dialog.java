package com.app;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

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
}
