import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LoginController implements ActionListener, ChangeListener {
	Model model;
	LoginView view;

	LoginController(Model model) {
		this.model = model;
	}

	public void AddView(LoginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.print(e.getActionCommand().toString());
		String action = e.getActionCommand().toString();
		// if login button is clicked
		// get the username and the password
		// and check if they match with any admin or secretary
		if (action.equals("Login")) {
			String pass = view.GetPassword();
			String user = view.GetUserName();
			User usr = model.userCanLogin(user, pass);
			if (usr != null
					&& (usr.getType().equalsIgnoreCase("admin") || usr.getType().equalsIgnoreCase("secretary"))) {
				ViewController controller = new ViewController(model);
				// ArrayList<Branch> branches = model.loadBranches();
				ArrayList<Branch> branches = new ArrayList<>();
				ViewPanel viewPanel = new ViewPanel(controller, branches, usr,
						usr.getType().equalsIgnoreCase("secretary"));

				view.closeWindow();
			} else {
				JOptionPane.showMessageDialog(null, "Wrong Username or Password");
			}
		}
		// if exit button is clicked system should be closed
		if (action.equals("Exit")) {
			System.exit(0);
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}

}
