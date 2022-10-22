package ver1;

public class HospitalTest {

	public static void main(String[] args) {
		test1();

	}
	
	public void test1() {
		Hospital h = makeHospital();
		System.out.println(h);
	}
	
	public Hospital makeHospital() {
		Hospital h = new Hospital();
		h.addRoom(1);
		h.addRoom(2);
		h.addRoom(3);
		return h;
	}

}
