package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


import java.sql.*;


public class login extends JFrame{
	// DB 객체들
	DB db_forlogin;
	ResultSet rs = null;
	//ResultSetMetaData rsMeta = null;
	Statement stmt = null;
	// DB 객체들
	
	
	// 스윙 컴포넌트들
	JPanel loginbox = new JPanel();
	JTextField id = new JTextField();
	JButton chk = new JButton("확인");
	// 스윙 컴포넌트들 
	
	String text; // 다른 클래스에 ID값을 넘겨주기 위함
	
	int chkk = 0;
	
	public login(DB db) {
		db_forlogin = db;
		rs = db_forlogin.res;
		stmt = db_forlogin.stmt;
		
		setTitle("회원아이디 입력");
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
			text = id.getText(); // 텍스트 박스 안에 있는 id 문자열 받아오기
			String st = b.getText(); // 버튼 텍스트 받아오기
			
			if (st == "확인") {

				try {
					rs = stmt.executeQuery(SQL);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // resulset에 쿼리 실행 결과문 삽입

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
