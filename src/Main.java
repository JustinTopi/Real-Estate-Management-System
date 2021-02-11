import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main{

	
	public static void main(String[] args) {
		Model model = new Model();
		LoginController controller = new LoginController(model);
		LoginView loginView = new LoginView(controller);

		// run this try catch to create users if any file is broke
		// just make sure to remove the multiline comment and set them back again
		/*try {
			Helper.CreateUsers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
