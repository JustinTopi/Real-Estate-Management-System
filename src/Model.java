import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Model {

	// user login
	public User userCanLogin(String username, String password) {
		try {
			FileInputStream fis = new FileInputStream("users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			User obj = null;

			while ((obj = (User) ois.readObject()) != null) {

				if (obj.getUsername().equals(username) && obj.getPassword().equals(password)) {
					ois.close();
					return obj;
				}
			}
			ois.close();

		} catch (EOFException e) {
			// do nothing, since it's expected.
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
