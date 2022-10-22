package ver1;

public class HospitalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Hospital makeHospital() {
		Hospital h = new Hospital();
		h.addRoom(1);
		h.addRoom(2);
		h.addRoom(3);
		return h;
	}

}
