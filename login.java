package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;


public class login extends JFrame{
	
	JPanel loginbox = new JPanel();
	JTextField id = new JTextField();
	JButton chk = new JButton("Ȯ��");
	
	public login() {
		setTitle("ȸ�����̵� �Է�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(loginbox);
		
		loginbox.setLayout(null);
		
		loginbox.add(id);
		loginbox.add(chk);
		
		id.setBounds(30, 30, 80, 25);
		chk.setBounds(150, 30, 80, 25);
		
		ButtonListener bt = new ButtonListener();
		
		chk.addActionListener(bt);		
		
		setSize(300,300);
		setVisible(true);
		
	}
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText();
			
			if (st == "Ȯ��") {
				
		//// id �ؽ�Ʈ�ʵ忡 �ִ� ���ڿ� �޾Ƽ� Customer ���̺��̶� �� ////
				
				dispose();
			}
		}
		
	}
}
