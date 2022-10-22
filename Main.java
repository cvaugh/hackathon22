package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	protected Button btnAddPatient, btnMoveUp, btnMoveDown, btnAddRoom;
	protected TextArea txaMessage;
	protected ListView<String> lvwWaitingPatients = new ListView<>();
	protected ListView<String> lvwFreeRooms = new ListView<>();
	protected ListView<String> lvwSortedRooms = new ListView<>();
	protected Hospital azaleaHealth = createHospital();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = buildGUI();
			Scene scene = new Scene(root,750,590);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Pane buildGUI() {
		VBox buttons = buildButtons();
		HBox displayLists = buildDisplay();
		
		VBox root = new VBox();
		root.getStyleClass().add("root");
		return null;
	}

	private HBox buildDisplay() {
		VBox vBoxWaitingPatients = buildListOfWaitingPatients();
		VBox vBoxFreeRooms = buildListOfFreeRooms();
		VBox vBoxSortedRooms = buildListOfSortedRooms();
		return null;
	}

	private VBox buildListOfSortedRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	private VBox buildListOfFreeRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	private VBox buildListOfWaitingPatients() {
		lvwWaitingPatients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwWaitingPatients.setPrefHeight(150);
		lvwWaitingPatients.setPrefWidth(120);
		
		ArrayList<Patient> waiting = azaleaHealth.getWaiting();
		
		VBox display = new VBox();
		display.getStyleClass().add("list");
		
		return display;
	}

	private VBox buildButtons() {
		VBox buttonsBox = new VBox();
		buttonsBox.getStyleClass().add("buttons_box");
		
		btnAddPatient = new Button("Add Patient");
		
		
		btnMoveUp = new Button("Move Up");
		
		
		btnMoveDown = new Button("MoveDown");
		
		buttonsBox.getChildren().addAll(btnAddPatient, btnMoveUp, btnMoveDown);
		
		return buttonsBox;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private Hospital createHospital() {
		Hospital azaleaHealth = new Hospital();
		
		
		
		return null;
	}
}
