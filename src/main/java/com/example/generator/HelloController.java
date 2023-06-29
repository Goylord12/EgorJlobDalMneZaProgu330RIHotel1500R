package com.example.generator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static javax.swing.JOptionPane.*;

public class HelloController {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()";

    @FXML
    private Button ButtonCopy;

    @FXML
    private Button ButtonGenerator;

    @FXML
    private CheckBox CheckBoxLowercase;

    @FXML
    private CheckBox CheckBoxNumbers;

    @FXML
    private CheckBox CheckBoxSymbols;

    @FXML
    private CheckBox CheckBoxUppercase;

    @FXML
    private Slider SliderLowercase;

    @FXML
    private Slider SliderNumbers;

    @FXML
    private Slider SliderSymbols;

    @FXML
    private Slider SliderUppercase;

    @FXML
    private TextField Text;

    @FXML
    private TextField TextFieldLengthPassword;

    @FXML
    void ClickButtonCopy(ActionEvent event) {
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(Text.getText());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }

    @FXML
    void ClickButtonGenerator(ActionEvent event) {
        if (CheckBoxUppercase.isSelected() || CheckBoxLowercase.isSelected()
                || CheckBoxNumbers.isSelected() || CheckBoxUppercase.isSelected()) {
            if(SliderSymbols.getValue() != 0 && CheckBoxSymbols.isSelected() == true || SliderNumbers.getValue() != 0 && CheckBoxNumbers.isSelected() == true || SliderLowercase.getValue() != 0 && CheckBoxLowercase.isSelected() == true || SliderUppercase.getValue() != 0 && CheckBoxUppercase.isSelected() == true ){

            }
            generatePassword();
        }

    }
    @FXML
    void DragDetectedLowercase(MouseEvent event) {
        updateSliders();
    }

    @FXML
    void DragDetectedNumbers(MouseEvent event) {
        updateSliders();
    }

    @FXML
    void DragDetectedSymbols(MouseEvent event) {
        updateSliders();
    }

    @FXML
    void DragDetectedUppercase(MouseEvent event) {
        updateSliders();
    }
    @FXML
    private void initialize() {
        SliderUppercase.valueProperty().addListener((observable, oldValue, newValue) -> {
           updateSliders();
        });
        SliderLowercase.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateSliders();
        });
        SliderNumbers.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateSliders();
        });
        SliderSymbols.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateSliders();
        });
    }
    private void initData() {}

    private void generatePassword() {
        int totalLength = 0;
        try {
            totalLength = Integer.parseInt(TextFieldLengthPassword.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Напишите цифрами длину!");
            e.printStackTrace();
        }
        int uppercasePercentage = (int) SliderUppercase.getValue();
        int lowercasePercentage = (int) SliderLowercase.getValue();
        int numbersPercentage = (int) SliderNumbers.getValue();
        int symbolPercentage = (int) SliderSymbols.getValue();
        int uppercaseCount = (int) Math.ceil(totalLength * uppercasePercentage / 100.0);
        int lowercaseCount = (int) Math.ceil(totalLength * lowercasePercentage / 100.0);
        int numbersCount = (int) Math.ceil(totalLength * numbersPercentage / 100.0);
        int symbolsCount = (int) Math.ceil(totalLength * symbolPercentage / 100.0);
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;
        int symbolCounter = 0;
        int numbersCounter = 0;

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        while (password.length() < totalLength) {
            int number = ThreadLocalRandom.current().nextInt(1, 5);
            switch (number){
                case 1:{
                    if (CheckBoxUppercase.isSelected() && uppercaseCounter < uppercaseCount) {
                        uppercaseCounter += 1;
                        password.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));
                    }
                    break;
                }
                case 2: {
                    if (CheckBoxLowercase.isSelected() && lowercaseCounter < lowercaseCount) {
                        lowercaseCounter += 1;
                        password.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));
                    }
                    break;
                }
                case 3: {
                    if (CheckBoxNumbers.isSelected() && numbersCounter < numbersCount) {
                        numbersCounter += 1;
                        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
                    }
                    break;
                }
                case 4: {
                    if (CheckBoxSymbols.isSelected() && symbolCounter < symbolsCount) {
                        symbolCounter += 1;
                        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
                    }
                    break;
                }
            }

        }
        Text.setText(password.toString());
    }

//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }

    private void updateSliders() {
        double totalPercentage = SliderUppercase.getValue() + SliderLowercase.getValue() + SliderNumbers.getValue() + SliderSymbols.getValue();

        double maxPercentage = 100.0;
        double remainingPercentage = maxPercentage - totalPercentage;

        SliderUppercase.setMax(remainingPercentage + SliderUppercase.getValue());
        SliderLowercase.setMax(remainingPercentage + SliderLowercase.getValue());
        SliderNumbers.setMax(remainingPercentage + SliderNumbers.getValue());
        SliderSymbols.setMax(remainingPercentage + SliderSymbols.getValue());
    }
}