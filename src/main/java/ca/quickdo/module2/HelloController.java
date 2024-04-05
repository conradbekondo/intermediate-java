package ca.quickdo.module2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloController {

    @FXML
    private Label lblSelectedFile;

    @FXML
    private Label lblFileDescription;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onBtnPickFileClicked() {
        var fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        var chosenFile = fileChooser.showOpenDialog(null);
        if (chosenFile == null) {
            lblSelectedFile.setText("");
            return;
        }
        lblSelectedFile.setText(chosenFile.getName());
//        javafx.scene.control.Alert
        var stringBuilder = new StringBuilder();
        final var outputPath = Paths.get(System.getProperty("user.home"), "Desktop", "output.txt");

        try(var stream = new FileOutputStream(outputPath.toString())) { // C:\Users\Conrad\Desktop\output.txt
            final var fileSize = Files.readAllBytes(Paths.get(chosenFile.getPath())).length;
            stringBuilder.append("File Name: ").append(chosenFile.getName()).append("\n");
            stringBuilder.append("File Path: ").append(chosenFile.getAbsolutePath()).append("\n");
            stringBuilder.append("File Size: ").append(fileSize).append(" bytes").append("\n");

            stream.write(stringBuilder.toString().getBytes());
            stream.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            lblSelectedFile.setText("");
            lblFileDescription.setText("");
            return;
        }
        lblFileDescription.setText(stringBuilder.toString());
    }

    @FXML
    private void onBtnPickFileClicked2() {
        try {
            var fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            var chosenFile = fileChooser.showOpenDialog(null);
            lblSelectedFile.setText(chosenFile.getName());
        } catch (NullPointerException ex) {
            lblSelectedFile.setText("");
            System.err.println("An error occurred while choosing the file.");
        }
    }
}