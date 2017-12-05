package com.work.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.work.login.entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StartMenu().setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnBack.setBounds(335, 227, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(98, 95, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(98, 131, 91, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(225, 91, 144, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(225, 127, 144, 23);
		contentPane.add(txtPassword);
		
		JLabel lblHeading = new JLabel("Create New User");
		lblHeading.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 28));
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setBounds(98, 11, 245, 37);
		contentPane.add(lblHeading);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(98, 167, 117, 14);
		contentPane.add(lblConfirmPassword);
		
		txtConfirm = new JTextField();
		txtConfirm.setColumns(10);
		txtConfirm.setBounds(225, 161, 144, 23);
		contentPane.add(txtConfirm);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtUsername.getText().isEmpty()||txtPassword.getText().isEmpty()||txtConfirm.getText().isEmpty()){
					txtPassword.setText("");
					txtConfirm.setText("");
					JOptionPane.showMessageDialog(null, "Please complete all form.");
				}
				else if(isUsername(txtUsername.getText())){
					JOptionPane.showMessageDialog(null, "Username already in use \n Please choose another.");
					txtUsername.setText("");
					txtPassword.setText("");
					txtConfirm.setText("");
				}
				else if (!txtPassword.getText().equals(txtConfirm.getText())){
					txtPassword.setText("");
					txtConfirm.setText("");
					JOptionPane.showMessageDialog(null, "Password not confirmed");
				}else{
					SessionFactory factory = new Configuration()
							.configure("hibernate.cfg.xml")
							.addAnnotatedClass(User.class)
							.buildSessionFactory();

					Session session = factory.getCurrentSession();
					
					try{
						User tempUser = new User(txtUsername.getText(), txtPassword.getText());
						session.beginTransaction();
						
						session.save(tempUser);
						
						session.getTransaction().commit();
						
					}finally{
						factory.close();
					}
					JOptionPane.showMessageDialog(null, "New user created");
					txtUsername.setText("");
					txtPassword.setText("");
					txtConfirm.setText("");
				}
			}

			private boolean isUsername(String username) {
				
				boolean search = false;
				
				SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(User.class)
						.buildSessionFactory();

				Session session = factory.getCurrentSession();
				
				try{
					session.beginTransaction();
					List<User> userList = session.createQuery("FROM User").list();
					
					for(User aUser: userList){
						if (aUser.getUsername().equals(username)){
							search = true;
						}
					}
					session.getTransaction().commit();
					
				}finally{
					factory.close();
				}
				
				return search;
			}
		});
		btnCreate.setBounds(173, 214, 89, 23);
		contentPane.add(btnCreate);
	}
}
