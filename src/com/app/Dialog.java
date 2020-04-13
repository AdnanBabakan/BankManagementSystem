package com.app;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Dialog {
    public static void show(String type, String message) {
        Alert.AlertType alertType;

        switch (type) {
            case "error":
                alertType = Alert.AlertType.ERROR;
                break;
            case "information":
                alertType = Alert.AlertType.INFORMATION;
                break;
            case "warning":
            default:
                alertType = Alert.AlertType.WARNING;
                break;
        }

        Alert alert = new Alert(alertType);
        alert.setTitle("خطا");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        ButtonType closeButton = new ButtonType("تمام", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(closeButton);

        alert.show();
    }
}
