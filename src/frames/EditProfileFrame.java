package frames;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class EditProfileFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField actualPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmNewPasswordField;
	private Character passwordChar;

	public EditProfileFrame(Controller ctrl) {
		setTitle("GAS Reviews - Modifica password");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imgs\\logoIcon.png"));
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 401, 401);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 43 ,72));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel passwordAttualeLabel = new JLabel("Password Attuale");
		passwordAttualeLabel.setForeground(Color.WHITE);
		passwordAttualeLabel.setFont(new Font("Ubuntu", Font.BOLD, 14));
		passwordAttualeLabel.setBounds(25, 65, 176, 13);
		
		JLabel newPasswordLabel = new JLabel("Nuova Password");
		newPasswordLabel.setForeground(Color.WHITE);
		newPasswordLabel.setFont(new Font("Ubuntu", Font.BOLD, 14));
		newPasswordLabel.setBounds(25, 158, 176, 13);
		
		actualPasswordField = new JPasswordField();
		actualPasswordField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		actualPasswordField.setBounds(25, 88, 331, 38);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		newPasswordField.setBounds(25, 181, 331, 38);
		
		JLabel confirmPasswordLabel = new JLabel("Conferma Password");
		confirmPasswordLabel.setFont(new Font("Ubuntu", Font.BOLD, 14));
		confirmPasswordLabel.setForeground(Color.WHITE);
		confirmPasswordLabel.setBounds(25, 229, 176, 13);
		
		confirmNewPasswordField = new JPasswordField();
		confirmNewPasswordField.setFont(new Font("Ubuntu", Font.BOLD, 16));
		confirmNewPasswordField.setBounds(25, 252, 331, 38);
		
		JButton confirmBtn = new JButton("");
		confirmBtn.setContentAreaFilled(false);
		confirmBtn.setBorderPainted(false);
		confirmBtn.setIcon(new ImageIcon("imgs\\confirm_button.png"));
		confirmBtn.setBounds(25, 309, 331, 38);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ctrl.updateUserPasswordEvent(actualPasswordField.getText(), newPasswordField.getText(), confirmNewPasswordField.getText());
			}
		});
		
		JCheckBox newPasswordCheckBox = new JCheckBox();
		newPasswordCheckBox.setText("Mostra");
		newPasswordCheckBox.setFont(new Font("Ubuntu", Font.BOLD, 13));
		newPasswordCheckBox.setForeground(Color.WHITE);
		newPasswordCheckBox.setBackground((new Color(23, 43 ,72)));
		newPasswordCheckBox.setBounds(157, 155, 109, 21);
		
		passwordChar = actualPasswordField.getEchoChar();
		
		newPasswordCheckBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(newPasswordCheckBox.isSelected()) {
					newPasswordField.setEchoChar((char)0);
					confirmNewPasswordField.setEchoChar((char)0);
				}
				else {
					newPasswordField.setEchoChar(passwordChar);
					confirmNewPasswordField.setEchoChar(passwordChar);
				} 
			}});
		
		JCheckBox actualPasswordCheckBox = new JCheckBox("Mostra");
		actualPasswordCheckBox.setForeground(Color.WHITE);
		actualPasswordCheckBox.setFont(new Font("Ubuntu", Font.BOLD, 13));
		actualPasswordCheckBox.setBackground(new Color(23, 43 ,72));
		actualPasswordCheckBox.setBounds(157, 61, 88, 21);
		actualPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(actualPasswordCheckBox.isSelected()) {
					actualPasswordField.setEchoChar((char)0);
					
				}
				else {
					actualPasswordField.setEchoChar(passwordChar);
					
				}
			}
		});
		panel.setLayout(null);
		panel.add(confirmNewPasswordField);
		panel.add(confirmPasswordLabel);
		panel.add(actualPasswordField);
		panel.add(newPasswordLabel);
		panel.add(passwordAttualeLabel);
		panel.add(newPasswordField);
		panel.add(newPasswordCheckBox);
		panel.add(actualPasswordCheckBox);
		panel.add(confirmBtn);
		
		JLabel lblModificaPassword = new JLabel("Modifica password");
		lblModificaPassword.setForeground(Color.WHITE);
		lblModificaPassword.setFont(new Font("Ubuntu", Font.BOLD, 20));
		lblModificaPassword.setBounds(25, 20, 273, 35);
		panel.add(lblModificaPassword);
	}
}
