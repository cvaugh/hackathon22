package ver1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hospital {
    protected List<Room> rooms;
    protected List<Patient> patients;
    private int nextID = 1;

    public Hospital() {
        this.patients = new List<>();
        this.rooms = new List<>();
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

    public List<Room> getRooms() {
        return rooms;
    }
    
    public List<Room> getFreeRooms(){
    	List<Room> free = new List<>();
    	for (Room r : rooms) {
    		if (r.isFree)
    			free.add(r);
    	}
    	return free;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public int addPatient() {
        Patient p = new Patient(nextID++);
        patients.add(p);
        return p.getId();
    }

    public List<Patient> getWaiting() {
        List<Patient> waiting = new ArrayList<>();
        for(Patient p : patients) {
            if(p.getStatus().equals("WAITING")) waiting.add(p);
        }
        return waiting;
    }

    public List<Patient> getReady() {
        List<Patient> ready = new ArrayList<>();
        for(Patient p : patients) {
            if(p.getStatus().equals("READY") ready.add(p);
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
