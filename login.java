package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


import java.sql.*;


public class login extends JFrame{
	// DB ��ü��
	DB db_forlogin;
	ResultSet rs = null;
	//ResultSetMetaData rsMeta = null;
	Statement stmt = null;
	// DB ��ü��
	
	
	// ���� ������Ʈ��
	JPanel loginbox = new JPanel();
	JTextField id = new JTextField();
	JButton chk = new JButton("Ȯ��");
	// ���� ������Ʈ�� 
	
	String text; // �ٸ� Ŭ������ ID���� �Ѱ��ֱ� ����
	
	int chkk = 0;
	
	public login(DB db) {
		db_forlogin = db;
		rs = db_forlogin.res;
		stmt = db_forlogin.stmt;
		
		setTitle("ȸ�����̵� �Է�");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
			JOptionPane warning = new JOptionPane();
			
			String SQL = "select custId from Customer;";
			JButton b = (JButton)e.getSource();
			text = id.getText(); // �ؽ�Ʈ �ڽ� �ȿ� �ִ� id ���ڿ� �޾ƿ���
			String st = b.getText(); // ��ư �ؽ�Ʈ �޾ƿ���
			
			if (st == "Ȯ��") {

				try {
					rs = stmt.executeQuery(SQL);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // resulset�� ���� ���� ����� ����

				try {

					while(rs.next()) {
						if (text.equals(rs.getString(1))) {
							chkk = 1;
							dispose();
							//System.out.println(chkk);
							Movie.mv.BG.setVisible(false);
							Movie.mv.Mainsc.setVisible(true);
							Movie.mv.setContentPane(Movie.mv.Mainsc);
						}
					}

				}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (chkk == 0) {
					warning.showMessageDialog(null,text);	
				}
				
			}
		}
	}


	public String getID() {
		return text;
	}


}
