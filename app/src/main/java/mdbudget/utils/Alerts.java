package mdbudget.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Alerts {
    public static void errorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

    }

    public static void confirmOrder() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Konfirmasi Aksi");
        alert.setContentText("Apakah Anda yakin ingin melanjutkan?");

        // Menambahkan tombol Cancel dan Confirm
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        ButtonType confirmButton = new ButtonType("Confirm", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(cancelButton, confirmButton);

        // Menampilkan dan menunggu respons pengguna
        alert.showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                // Tindakan jika tombol Confirm ditekan
                System.out.println("Aksi dikonfirmasi");
            } else {
                Alerts.errorMessage("Order Dibatalkan");
                System.out.println("Aksi dibatalkan");
            }
        });
    }

    public static void successMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();

    }
}
