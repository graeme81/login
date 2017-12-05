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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class ViewUser extends JFrame {

	private JPanel contentPane;
	private JTextPane txtPaneInfo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUser frame = new ViewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new StartMenu().setVisible(true);
					dispose();
				}
			});
			btnBack.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
			btnBack.setBounds(335, 227, 89, 23);
			contentPane.add(btnBack);
		
		JLabel lblUsersList = new JLabel("Users List");
			lblUsersList.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsersList.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
			lblUsersList.setBounds(0, 11, 434, 30);
			contentPane.add(lblUsersList);
		
		txtPaneInfo = new JTextPane();
			txtPaneInfo.setBounds(64, 52, 320, 140);
			contentPane.add(txtPaneInfo);
		
		readUserInfo();
	}

	private void readUserInfo() {
	
			
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(User.class)
									.buildSessionFactory();
				
			Session session = factory.getCurrentSession();
			
		try{
			session.beginTransaction();
			List<User> userList = session.createQuery("FROM User").list();
			System.out.println(userList);
			
			String users = "Username \t\t Password \n\n";
			
			for(User aUser: userList){
				users = users + aUser.getUsername() + "\t\t"  + aUser.getPassword()+"\n";
			}
	
			txtPaneInfo.setText(users);
			
			session.getTransaction().commit();
			
		}finally{
			factory.close();
		}
	}
}
