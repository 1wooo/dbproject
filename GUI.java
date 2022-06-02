package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;


public class GUI extends JFrame {
	DB db;
	JPanel BG = new JPanel();
	
	JButton Manager = new JButton("관리자");
	JButton Client = new JButton("회원");
	
	public GUI(DB passdb) {
		db = passdb; // Database 메인에서 생성된거 넘겨받기
		
		setTitle("17011395 김원우");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(BG);
		panel();
		
		setSize(1000, 1000);
		setVisible(true);		
	}
	
	void panel() {
		
		BG.setLayout(null);


		BG.add(Manager);
		BG.add(Client);
		///////////////// 패널 셋팅 끝

		Manager.setBounds(500, 200, 80, 25);
		Client.setBounds(400, 200, 80, 25);

		ButtonListener bt = new ButtonListener();
		
		Manager.addActionListener(bt);
		Client.addActionListener(bt);
	}
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText(); // 버튼 텍스트 받아오기
			
			if (st == "회원") {
				login cl = new login();
			}
		}
		
	}
}