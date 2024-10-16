package frames;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginManagerFrame extends JFrame {

	private JPanel contentPane;
	private JCheckBox loginShowCheckBox;
	private JCheckBox registerShowCheckBox;
	private JTextField loginUsernameTextField;
	private JPasswordField loginPasswordTextField;
	private JTextField registerUsernameTextField;
	private JPasswordField registerPasswordTextField;
	private JPasswordField registerConfirmPasswordTextField;
	
	private Character passwordChar;
	

	public LoginManagerFrame(final Controller ctrl) {
		setTitle("GAS Reviews - Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imgs\\logoIcon.png"));
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 350);
		setLocationRelativeTo(null); /*POSIZIONE CENTRATA*/
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(new Color(20, 92, 174));
		panel.setBounds(0, 0, 353, 321);
		contentPane.add(panel);
		panel.setFocusable(true);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setIcon(new ImageIcon("imgs\\login_button.png"));
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				ctrl.loginEvent(loginUsernameTextField.getText(), loginPasswordTextField.getText());
				
			}
		});
		btnLogin.setBounds(10, 258, 331, 38);
		panel.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Gi\u00E0 registrato?  ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 154, 31);
		panel.add(lblNewLabel);
		
		JLabel lblLogIn = new JLabel("Accedi");
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Ubuntu", Font.BOLD, 24));
		lblLogIn.setBounds(152, 11, 81, 31);
		panel.add(lblLogIn);
		
		JLabel label = new JLabel("________________________");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 24));
		label.setBounds(10, 25, 331, 23);
		panel.add(label);
		
		loginShowCheckBox = new JCheckBox("Mostra");
		loginShowCheckBox.setFont(new Font("Ubuntu", Font.BOLD, 13));
		loginShowCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginShowCheckBox.isSelected()) {
					loginPasswordTextField.setEchoChar((char)0);
				}
				else {
					loginPasswordTextField.setEchoChar(passwordChar);
				}
			}
				
		});
		loginShowCheckBox.setForeground(new Color(255, 255, 255));
		loginShowCheckBox.setBackground(new Color(20, 92, 174));
		loginShowCheckBox.setBounds(86, 125, 168, 23);
		
		panel.add(loginShowCheckBox);
		
		loginUsernameTextField = new JTextField();
		loginUsernameTextField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		loginUsernameTextField.setBounds(10, 87, 331, 38);
		panel.add(loginUsernameTextField);
		loginUsernameTextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblUsername.setBounds(14, 60, 331, 31);
		panel.add(lblUsername);
		
		loginPasswordTextField = new JPasswordField();
		loginPasswordTextField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		loginPasswordTextField.setBounds(10, 151, 331, 38);
		panel.add(loginPasswordTextField);
		passwordChar = loginPasswordTextField.getEchoChar();
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblPassword.setBounds(14, 121, 331, 31);
		panel.add(lblPassword);
		
		JLabel lblHaiDimenticatoLa = new JLabel("<HTML><U>Hai dimenticato la Password?</U></HTML>");
		lblHaiDimenticatoLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ctrl.forgottenPassword();
			}
		});
		lblHaiDimenticatoLa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHaiDimenticatoLa.setForeground(Color.WHITE);
		lblHaiDimenticatoLa.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblHaiDimenticatoLa.setBounds(10, 199, 295, 31);
		panel.add(lblHaiDimenticatoLa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(23, 43 ,72));
		panel_1.setBounds(351, 0, 374, 321);
		contentPane.add(panel_1);
		panel_1.setFocusable(true);
		panel_1.setLayout(null);
		
		JButton btnRegister = new JButton("");
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setIcon(new ImageIcon("imgs\\register_button.png"));
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.createNewUserEvent(registerUsernameTextField.getText(), registerPasswordTextField.getText(), registerConfirmPasswordTextField.getText());
			}
		});
		btnRegister.setBounds(6, 257, 338, 38);
		panel_1.add(btnRegister);
		
		JLabel lblNewUser = new JLabel("Nuovo utente?");
		lblNewUser.setForeground(Color.WHITE);
		lblNewUser.setFont(new Font("Ubuntu", Font.BOLD, 18));
		lblNewUser.setBounds(13, 11, 133, 31);
		panel_1.add(lblNewUser);
		
		JLabel label_2 = new JLabel("________________________");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("SansSerif", Font.BOLD, 24));
		label_2.setBounds(13, 25, 331, 23);
		panel_1.add(label_2);
		
		JLabel lblRegister = new JLabel("Registrati");
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Ubuntu", Font.BOLD, 24));
		lblRegister.setBounds(155, 11, 187, 31);
		panel_1.add(lblRegister);
		
		registerShowCheckBox = new JCheckBox("Mostra");
		registerShowCheckBox.setFont(new Font("Ubuntu", Font.BOLD, 13));
		registerShowCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(registerShowCheckBox.isSelected()) {
					registerPasswordTextField.setEchoChar((char)0);
					registerConfirmPasswordTextField.setEchoChar((char)0);
				}
				else {
					registerPasswordTextField.setEchoChar(passwordChar);
					registerConfirmPasswordTextField.setEchoChar(passwordChar);
			}
			}});
		
		registerShowCheckBox.setForeground(Color.WHITE);
		registerShowCheckBox.setBackground(new Color(23, 43 ,72));
		registerShowCheckBox.setBounds(84, 126, 124, 23);
		panel_1.add(registerShowCheckBox);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Ubuntu", Font.BOLD, 14));
		label_1.setBounds(13, 61, 331, 31);
		panel_1.add(label_1);
		
		registerUsernameTextField = new JTextField();
		registerUsernameTextField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		registerUsernameTextField.setColumns(10);
		registerUsernameTextField.setBounds(13, 88, 331, 38);
		panel_1.add(registerUsernameTextField);
		
		JLabel label_3 = new JLabel("Password");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Ubuntu", Font.BOLD, 14));
		label_3.setBounds(13, 122, 331, 31);
		panel_1.add(label_3);
		
		registerPasswordTextField = new JPasswordField();
		registerPasswordTextField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		registerPasswordTextField.setBounds(13, 150, 331, 38);
		panel_1.add(registerPasswordTextField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Ubuntu", Font.BOLD, 14));
		lblConfirmPassword.setBounds(13, 184, 331, 31);
		panel_1.add(lblConfirmPassword);
		
		registerConfirmPasswordTextField = new JPasswordField();
		registerConfirmPasswordTextField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		registerConfirmPasswordTextField.setBounds(13, 211, 331, 38);
		panel_1.add(registerConfirmPasswordTextField);
	}
	
	public void setVisibleFalse(){
		
		this.setVisible(false);
		
	}
}
