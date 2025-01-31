package me.logger.Utility.CustomTableCell;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TableCellStyle {

    public static <T> Callback<TableColumn<T, String>, TableCell<T, String>> getStatusCellFactory() {
        return column -> new TableCell<T, String>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);

                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);

                    switch (status) {
                        case "Running":
                            setStyle("-fx-text-fill: green;");
                            break;
                        case "Stopped":
                            setStyle("-fx-text-fill: red;");
                            break;
                        case "Maintenance":
                            setStyle("-fx-text-fill: blue;");
                            break;
                        default:
                            setStyle("-fx-text-fill: black;");
                            break;
                    }
                }
            }
        };
    }

}
