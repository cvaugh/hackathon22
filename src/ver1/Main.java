package ver1;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    protected Button btnNewPatient, btnMoveUp, btnMoveDown, btnAddRoom, btnAddPatient, btnHold, btnCheckout,
            btnInProgress;
    protected TextArea txaMessage;
    protected ListView<Patient> lvwWaitingPatients = new ListView<>();
    protected ListView<Room> lvwFreeRooms = new ListView<>();
    protected ListView<Room> lvwSortedRooms = new ListView<>();
    protected ListView<Patient> lvwCheckedout = new ListView<>();
    protected Hospital azaleaHealth = createHospital();

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = buildGUI();
            Scene scene = new Scene(root, 1200, 590);
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
        VBox vBoxSortedRooms = buildListOfSortedRooms();
        VBox vBoxCheckedout = buildCheckedout();
        display.getChildren().addAll(vBoxWaitingPatients, vBoxFreeRooms, vBoxSortedRooms, vBoxCheckedout);

        return display;
    }

    private VBox buildCheckedout() {
        lvwCheckedout.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwCheckedout.setPrefHeight(150);
        lvwCheckedout.setPrefWidth(800);

        VBox display = new VBox();
        display.getStyleClass().add("list");
        display.getChildren().add(lvwCheckedout);

        return display;
    }

    private VBox buildListOfSortedRooms() {
        lvwSortedRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwSortedRooms.setPrefHeight(150);
        lvwSortedRooms.setPrefWidth(350);

        VBox display = new VBox();
        display.getStyleClass().add("list");
        display.getChildren().add(lvwSortedRooms);

        return display;
    }

    private VBox buildListOfFreeRooms() {
        lvwFreeRooms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwFreeRooms.setPrefHeight(150);
        lvwFreeRooms.setPrefWidth(200);

        List<Room> freeRooms = azaleaHealth.getFreeRooms();

        for(Room r : freeRooms) {
            lvwFreeRooms.getItems().add(r);
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
        for(Patient p : waiting) {
            if(p.getStatus() == PatientStatus.WAITING) lvwWaitingPatients.getItems().add(p);
        }

        VBox display = new VBox();
        display.getStyleClass().add("list");
        display.getChildren().add(lvwWaitingPatients);

        return display;
    }

    private HBox buildButtons() {
        HBox buttonsBox = new HBox();
        buttonsBox.getStyleClass().add("buttons_box");

        btnNewPatient = new Button("New Patient");
        btnNewPatient.setOnAction(new CreateNewPatientEventHandler());

        btnMoveUp = new Button("Move Up");
        btnMoveUp.setOnAction(new CreateMoveUpEventHandler());

        btnMoveDown = new Button("MoveDown");
        btnMoveDown.setOnAction(new CreateMoveDownEventHandler());

        btnAddPatient = new Button("Add Patient");
        btnAddPatient.setOnAction(new CreateAddPatientEventHandler());

        btnHold = new Button("Hold");
        btnHold.setOnAction(new CreateHoldEventHandler());

        btnInProgress = new Button("In-Progress");
        btnInProgress.setOnAction(new CreateInProgressEventHandler());

        btnCheckout = new Button("CheckOut");
        btnCheckout.setOnAction(new CreateCheckoutEventHandler());

        buttonsBox.getChildren().addAll(btnNewPatient, btnMoveUp, btnMoveDown, btnAddPatient, btnHold, btnInProgress,
                btnCheckout);

        return buttonsBox;
    }

    public class CreateNewPatientEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            int patientID = azaleaHealth.addPatient();
            List<Patient> patients = azaleaHealth.getPatients();
            Patient patient = null;

            for(Patient p : patients) {
                if(p.getId() == patientID) patient = p;
            }

            if(patient != null) {
                System.out.println("Adding Patient...");
                lvwWaitingPatients.getItems().add(patient);
            }
        }

    }

    public class CreateMoveUpEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            int selected = lvwWaitingPatients.getSelectionModel().getSelectedIndex();
            Patient sel = lvwWaitingPatients.getSelectionModel().getSelectedItem();

            if(selected > 0) {
                lvwWaitingPatients.getItems().add(selected - 1, sel);
                lvwWaitingPatients.getItems().remove(selected + 1);
            }

        }

    }

    public class CreateMoveDownEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            int selected = lvwWaitingPatients.getSelectionModel().getSelectedIndex();
            Patient sel = lvwWaitingPatients.getSelectionModel().getSelectedItem();

            if(selected < lvwWaitingPatients.getItems().size() - 1) {
                lvwWaitingPatients.getItems().add(selected + 2, sel);
                lvwWaitingPatients.getItems().remove(selected);
            }

        }

    }

    public class CreateAddPatientEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            int selectedWaiting = lvwWaitingPatients.getSelectionModel().getSelectedIndex();
            Patient selWaiting = lvwWaitingPatients.getSelectionModel().getSelectedItem();
            int selectedRoom = lvwFreeRooms.getSelectionModel().getSelectedIndex();
            Room selRoom = lvwFreeRooms.getSelectionModel().getSelectedItem();

            List<Patient> patients = azaleaHealth.getPatients();
            List<Room> rooms = azaleaHealth.getFreeRooms();

            for(Room r : rooms) {
                if(selRoom.equals(r)) {
                    for(Patient p : patients) {
                        if(selWaiting.equals(p)) {
                            r.addPatient(p);
                            p.setStatus(PatientStatus.READY);
                            lvwSortedRooms.getItems().add(r);
                        }
                    }
                }
            }

            lvwWaitingPatients.getItems().remove(selectedWaiting);
            lvwFreeRooms.getItems().remove(selectedRoom);

        }

    }

    public class CreateHoldEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Room roomSel = lvwSortedRooms.getSelectionModel().getSelectedItem();
            int roomSelected = lvwSortedRooms.getSelectionModel().getSelectedIndex();
            roomSel.getPatient().setStatus(PatientStatus.ON_HOLD);

            lvwSortedRooms.getItems().remove(roomSelected);
            lvwSortedRooms.getItems().add(roomSel);

        }

    }

    public class CreateInProgressEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Room roomSel = lvwSortedRooms.getSelectionModel().getSelectedItem();
            int roomSelected = lvwSortedRooms.getSelectionModel().getSelectedIndex();
            roomSel.getPatient().setStatus(PatientStatus.IN_PROGRESS);

            lvwSortedRooms.getItems().remove(roomSelected);
            lvwSortedRooms.getItems().add(roomSel);

        }

    }

    public class CreateCheckoutEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent arg0) {
            Room roomSel = lvwSortedRooms.getSelectionModel().getSelectedItem();
            int roomSelected = lvwSortedRooms.getSelectionModel().getSelectedIndex();
            roomSel.getPatient().setStatus(PatientStatus.CHECKED_OUT);

            lvwSortedRooms.getItems().remove(roomSelected);
            Patient pat = roomSel.removePatient();
            lvwCheckedout.getItems().add(pat);
            lvwFreeRooms.getItems().add(roomSel);

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
