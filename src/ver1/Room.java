package ver1;

public class Room implements Comparable<Room>{
    private int roomNumber;
    private boolean isFree;
    private Patient p = null;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        isFree = true;
    }

    public boolean addPatient(Patient readyPatient) {
        if(isFree) {
            p = readyPatient;
            setFree(false);
            return true;
        }
        return false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Patient getPatient() {
        return p;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean state) {
        isFree = state;
    }

    public String getRoomStatus() {
        if(isFree)
            return "Room is Free";
        else {
            return String.format("Room is not free; %s", p.toString());
        }
    }
    
    public int compareTo(Room r) {
    	if (!(isFree && r.isFree)) {
    		return this.getPatient().compareTo(r.getPatient());
    	}
    	return this.roomNumber = r.roomNumber;
    		
    }

    @Override
    public String toString() {
        String output = "Room " + roomNumber;

        if(isFree)
            output += " is free.";
        else {
            output += " has " + p.toString();
        }

        return output;
    }

}
