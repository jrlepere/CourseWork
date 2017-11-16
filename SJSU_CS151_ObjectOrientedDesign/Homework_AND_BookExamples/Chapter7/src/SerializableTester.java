import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class SerializableTester {

	public static void main(String[] args) {
		
		Employee[] staff = new Employee[2];
		staff[0] = new Employee("Jake Lepere", 40000, Department.SALES);
		staff[1] = new Employee("John", 10000, Department.ENGINEERING);
		
		
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream("staff.dat"));
			out.writeObject(staff);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("staff.dat"));
			Employee[] theStaff = (Employee[]) in.readObject();
			System.out.println(Arrays.toString(theStaff));
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
