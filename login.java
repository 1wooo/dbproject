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
	JButton chk = new JButton("확인");
	
	public login() {
		setTitle("회원아이디 입력");
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
			
			if (st == "확인") {
				
		//// id 텍스트필드에 있는 문자열 받아서 Customer 테이블이랑 비교 ////
				
				dispose();
			}
		}
		
	}
}
