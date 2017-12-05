package com.work.login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JFrame {

	private JPanel frmStartMenu;

	// Launch the application //
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame //
	
	public StartMenu() {
		setTitle("Start Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		frmStartMenu = new JPanel();
		frmStartMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmStartMenu);
		frmStartMenu.setLayout(null);
		
		JButton btnNew = new JButton("Create New User");
			btnNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new NewUser().setVisible(true);
					dispose();
				}
			});
			btnNew.setBounds(126, 38, 144, 23);
			frmStartMenu.add(btnNew);
		
		JButton btnView = new JButton("View Users");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewUser().setVisible(true);
				dispose();
			}
		});
			btnView.setBounds(126, 75, 144, 23);
			frmStartMenu.add(btnView);
		
		JButton btnDelete = new JButton("Delete User");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteUser().setVisible(true);;
				dispose();
			}
		});
			btnDelete.setBounds(126, 109, 144, 23);
			frmStartMenu.add(btnDelete);
			
		JButton btnLogOut = new JButton("Log Out");
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LogInScreen login = new LogInScreen();
					login.frmLogin.setVisible(true);
					dispose();
				}
			});
			btnLogOut.setBounds(126, 201, 144, 23);
			frmStartMenu.add(btnLogOut);
	}

}
