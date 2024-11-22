package com.example.guiassignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Form extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initial Welcome Window
        Stage welcomeStage = new Stage();

        // Welcome Message Layout
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setPadding(new Insets(30));
        welcomeLayout.setAlignment(Pos.CENTER);

        Label welcomeMessage = new Label("Welcome to the COMSATS Data Entry System!");
        welcomeMessage.setFont(new Font("Arial", 20));
        welcomeMessage.setTextFill(Color.DARKBLUE);

        Button startButton = new Button("Start Registration");
        startButton.setFont(new Font("Arial", 16));

        // Event handler for the Start button
        startButton.setOnAction(e -> {
            welcomeStage.close(); // Close the welcome window
            openMainForm(primaryStage); // Open the main form
        });

        welcomeLayout.getChildren().addAll(welcomeMessage, startButton);

        // Scene for the Welcome Window
        Scene welcomeScene = new Scene(welcomeLayout, 600, 600);
        welcomeStage.setScene(welcomeScene);
        welcomeStage.setTitle("Welcome");
        welcomeStage.show();
    }

    public void openMainForm(Stage stage) {
        final ArrayList<Person> personList = new ArrayList<>();

        // Root layout
        BorderPane root = new BorderPane();

        // Top Banner with Image and Label
        Label bannerLabel = new Label("COMSATS DATA ENTRY FORM!");
        bannerLabel.setFont(new Font("Times New Roman", 24));
        bannerLabel.setTextFill(Color.DARKBLUE);

        Image bannerImage = new Image("file:C:/Users/xehib/Desktop/COMSATS_new_logo.jpg"); // Replace with your file path
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitHeight(80);
        bannerImageView.setPreserveRatio(true);

        HBox bannerBox = new HBox(10, bannerImageView, bannerLabel);
        bannerBox.setAlignment(Pos.CENTER);
        bannerBox.setPadding(new Insets(20));
        root.setTop(bannerBox);

        // Left side: Form fields
        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(20);
        formGrid.setPadding(new Insets(20));

        // Name Field
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font("Arial", 16));
        TextField nameField = new TextField();
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);

        // Father's Name
        Label fatherNameLabel = new Label("Father Name:");
        fatherNameLabel.setFont(new Font("Arial", 16));
        TextField fatherNameField = new TextField();
        formGrid.add(fatherNameLabel, 0, 1);
        formGrid.add(fatherNameField, 1, 1);

        // CNIC
        Label cnicLabel = new Label("CNIC:");
        cnicLabel.setFont(new Font("Arial", 16));
        TextField cnicField = new TextField();
        formGrid.add(cnicLabel, 0, 2);
        formGrid.add(cnicField, 1, 2);

        // Date of Birth
        Label dobLabel = new Label("Date:");
        dobLabel.setFont(new Font("Arial", 16));
        DatePicker dobPicker = new DatePicker();
        formGrid.add(dobLabel, 0, 3);
        formGrid.add(dobPicker, 1, 3);

        // Gender
        Label genderLabel = new Label("Gender:");
        genderLabel.setFont(new Font("Arial", 16));
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, maleRadio, femaleRadio);
        formGrid.add(genderLabel, 0, 4);
        formGrid.add(genderBox, 1, 4);

        // City ComboBox
        Label cityLabel = new Label("City:");
        cityLabel.setFont(new Font("Arial", 16));
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Islamabad", "Lahore", "Karachi", "Quetta", "Vehari", "Peshawar");
        formGrid.add(cityLabel, 0, 5);
        formGrid.add(cityComboBox, 1, 5);

        // Right side: Image upload and preview
        VBox imageBox = new VBox(10);
        imageBox.setPadding(new Insets(20));
        imageBox.setAlignment(Pos.CENTER);

        Label imageLabel = new Label("Image:");
        imageLabel.setFont(new Font("Arial", 16));

        Button fileChooserButton = new Button("Choose Image");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        // FileChooser for image upload
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooserButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            }
        });

        imageBox.getChildren().addAll(imageLabel, fileChooserButton, imageView);

        // Combine Form and Image Sections
        HBox mainContent = new HBox(20, formGrid, imageBox);
        mainContent.setPadding(new Insets(20));
        root.setCenter(mainContent);

        // Save and Exit Buttons
        Button saveButton = new Button("Save");
        saveButton.setFont(new Font("Arial", 16));
        saveButton.setOnAction(e -> {
            // Validate input
            if (nameField.getText().isEmpty() || fatherNameField.getText().isEmpty() || cnicField.getText().isEmpty()
                    || dobPicker.getValue() == null || genderGroup.getSelectedToggle() == null || cityComboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields", ButtonType.OK);
                alert.showAndWait();
            } else {
                personList.add(new Person(nameField.getText(), fatherNameField.getText(), cnicField.getText(),
                        dobPicker.getValue().toString(), ((RadioButton) genderGroup.getSelectedToggle()).getText(),
                        cityComboBox.getValue()));
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, ("User: " + nameField.getText() + " added successfully"), ButtonType.OK);
                successAlert.showAndWait();
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setFont(new Font("Arial", 16));
        exitButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(20, saveButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));
        root.setBottom(buttonBox);

        // Scene and Stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Data Entry Form");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
