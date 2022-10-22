package ver1;

public class Patient implements Comparable<Patient>{
	protected int id;
	protected String status;
	
	public Patient(int id) {
		this.id = id;
		status = "Waiting";
	}
	
	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
	
	public int compareTo(Patient p) {
		int stat, stat2;
		switch (status) {
		case "Waiting": stat=0; break;
		case "Ready": stat=1; break;
		case "On hold": stat=2; break;
		case "In-progress": stat=3; break;
		case "Checked out": stat=4; break;
		}
		switch (p.getStatus()) {
		case "Waiting": stat2=0; break;
		case "Ready": stat2=1; break;
		case "On hold": stat2=2; break;
		case "In-progress": stat2=3; break;
		case "Checked out": stat2=4; break;
		}
		if (stat==stat2) {
			return this.id - p.id;
		}
		return stat-stat2;
	}

	public void setStatus(String status) {
		status = status.toUpperCase();
		switch (status.charAt(0)) {
		case 'W': this.status = "Waiting";
				break;
		case 'R': this.status = "Ready";
				break;
		case 'I': this.status = "In-progress";
				break;
		case 'O': this.status = "On hold";
				break;
		case 'C': this.status = "Checked out";
				break;

		}
	}

	@Override
	public String toString() {
		return "Patient id: " + id + ", status: " + status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Patient other = (Patient) obj;
		return id == other.id;
	}

	

}
