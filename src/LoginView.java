import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginView {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwdField;
	private LoginController controller;

	/**
	 * Create the application.
	 */
	public LoginView(LoginController controller) {
		this.controller = controller;
		View();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void View() {
		// login panel
		frame = new JFrame("Real Estate");
		frame.setBounds(100, 100, 390, 500);
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		// Login label with big font and blue color
		JLabel label = new JLabel("LOGIN");
		label.setForeground(new Color(0, 153, 255));
		label.setFont(new Font("Stencil", Font.PLAIN, 70));
		label.setBounds(104, 79, 199, 60);
		frame.getContentPane().add(label);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// username label
		JLabel userNameLbl = new JLabel("");
		userNameLbl.setIcon(new ImageIcon("resources//username.png"));
		userNameLbl.setBounds(49, 191, 45, 41);
		frame.getContentPane().add(userNameLbl);

		// username textfield
		textField = new JTextField();
		textField.setBounds(86, 191, 248, 41);
		textField.setColumns(10);
		frame.getContentPane().add(textField);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// password label
		JLabel lblPassword = new JLabel("");
		lblPassword.setIcon(new ImageIcon("resources//password.png"));
		lblPassword.setBounds(49, 269, 45, 41);
		frame.getContentPane().add(lblPassword);

		// password textfield
		passwdField = new JPasswordField();
		passwdField.setBounds(86, 269, 248, 41);
		frame.getContentPane().add(passwdField);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// login button
		JButton loginBtn = new JButton("Login");
		loginBtn.setIcon(new ImageIcon("resources//sign.png"));
		loginBtn.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBounds(104, 353, 212, 51);
		loginBtn.setBackground(new Color(0, 153, 255));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.addActionListener(controller);
		frame.getContentPane().add(loginBtn);

		frame.setVisible(true);
		frame.invalidate();
		frame.revalidate();
		controller.AddView(this);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// exit button
		JButton exitBtn = new JButton("Exit");
		exitBtn.setIcon(new ImageIcon("resources//exitsys.png"));
		exitBtn.setForeground(Color.WHITE);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setBounds(300, 10, 60, 50);
		exitBtn.addActionListener(controller);
		frame.getContentPane().add(exitBtn);
		frame.setVisible(true);
		frame.invalidate();
		frame.revalidate();
		controller.AddView(this);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

	// function to shutdown the frame panel
	public void closeWindow() {
		frame.dispose();
	}

	public String GetPassword() {
		return passwdField.getText();

	}

	public String GetUserName() {
		return textField.getText();

	}
}
