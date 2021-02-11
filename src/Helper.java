import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Helper {

	// creating admin and secretary users
	public static void CreateUsers() throws IOException {
		User user1 = new User("tester", "admin", "root", "admin");
		User user2 = new User("barney", "barney", "stinson", "secretary");
		User user3 = new User("ted", "ted", "mosby", "secretary");
		User user4 = new User("robin", "robin", "letmein1", "secretary");
		User user5 = new User("marshall", "marshall", "letmein", "secretary");

		FileOutputStream fos = new FileOutputStream("users.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(user1);
		oos.writeObject(user2);
		oos.writeObject(user3);
		oos.writeObject(user4);
		oos.writeObject(user5);
		oos.close();
	}
}
