package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import worker.IliasStarter;

public class LoginWindow {

	private final Container c;
	private final JPanel south, left, right;
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private final JLabel nameLabel, passwordLabel, icon;
	private final JFrame loginDialog;

	public LoginWindow() {
		loginDialog = new JFrame();
		loginDialog.setSize(610, 160);
		loginDialog.setTitle("Anmeldung");
		loginDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginDialog.setResizable(false);
		loginDialog.setLocationRelativeTo(null);

		Image image = new ImageFactory().createImage("image.png");
		loginDialog.setIconImage(image);
		icon = new JLabel(new ImageIcon(getClass().getResource("iliasLogo.jpg")));

		c = loginDialog.getContentPane();
		c.setLayout(new BorderLayout());

		south = new JPanel(new BorderLayout(17, 1));
		left = new JPanel(new GridLayout(2, 1, 1, 5));
		right = new JPanel(new GridLayout(2, 1, 1, 5));

		usernameField = new JTextField();
		passwordField = new JPasswordField();
		passwordField.addActionListener(new LoginProvider());
		nameLabel = new JLabel("Benutzererkennung: ");
		passwordLabel = new JLabel("Passwort: ");

		DesignerFactory.designPanel(left, 1, 1, 1, 1);
		DesignerFactory.designPanel(south, 3, 14, 3, 1);
		DesignerFactory.designPanel(right, 1, 1, 1, 250);

		DesignerFactory.designField(usernameField);
		DesignerFactory.designField(passwordField);

		DesignerFactory.designLabel(nameLabel);
		DesignerFactory.designLabel(passwordLabel);

		south.add(left, BorderLayout.WEST);
		south.add(right, BorderLayout.CENTER);

		left.add(nameLabel);
		left.add(passwordLabel);
		right.add(usernameField);
		right.add(passwordField);

		c.add(icon, BorderLayout.NORTH);
		c.add(south, BorderLayout.CENTER);

		loginDialog.setVisible(true);
	}

	public class LoginProvider implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = usernameField.getText();
			final boolean validUsername = username.length() != 5 || !username.startsWith("u");
			if (validUsername) {
				JOptionPane.showMessageDialog(null, "ung�ltiger Benutzername", null, JOptionPane.ERROR_MESSAGE);
				usernameField.requestFocus();
				usernameField.selectAll();
			} else {
				String password = "";
				char pass[] = passwordField.getPassword();
				if (pass.length < 1) {
					JOptionPane.showMessageDialog(null, "ung�ltiges Passwort", null, JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					passwordField.selectAll();
				}
				for (int i = 0; i < pass.length; i++) {
					password = password + pass[i];
				}
				loginDialog.setVisible(false);
				new Thread(new IliasStarter(username, password)).start();
			}
		}
	}
}