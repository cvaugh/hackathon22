package ver1;

public class Patient implements Comparable<Patient> {
    protected int id;
    protected PatientStatus status;

    public Patient(int id) {
        this.id = id;
        status = PatientStatus.WAITING;
    }

    public int getId() {
        return id;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public int compareTo(Patient p) {
        int a = status.ordinal();
        int b = p.status.ordinal();

        if(a == b) {
            return this.id - p.id;
        }
        return status.ordinal() - p.status.ordinal();
    }

    public void setStatus(PatientStatus status) {
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
