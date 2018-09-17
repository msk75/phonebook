package ir.maktab.finalproject.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import ir.maktab.finalproject.entities.Calls;

@SuppressWarnings("serial")
public class Call extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField idtxt;
	private JTextField fnametxt;
	private JTextField lnametxt;
	private JTextField mobiletxt;
	private JTextField phonetxt;
	private JTextField addresstxt;
	private JTextField emailtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Call frame = new Call();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Call() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 418);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.menu);
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 11, 413, 339);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(10, 18, 82, 14);
		contentPane.add(lblId);

		JLabel lblFirstname = new JLabel("FirstName:");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(10, 44, 82, 14);
		contentPane.add(lblFirstname);

		JLabel lblLastname = new JLabel("LastName:");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setBounds(10, 71, 82, 14);
		contentPane.add(lblLastname);

		JLabel lblMobilenumber = new JLabel("MobileNumber:");
		lblMobilenumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMobilenumber.setBounds(0, 96, 92, 14);
		contentPane.add(lblMobilenumber);

		JLabel lblPhonenumber = new JLabel("PhoneNumber:");
		lblPhonenumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhonenumber.setBounds(0, 121, 92, 14);
		contentPane.add(lblPhonenumber);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(10, 171, 82, 14);
		contentPane.add(lblAddress);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 146, 82, 14);
		contentPane.add(lblEmail);

		idtxt = new JTextField();
		idtxt.setBounds(124, 15, 120, 20);
		contentPane.add(idtxt);
		idtxt.setColumns(10);

		fnametxt = new JTextField();
		fnametxt.setBounds(124, 41, 120, 20);
		contentPane.add(fnametxt);
		fnametxt.setColumns(10);

		lnametxt = new JTextField();
		lnametxt.setBounds(124, 68, 120, 20);
		contentPane.add(lnametxt);
		lnametxt.setColumns(10);

		mobiletxt = new JTextField();
		mobiletxt.setBounds(124, 93, 120, 20);
		contentPane.add(mobiletxt);
		mobiletxt.setColumns(10);

		phonetxt = new JTextField();
		phonetxt.setBounds(124, 118, 120, 20);
		contentPane.add(phonetxt);
		phonetxt.setColumns(10);

		addresstxt = new JTextField();
		addresstxt.setBounds(102, 168, 142, 107);
		contentPane.add(addresstxt);
		addresstxt.setColumns(10);

		emailtxt = new JTextField();
		emailtxt.setBounds(124, 143, 120, 20);
		contentPane.add(emailtxt);
		emailtxt.setColumns(10);

		JButton btnAddContact = new JButton("ADD Contact");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calls calls = new Calls();
				calls.setFname(fnametxt.getText());
				calls.setLname(lnametxt.getText());
				calls.setEmail(emailtxt.getText());
				calls.setAddress(addresstxt.getText());
				calls.setMobileNumber(mobiletxt.getText());
				calls.setPhoneNumber(phonetxt.getText());
				Client client = ClientBuilder.newClient();
				client.target("http://localhost:8080/final_webapp/api/calls").request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(calls, MediaType.APPLICATION_JSON));
				try {
					loadData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddContact.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddContact.setForeground(new Color(255, 255, 255));
		btnAddContact.setBackground(new Color(173, 216, 230));
		btnAddContact.setBounds(61, 308, 140, 23);
		contentPane.add(btnAddContact);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(30, 144, 255));
		btnExit.setBounds(61, 345, 140, 23);
		contentPane.add(btnExit);
		loadData();

	}

	private void loadData() throws IOException {
		List<Calls> list = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		list = client.target("http://localhost:8080/final_webapp/api/calls").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Calls>>() {
				});
		DefaultTableModel model = new DefaultTableModel(
				new String[] { "Id", "Firstname", "LastName", "MobileNumber", "PhoneNumber", "Address", "Email" }, 0);
		for (Calls calls : list) {
			model.addRow(new Object[] { calls.getId(), calls.getFname(), calls.getLname(), calls.getMobileNumber(),
					calls.getPhoneNumber(), calls.getAddress(), calls.getEmail() });
		}
		this.table.setModel(model);
	}
}
