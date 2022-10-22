package ver1;

import java.util.ArrayList;
import java.util.Collection;

public class Hospital {
    protected ArrayList<Room> rooms;
    protected ArrayList<Patient> patients;
    private int nextID = 1;

    public Hospital() {
        this.patients = new ArrayList<>();

    }

    public Hospital(Collection<Room> rooms) {
        this();
        this.rooms.addAll(rooms);
    }

    public boolean addRoom(int rn) {
        Room room = new Room(rn);
        if(rooms.contains(room)) return false;
        rooms.add(room);
        return true;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public int addPatient() {
        Patient p = new Patient(nextID++);
        patients.add(p);
        return p.getId();
    }

    public ArrayList<Patient> getWaiting() {
        ArrayList<Patient> waiting = new ArrayList<>();
        for(Patient p : patients) {
            if(p.getStatus().equals("Waiting")) waiting.add(p);
        }
        return waiting;
    }

    public ArrayList<Patient> getReady() {
        ArrayList<Patient> ready = new ArrayList<>();
        for(Patient p : patients) {
            if(p.getStatus().equals("Ready")) ready.add(p);
        }
        return ready;
    }

    @Override
    public String toString() {
        String s = "Hospital";
        for(Room r : rooms) {
            s += "\n" + r;
        }
        return s;
    }

}
