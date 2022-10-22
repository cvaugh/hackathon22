package ver1;

public class Patient {
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
