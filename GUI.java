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
	
	JButton Manager = new JButton("������");
	JButton Client = new JButton("ȸ��");
	
	public GUI(DB passdb) {
		db = passdb; // Database ���ο��� �����Ȱ� �Ѱܹޱ�
		
		setTitle("17011395 �����");
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
		///////////////// �г� ���� ��

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
			String st = b.getText(); // ��ư �ؽ�Ʈ �޾ƿ���
			
			if (st == "ȸ��") {
				login cl = new login();
			}
		}
		
	}
}