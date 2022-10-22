package ver1;

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
		VBox buttons = buildButtons();
		HBox displayLists = buildDisplay();
		
		VBox root = new VBox();
		root.getStyleClass().add("root");
		root.getChildren().addAll(buttons, displayLists);
		return root;
	}

	private HBox buildDisplay() {
		HBox display = new HBox();
		VBox vBoxWaitingPatients = buildListOfWaitingPatients();
//		VBox vBoxFreeRooms = buildListOfFreeRooms();
//		VBox vBoxSortedRooms = buildListOfSortedRooms();
		display.getChildren().add(vBoxWaitingPatients);
		
		return display;
	}

//	private VBox buildListOfSortedRooms() {
//		lvwSortedRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		lvwSortedRooms.setPrefHeight(150);
//		lvwSortedRooms.setPrefWidth(120);
//		
//		ArrayList<Room> rooms = azaleaHealth.getRooms();
//		
//		for(Room r: rooms) {
//			lvwFreeRooms.getItems().add(r.toString());
//		}
//		
//		VBox display = new VBox();
//		display.getStyleClass().add("list");
//		display.getChildren().add(lvwSortedRooms);
//		
//		return display;
//	}

//	private VBox buildListOfFreeRooms() {
//		lvwFreeRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		lvwFreeRooms.setPrefHeight(150);
//		lvwFreeRooms.setPrefWidth(120);
//		
//		ArrayList<Room> freeRooms = azaleaHealth.getFreeRooms();
//		
//		for(Room r: freeRooms) {
//			lvwFreeRooms.getItems().add(r.toString());
//		}
//		
//		VBox display = new VBox();
//		display.getStyleClass().add("list");
//		display.getChildren().add(lvwFreeRooms);
//		
//		return display;
//	}

	private VBox buildListOfWaitingPatients() {
		lvwWaitingPatients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lvwWaitingPatients.setPrefHeight(150);
		lvwWaitingPatients.setPrefWidth(200);
		
		List<Patient> waiting = azaleaHealth.getWaiting();
		int i = 0;
		for(Patient p: waiting) {
			System.out.println(i);
			i++;
			lvwWaitingPatients.getItems().add(p.toString());
		}
		
		VBox display = new VBox();
		display.getStyleClass().add("list");
		display.getChildren().add(lvwWaitingPatients);
		
		return display;
	}
	
	

	private VBox buildButtons() {
		VBox buttonsBox = new VBox();
		buttonsBox.getStyleClass().add("buttons_box");
		
		btnAddPatient = new Button("Add Patient");
		btnAddPatient.setOnAction(new CreateAddPatientEventHandler());
		
		
		btnMoveUp = new Button("Move Up");
		
		
		btnMoveDown = new Button("MoveDown");
		
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

	public static void main(String[] args) {
		launch(args);
	}
	
	private Hospital createHospital() {
		Hospital azaleaHealth = new Hospital();

		return azaleaHealth;
	}
}

