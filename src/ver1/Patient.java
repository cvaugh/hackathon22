package ver1;

public class Patient implements Comparable<Patient> {
    protected int id;
    protected PatientStatus status;

    public Patient(int id) {
        this.id = id;
        this.status = PatientStatus.WAITING;
    }

    public int getId() {
        return id;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public int compareTo(Patient p) {
        if(status == p.status) {
            return this.id - p.id;
        }
        return status.ordinal() - p.status.ordinal();
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + " (" + status + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Patient) obj).id;
    }

}
