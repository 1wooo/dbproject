package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;


public class GUI extends JFrame {
	ResultSet rs = null;
	ResultSetMetaData rsMeta = null;
	Statement stmt = null;
	
	DB db_forGUI;
	JPanel BG = new JPanel();
	JPanel Mainsc = new JPanel();
	
	JButton Manager = new JButton("관리자");
	JButton Client = new JButton("회원");
	JButton Showing = new JButton("예매");
	JButton Mypage = new JButton("마이페이지");
	JButton searchMovie = new JButton("입력");
	
	//예매버튼
	JButton [] book = new JButton[19];
	//예매버튼
	
	JTextField movieName = new JTextField();	
	JTextField directorName = new JTextField();
	JTextField actorName = new JTextField();
	JTextField movieType = new JTextField();

	JTable table = new JTable();
	JScrollPane tablescroll = new JScrollPane(table);

	
	
	public GUI(DB passdb) {
		db_forGUI = passdb; // Database 메인에서 생성된거 넘겨받기
		rs = db_forGUI.res;
		stmt = db_forGUI.stmt;
		rsMeta = db_forGUI.Meta;
		
		setTitle("17011395 김원우");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(BG);
		panel();
		
		setSize(1000, 1000);
		setVisible(true);		
	}
	
	void panel() {
		// 로그인 이전 시작패널
		
		BG.setLayout(null);


		BG.add(Manager);
		BG.add(Client);
		///////////////// 패널 셋팅 끝

		Manager.setBounds(500, 200, 80, 25);
		Client.setBounds(400, 200, 80, 25);

		ButtonListener bt = new ButtonListener();
		
		Manager.addActionListener(bt);
		Client.addActionListener(bt);
		// 로그인 이전 시작 패널

		// 로그인 이후 메인패널
		Mainsc.setLayout(null);
		Mainsc.add(Showing);
		Mainsc.add(Mypage);
		//Mainsc.add(table);
		
		Showing.addActionListener(bt);
		Mypage.addActionListener(bt);
		
		
		Showing.setBounds(0, 0, 110, 25);
		Mypage.setBounds(120, 0, 110, 25);
		
		// 검색기능
		movieName.setText("");
		directorName.setText("");
		actorName.setText("");
		movieType.setText("");		
		
		searchMovie.addActionListener(bt);
		// 검색기능
		
		// 예매기능
		
		for (int i=0; i<19; i++) {
			book[i] = new JButton();
			book[i].setText("예매"+String.valueOf(i+1));
		}
		
		// 예매기능
		
		
		// 로그인 이후 메인패널
	
	}
	
	
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText(); // 버튼 텍스트 받아오기
			login cl;
			
			// 로그인 이전 시작패널에서의 버튼들
			if (st == "회원") {
				cl = new login(db_forGUI);
			} 
			else if (st == "관리자") {
				//관리자 버튼 눌렀을때
			}
			// 로그인 이전 시작패널에서의 버튼들
			
			// 로그인 이후 메인패널에서의 버튼들
			else if (st == "예매") {
				
				Mainsc.add(tablescroll);
				table.setRowHeight(25);
				tablescroll.setBounds(100, 100, 800, 500);

				Mainsc.add(movieName);
				Mainsc.add(directorName);
				Mainsc.add(actorName);
				Mainsc.add(movieType);
				Mainsc.add(searchMovie);
				
				movieName.setBounds(120, 60, 110, 25);
				directorName.setBounds(240, 60, 110, 25);
				actorName.setBounds(360, 60, 110, 25);
				movieType.setBounds(480, 60, 110, 25);
				
				searchMovie.setBounds(600, 60, 110, 25);
				
				for (int i=0; i<19; i++) {
					Mainsc.add(book[i]);
					book[i].setBounds(10, 125+(i*25), 80, 25);
				}
			}
			else if (st == "마이페이지") {
				
			}
			
			else if (st == "입력") {
				
				
				String scSQL = "select * "
						+ "from movie "
						+ "where movieName like '%"+ movieName.getText() + "%' "
						+ "and movieDirector like '%"+ directorName.getText() + "%' "
						+ "and movieActor like '%"+ actorName.getText() + "%' "
						+ "and movieType like '%"+ movieType.getText() + "%';";
				try {
					rs = stmt.executeQuery(scSQL);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Vector<String> columnNames = new Vector<String>(); // 필드생성
				Vector data = new Vector(); // 데이터 생성
				
				
				for (int i=1; i<=9; i++) {
					try {
						columnNames.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
				try {
					
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=9; j++) {
							row.add(rs.getString(j));
						}
						data.add(row);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
				table.setModel(dtm);
				
				//dtm.fireTableDataChanged();
				

				
				
			}
			// 로그인 이후 메인패널에서의 버튼들

			
		}
		
	}
}