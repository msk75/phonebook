package ir.maktab.finalproject.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class LoginUser {

	private JFrame frame;
	private JTextField usertxt;
	private JPasswordField passtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUser window = new LoginUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUser() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 320, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel userlbl = new JLabel("Usersname:");
		userlbl.setHorizontalAlignment(SwingConstants.LEFT);
		userlbl.setBounds(37, 73, 81, 14);
		frame.getContentPane().add(userlbl);

		JLabel passlbl = new JLabel("Password:");
		passlbl.setHorizontalAlignment(SwingConstants.LEFT);
		passlbl.setBounds(37, 110, 81, 14);
		frame.getContentPane().add(passlbl);

		usertxt = new JTextField();
		usertxt.setBounds(128, 70, 110, 20);
		frame.getContentPane().add(usertxt);
		usertxt.setColumns(10);

		JButton loginbtn = new JButton("LOGIN");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = usertxt.getText();
				String pass = String.valueOf(passtxt.getPassword());
				Client client = ClientBuilder.newClient();
				int ans = client.target(
						"http://localhost:8080/final_webapp/api/users/login?username=" + user + "&password=" + pass)
						.request(MediaType.TEXT_PLAIN).get(new GenericType<Integer>() {
						});
				if (ans != 0) {
					try {

						Call call = new Call();
						call.setVisible(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Your Information Not Current");

				}

			}
		});

		loginbtn.setBounds(108, 164, 89, 23);
		frame.getContentPane().add(loginbtn);

		passtxt = new JPasswordField();
		passtxt.setBounds(128, 107, 110, 20);
		frame.getContentPane().add(passtxt);
	}
}
