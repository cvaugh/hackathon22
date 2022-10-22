package ver1;

public class Patient implements Comparable<Patient> {
    protected int id;
    protected String status;

    public Patient(int id) {
        this.id = id;
        this.status = "WAITING";
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int compareTo(Patient p) {
        int a = order(this.status);
        int b = order(p.status);

        if(a == b) {
            return this.id - p.id;
        }
        return a - b;
    }
    
    private int order(String s) {
    	if (s.equals("WAITING")
    			return 0;
    	if (s.equals("READY")
    			return 1;
    	if (s.equals("IN_PROGRESS")
    			return 2;
    	if (s.equals("ON_HOLD")
    			return 3;
    	return 4;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient id: " + id + ", status: " + status;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        Patient other = (Patient) obj;
        return id == other.id;
    }

}
