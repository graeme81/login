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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class DeleteUser extends JFrame {

	private JPanel contPDelete;
	private JTextField txtUsername;
	private JTextPane txtPaneUnList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteUser() {
		setTitle("Delete User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contPDelete = new JPanel();
		contPDelete.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contPDelete);
		contPDelete.setLayout(null);
		
		JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new StartMenu().setVisible(true);
					dispose();
				}
			});
			btnBack.setBounds(335, 227, 89, 23);
			contPDelete.add(btnBack);
		
		txtPaneUnList = new JTextPane();
			txtPaneUnList.setBounds(112, 72, 177, 89);
			contPDelete.add(txtPaneUnList);
		
		JLabel lblDeleteCurrentUser = new JLabel("Delete Current User");
			lblDeleteCurrentUser.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeleteCurrentUser.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
			lblDeleteCurrentUser.setBounds(10, 11, 414, 39);
			contPDelete.add(lblDeleteCurrentUser);
		
		JLabel lblUserToDelete = new JLabel("User to Delete:");
			lblUserToDelete.setBounds(82, 183, 83, 14);
			contPDelete.add(lblUserToDelete);
		
		txtUsername = new JTextField();
			txtUsername.setBounds(187, 180, 102, 20);
			contPDelete.add(txtUsername);
			txtUsername.setColumns(10);
		
		JButton btnDelete = new JButton("Delete User");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(User.class)
											.buildSessionFactory();
				
					Session session = factory.getCurrentSession();
				
					try{
						session.beginTransaction();
					
						String unIn = txtUsername.getText();
					
						String hql = "FROM User WHERE username = '"+ unIn +"'";
						List<User> users = session.createQuery(hql).list();
					
						for (User tempUser : users){
							JOptionPane.showMessageDialog(null, "User: "+ tempUser.getUsername()+" ...Deleted");
							session.delete(tempUser);
						}
						txtUsername.setText("");
						session.getTransaction().commit();
						readUsernames();
					
					}finally{
					factory.close();
					}
				
				}
			});
			btnDelete.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
			btnDelete.setBounds(156, 221, 102, 29);
			contPDelete.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Username List:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(112, 50, 177, 14);
			contPDelete.add(lblNewLabel);
			
		readUsernames();
	}

	private void readUsernames() {
		
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(User.class)
									.buildSessionFactory();
				
			Session session = factory.getCurrentSession();
			
		try{
			session.beginTransaction();
			List<User> userList = session.createQuery("FROM User").list();
			System.out.println(userList);
			
			String people = "";
			txtPaneUnList.setText(people);
			
			for(User aUser: userList){
				people = people + aUser.getUsername()+"\n";
				
			}
			txtPaneUnList.setText(people);
			
			session.getTransaction().commit();
			
		}finally{
			factory.close();
		}
	}	
}










