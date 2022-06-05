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
	
	JButton Manager = new JButton("������");
	JButton Client = new JButton("ȸ��");
	JButton Showing = new JButton("����");
	JButton Mypage = new JButton("����������");
	JButton searchMovie = new JButton("�Է�");
	
	//���Ź�ư
	JButton [] book = new JButton[19];
	//���Ź�ư
	
	JTextField movieName = new JTextField();	
	JTextField directorName = new JTextField();
	JTextField actorName = new JTextField();
	JTextField movieType = new JTextField();

	JTable table = new JTable();
	JScrollPane tablescroll = new JScrollPane(table);

	
	
	public GUI(DB passdb) {
		db_forGUI = passdb; // Database ���ο��� �����Ȱ� �Ѱܹޱ�
		rs = db_forGUI.res;
		stmt = db_forGUI.stmt;
		rsMeta = db_forGUI.Meta;
		
		setTitle("17011395 �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(BG);
		panel();
		
		setSize(1000, 1000);
		setVisible(true);		
	}
	
	void panel() {
		// �α��� ���� �����г�
		
		BG.setLayout(null);


		BG.add(Manager);
		BG.add(Client);
		///////////////// �г� ���� ��

		Manager.setBounds(500, 200, 80, 25);
		Client.setBounds(400, 200, 80, 25);

		ButtonListener bt = new ButtonListener();
		
		Manager.addActionListener(bt);
		Client.addActionListener(bt);
		// �α��� ���� ���� �г�

		// �α��� ���� �����г�
		Mainsc.setLayout(null);
		Mainsc.add(Showing);
		Mainsc.add(Mypage);
		//Mainsc.add(table);
		
		Showing.addActionListener(bt);
		Mypage.addActionListener(bt);
		
		
		Showing.setBounds(0, 0, 110, 25);
		Mypage.setBounds(120, 0, 110, 25);
		
		// �˻����
		movieName.setText("");
		directorName.setText("");
		actorName.setText("");
		movieType.setText("");		
		
		searchMovie.addActionListener(bt);
		// �˻����
		
		// ���ű��
		
		for (int i=0; i<19; i++) {
			book[i] = new JButton();
			book[i].setText("����"+String.valueOf(i+1));
		}
		
		// ���ű��
		
		
		// �α��� ���� �����г�
	
	}
	
	
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText(); // ��ư �ؽ�Ʈ �޾ƿ���
			login cl;
			
			// �α��� ���� �����гο����� ��ư��
			if (st == "ȸ��") {
				cl = new login(db_forGUI);
			} 
			else if (st == "������") {
				//������ ��ư ��������
			}
			// �α��� ���� �����гο����� ��ư��
			
			// �α��� ���� �����гο����� ��ư��
			else if (st == "����") {
				
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
			else if (st == "����������") {
				
			}
			
			else if (st == "�Է�") {
				
				
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
				
				Vector<String> columnNames = new Vector<String>(); // �ʵ����
				Vector data = new Vector(); // ������ ����
				
				
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
			// �α��� ���� �����гο����� ��ư��

			
		}
		
	}
}