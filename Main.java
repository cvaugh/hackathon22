package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		HBox buttons = buildButtons();
		HBox displayLists = buildDisplay();
		
		VBox root = new VBox();
		root.getStyleClass().add("root");
		root.getChildren().addAll(buttons, displayLists);
		return root;
	}

	private HBox buildDisplay() {
		HBox display = new HBox();
		VBox vBoxWaitingPatients = buildListOfWaitingPatients();
		VBox vBoxFreeRooms = buildListOfFreeRooms();
//		VBox vBoxSortedRooms = buildListOfSortedRooms();
		display.getChildren().addAll(vBoxWaitingPatients, vBoxFreeRooms);
		
		return display;
	}

	private VBox buildListOfSortedRooms() {
		lvwSortedRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwSortedRooms.setPrefHeight(150);
		lvwSortedRooms.setPrefWidth(120);
		
		List<Room> rooms = azaleaHealth.getRooms();
		
		for(Room r: rooms) {
			lvwFreeRooms.getItems().add(r.toString());
		}
		
		VBox display = new VBox();
		display.getStyleClass().add("list");
		display.getChildren().add(lvwSortedRooms);
		
		return display;
	}

	private VBox buildListOfFreeRooms() {
		lvwFreeRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwFreeRooms.setPrefHeight(150);
		lvwFreeRooms.setPrefWidth(200);
		
		ArrayList<Room> freeRooms = azaleaHealth.getFreeRooms();
		
		for(Room r: freeRooms) {
			lvwFreeRooms.getItems().add(r.toString());
		}
		
		VBox display = new VBox();
		display.getStyleClass().add("list");
		display.getChildren().add(lvwFreeRooms);
		
		return display;
	}

	private VBox buildListOfWaitingPatients() {
		lvwWaitingPatients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwWaitingPatients.setPrefHeight(150);
		lvwWaitingPatients.setPrefWidth(200);
		
		List<Patient> waiting = azaleaHealth.getPatients();
		System.out.println(waiting);
		for(Patient p: waiting) {
			if(p.getStatus()== PatientStatus.WAITING)
				lvwWaitingPatients.getItems().add(p.toString());
		}
		
		VBox display = new VBox();
		display.getStyleClass().add("list");
		display.getChildren().add(lvwWaitingPatients);
		
		return display;
	}
	
	

	private HBox buildButtons() {
		HBox buttonsBox = new HBox();
		buttonsBox.getStyleClass().add("buttons_box");
		
		btnAddPatient = new Button("Add Patient");
		btnAddPatient.setOnAction(new CreateAddPatientEventHandler());
		
		
		btnMoveUp = new Button("Move Up");
		btnMoveUp.setOnAction(new CreateMoveUpEventHandler());
		
		btnMoveDown = new Button("MoveDown");
		btnMoveDown.setOnAction(new CreateMoveDownEventHandler());
		
		buttonsBox.getChildren().addAll(btnAddPatient, btnMoveUp, btnMoveDown);
		
		return buttonsBox;
	}
	
	
	public class CreateAddPatientEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			int patientID = azaleaHealth.addPatient();
			List<Patient> patients = azaleaHealth.getPatients();
			Patient patient = null;
			
			for(Patient p: patients) {
				if(p.getId() == patientID)
					patient = p;
			}
			
			if(patient != null) {
				System.out.println("Adding Patient...");
				lvwWaitingPatients.getItems().add(patient.toString());
			}
		}
		
	}
	
	public class CreateMoveUpEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			int selected = lvwWaitingPatients.getSelectionModel().getSelectedIndex();
			String sel = lvwWaitingPatients.getSelectionModel().getSelectedItem();
			
			if(selected > 0) {
				lvwWaitingPatients.getItems().add(selected-1,sel);
				lvwWaitingPatients.getItems().remove(selected+1);
			}

		}
		
	}
	
	public class CreateMoveDownEventHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			int selected = lvwWaitingPatients.getSelectionModel().getSelectedIndex();
			String sel = lvwWaitingPatients.getSelectionModel().getSelectedItem();
			
			if(selected < lvwWaitingPatients.getItems().size()-1) {
				lvwWaitingPatients.getItems().add(selected+2,sel);
				lvwWaitingPatients.getItems().remove(selected);
			}

		}
		
	}


	public static void main(String[] args) {
		launch(args);
	}
	
	private Hospital createHospital() {
		Hospital azaleaHealth = new Hospital();
		azaleaHealth.addPatient();		
		azaleaHealth.addRoom(100);
		azaleaHealth.addRoom(101);
		azaleaHealth.addRoom(102);
		return azaleaHealth;
	}
}
