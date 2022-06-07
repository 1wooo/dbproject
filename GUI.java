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
	
	login cl; // �α��� ���� Ŭ����

	DB db_forGUI;
	JPanel BG = new JPanel(); // �α��� ���� �г�
	JPanel Mainsc = new JPanel(); // �α��� ���� �г�
	JPanel Managesc = new JPanel(); // ������ �г�
	
	// �α��� ���� ��ư
	JButton Manager = new JButton("������");
	JButton Client = new JButton("ȸ��");
	// �α��� ���� ��ư��
	
	// �α��� ���� ��ư��
	
	// �⺻�׺���̼� ��ư�� 
	JButton Showing = new JButton("����");
	JButton Mypage = new JButton("����������");
	// �⺻�׺���̼� ��ư��
	
	// ������ ������Ʈ��
	
	JTextField SQLtext = new JTextField();
	JButton SQLex = new JButton("����");
	
	JButton movie = new JButton("��ȭ���̺�");
	JButton schedule = new JButton("���������̺�");
	JButton reservation = new JButton("�������̺�");
	JButton ticket = new JButton("Ƽ�����̺�");
	JButton customer = new JButton("�����̺�");
	JButton seat = new JButton("�¼����̺�");
	JButton theater = new JButton("�󿵰����̺�");
	
	JButton goHome = new JButton("Ȩ����");

	JTable seeTable = new JTable();
	JScrollPane seeTable_scroll = new JScrollPane(seeTable);
	
	// ������ ������Ʈ��
	// �˻����� ������Ʈ��
	JLabel mN = new JLabel("��ȭ��");
	JLabel dN = new JLabel("������");
	JLabel aN = new JLabel("����");
	JLabel mT = new JLabel("�帣");
	JTextField movieName = new JTextField();	
	JTextField directorName = new JTextField();
	JTextField actorName = new JTextField();
	JTextField movieType = new JTextField();
	JButton searchMovie = new JButton("�Է�");
	// �˻����� ������Ʈ��
	
	// ���Ű��� ������Ʈ��
	JButton booking = new JButton("�����ٺ���");
	JButton reserVation = new JButton("�����ϱ�");
			
		// �������̺��
	JTable table = new JTable();
	JTable ScheduleTable = new JTable();
	JScrollPane tablescroll = new JScrollPane(table);
	JScrollPane scheduleScroll = new JScrollPane(ScheduleTable);
	
		// ��ȭ ���Ÿ� ���� ���� �ڽ���
	JRadioButton [] chk = new JRadioButton[10];
	JRadioButton [] res = new JRadioButton[7];
	ButtonGroup chkbtns = new ButtonGroup();
	ButtonGroup resbtns = new ButtonGroup();
	// ���Ű��� ������Ʈ��
	
	
	// ���������� �г� ������Ʈ��
	JButton revSearch = new JButton("����������ȸ");
	JButton deleteBtn = new JButton("�������");
	
		// ���������� ���̺�
	JTable MPtable = new JTable();
	JTable MPtable2 = new JTable();
	JScrollPane MPscroll = new JScrollPane(MPtable);
	JScrollPane MPscroll2 = new JScrollPane(MPtable2);
	
	JRadioButton [] Myreserve = new JRadioButton[10]; // MPtable1 ���� �޾Ƴ��� üũ�ڽ���
	ButtonGroup Myrevbtns = new ButtonGroup();
	JRadioButton [] Myreserve2 = new JRadioButton[7];
	ButtonGroup Myrevbtns2 = new ButtonGroup();
	
	// ���������� �г� ������Ʈ��
	
	public GUI(DB passdb) {
		db_forGUI = passdb; // Database ���ο��� �����Ȱ� �Ѱܹޱ�
		rs = db_forGUI.res;
		stmt = db_forGUI.stmt;
		rsMeta = db_forGUI.Meta;
		
		setTitle("17011395 �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(BG);
		panel();
		
		setSize(1000, 890);
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
		
		// �⺻ ���̰��̼� ������Ʈ ����
		Mainsc.setLayout(null);
		Mainsc.add(Showing);
		Mainsc.add(Mypage);
		Mainsc.add(goHome);
		
		Showing.addActionListener(bt);
		Mypage.addActionListener(bt); 
		goHome.addActionListener(bt);
		
		Showing.setBounds(0, 0, 110, 25);
		Mypage.setBounds(120, 0, 110, 25);
		goHome.setBounds(240, 0, 110, 25);

		// �⺻ ���̰��̼� ������Ʈ ����
		
		// �˻����
		searchMovie.addActionListener(bt); // �˻� �Է� ��ư
		booking.addActionListener(bt); // �����ٺ��� ��ư
		// �˻����
		
		// �����ϱ� ��ư
		reserVation.addActionListener(bt);
		// �����ϱ� ��ư
		
		// ���ű���� ���� ������ư �ڽ�
		for (int i=0; i<10; i++) {
			chk[i] = new JRadioButton("����" + (i+1));
			chkbtns.add(chk[i]);
		} 
		
		for (int i=0; i<7; i++) {
			res[i] = new JRadioButton("����" + (i+1));
			resbtns.add(res[i]);
		}
		// ���� ��ư �׷�ȭ
	
		
		
		// �α��� ���� �����г�
	
	}
	
	void MypagePanel() {
		Mainsc.removeAll();
		Mainsc.add(Mypage);
		Mainsc.add(Showing);
		Mainsc.add(goHome);
		Mainsc.add(MPscroll);
		Mainsc.add(MPscroll2);
		Mainsc.repaint();
		
		Mainsc.add(revSearch); // ����������ȸ��ư �ø���
		Mainsc.add(deleteBtn);
		
		ButtonListener myPagebt = new ButtonListener();
		
		revSearch.setBounds(380,70, 120, 25); // ����������ȸ ��ư ��ġ����
		revSearch.addActionListener(myPagebt); // ����������ȸ �׼Ǹ����� �ޱ�
		deleteBtn.setBounds(410, 378, 100, 25); // ������ҹ�ư ��ġ����
		deleteBtn.addActionListener(myPagebt); // ������ҹ�ư �׼Ǹ����� �ޱ�
		
		
		MPtable.setRowHeight(25);
		MPtable2.setRowHeight(25);
		MPscroll.setBounds(100, 100, 800, 274);
		MPscroll2.setBounds(100, 410, 800, 200);
		// �гο� ���̺�� ����̼��� �� ��ġ����
		
		for (int i=0; i<10; i++) {
			Myreserve[i] = new JRadioButton("����" + (i+1));
			Myrevbtns.add(Myreserve[i]);
			Mainsc.add(Myreserve[i]);
			Myreserve[i].setBounds(20, 125+(i*25), 80, 25);
		} // üũ�ڽ��� �ޱ�
		
		for (int i=0; i<7; i++) {
			Myreserve2[i] = new JRadioButton("����" + (i+1));
			Myrevbtns2.add(Myreserve2[i]);
		} // MPtable2 ���� �� üũ�ڽ���
	
		
	}
	
	void manegerPanel() {
		BG.setVisible(false);
		setContentPane(Managesc);
		Managesc.setLayout(null);
		Managesc.setVisible(true);
		
		Managesc.add(SQLtext);
		Managesc.add(SQLex);
		// �����Է°��� ������Ʈ��
		
		SQLtext.setBounds(160, 55, 610, 25);
		SQLex.setBounds(785, 55, 100, 25);
		

		Managesc.add(movie);
		Managesc.add(schedule);
		Managesc.add(reservation);
		Managesc.add(ticket);
		Managesc.add(customer);
		Managesc.add(seat);
		Managesc.add(theater);
		
		Managesc.add(goHome);
		
		ButtonListener manegebtnLi = new ButtonListener();
		
		movie.addActionListener(manegebtnLi);
		schedule.addActionListener(manegebtnLi);
		reservation.addActionListener(manegebtnLi);
		ticket.addActionListener(manegebtnLi);
		customer.addActionListener(manegebtnLi);
		seat.addActionListener(manegebtnLi);
		theater.addActionListener(manegebtnLi);
		goHome.addActionListener(manegebtnLi);
		SQLex.addActionListener(manegebtnLi);
		
		movie.setBounds(20, 125, 130, 25);
		schedule.setBounds(20, 150, 130, 25);
		reservation.setBounds(20, 175, 130, 25);
		ticket.setBounds(20, 200, 130, 25);
		customer.setBounds(20, 225, 130, 25);
		seat.setBounds(20, 250, 130, 25);
		theater.setBounds(20, 275, 130, 25);
		goHome.setBounds(240, 0, 110, 25);
		
		Managesc.add(seeTable_scroll);
		
		seeTable.setRowHeight(25);
		seeTable_scroll.setBounds(160, 100, 800, 500);
		
		// ���̺��� ���� ������Ʈ��
		
		
	}
	
	public class ButtonListener implements ActionListener {
		int MyreserveNum = 0; // MPtable1 ���� üũ�� ���ų����� ���� ���Ź�ȣ

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText(); // ��ư �ؽ�Ʈ �޾ƿ���
			
			// �α��� ���� �����гο����� ��ư��
			if (st == "ȸ��") {
				cl = new login(db_forGUI);
			} 
			else if (st == "������") {
				//������ ��ư ��������
			
				manegerPanel();
				
				
			}
			// �α��� ���� �����гο����� ��ư��
			
			// �α��� ���� �����гο����� ��ư��
			else if (st == "����") {
				Mainsc.removeAll();
				Mainsc.add(Mypage);
				Mainsc.add(Showing);
				Mainsc.add(goHome);
				Mainsc.repaint();
				// ���̺�� �߰� �� ������ ����
				Mainsc.add(tablescroll);
				Mainsc.add(scheduleScroll);
				table.setRowHeight(25);
				tablescroll.setBounds(100, 100, 800, 274);
				ScheduleTable.setRowHeight(25);
				scheduleScroll.setBounds(100, 410, 800, 200);
				// ���̺�� �߰� �� ������ ����
				
				// �˻��� �ؽ�Ʈ�ʵ�� ��ư
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
				// �˻��� �ؽ�Ʈ�ʵ�� ��ư

				// �󺧵�
				Mainsc.add(mN);
				Mainsc.add(dN);
				Mainsc.add(aN);
				Mainsc.add(mT);				
				mN.setBounds(120, 30, 80, 25);
				dN.setBounds(240, 30, 80, 25);
				aN.setBounds(360, 30, 80, 25);
				mT.setBounds(480, 30, 80, 25);
				// �󺧵�
				
			}
			else if (st == "����������") {
				MypagePanel(); // �гο� ������Ʈ �ø���
				Vector<String> columnNames = new Vector<String>(); // ���� �÷����� ���Կ� MPtable1 ����

				String serchReserve = "Select Reservation.reserveNum, movieName, screeningStart, Schedule.theaterNum, seatNum, salePrice"
						+ " from Customer, Reservation, Movie, Schedule, Ticket"
						+ " where Customer.custId = '" + cl.getID() +"' and Reservation.custId = customer.custId  and Reservation.reserveNum = Ticket.reserveNum "
								+ "and Movie.movieNum = Schedule.movieNum and Schedule.scheduleNum = Ticket.scheduleNum;";
				// ���� ����������ȸ ����
				
				try {
					rs = stmt.executeQuery(serchReserve);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				Vector data = new Vector();
				
				for (int i=1; i<=6; i++) {
					try {
						columnNames.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=6; j++) {
							row.add(rs.getString(j));
						}
						data.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
				MPtable.setModel(dtm); // ���̺� ���� ������ �ֱ�
				
			}
			
			else if (st == "����") {
				String sql = SQLtext.getText();
				JOptionPane warning = new JOptionPane();

				try {
					stmt.execute(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				warning.showMessageDialog(null,"������ ����Ǿ����ϴ�. ���̺��� ��ȸ���ּ���.");	

				
			}
			else if (st == "��ȭ���̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From Movie;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
				
			}
			else if (st == "���������̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From Schedule;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "�������̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From Reservation;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "Ƽ�����̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From ticket;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "�����̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From Customer;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "�¼����̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From Seat;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "�󿵰����̺�") {
				Vector<String> colN = new Vector<String>();
				Vector tableData = new Vector();
				
				String sql = "Select * From theater;";
				int colcnt = 0;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					colcnt = rsMeta.getColumnCount();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i=1; i<=colcnt; i++) {
					try {
						colN.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=colcnt; j++) {
							row.add(rs.getString(j));
						}
						tableData.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "Ȩ����") {
				Mainsc.setVisible(false);
				Managesc.setVisible(false);
				BG.setVisible(true);
				setContentPane(BG);
			}
			
 			else if (st == "����������ȸ") {
				// �������������� ���� ������ ���ſ� ���� ���� ��� ����
				Vector<String> columnNames2 = new Vector<String>(); // Mysql�� ���̺� ���� �������� ���� ���͵� MPtable2 ����

				for (int i=0; i<7; i++) {
					Mainsc.add(Myreserve2[i]);
					Myreserve2[i].setBounds(20, 435+(i*25), 80, 25);
				} // MPtable2 ���� �� üũ�ڽ���
				
				
				int chkNum = 0;
				Vector data2 = new Vector();
				
				for (int i=0; i<10; i++) {
					if (Myreserve[i].isSelected()) {
						chkNum = i; // ���õ� üũ�ڽ��� �������?
					}
				}
				
				MyreserveNum = Integer.parseInt(MPtable.getValueAt(chkNum, 0).toString()); // ������ üũ�ڽ��� ����� �����ȣ
				
				
				String viewallInfoSQL = "Select screeningStart, screeningDate, startTime, Ticket.*"
						+ " from Reservation, Schedule, Ticket"
						+ " where reservation.reserveNum = " + MyreserveNum + " and reservation.reserveNum = Ticket.reserveNum and Schedule.scheduleNum = Ticket.scheduleNum;"; // �ش� ���ſ� ���� ���� ��� ����
				
				try {
					rs = stmt.executeQuery(viewallInfoSQL);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				for (int i=1; i<=11; i++) {
					try {
						columnNames2.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} // �÷��̸� ���Ϳ� ����
				
				try {
					
					while(rs.next()) {
						Vector row2 = new Vector();
						for (int j=1; j<=11; j++) {
							row2.add(rs.getString(j));
						}
						data2.add(row2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // ���̺� ������ ���Ϳ� ����
				
				DefaultTableModel dtm = new DefaultTableModel(data2, columnNames2);
				MPtable2.setModel(dtm);
				
				
			}
 			
			else if (st == "�������") {
				int chkNum = 0;
				int chkNum2 = 0;
				int MyreserveNum = 0;
				int scheduleNum = 0;
				int seatNum = 0;
				
				String deleteSQL = "";
				String ticketDelete = "";
				
				for (int i=0; i<7; i++) {
					if (Myreserve[i].isSelected()) {
						chkNum = i; // ���õ� üũ�ڽ��� �������?
					}
				}
				
				for (int i=0; i<7; i++) {
					if (Myreserve2[i].isSelected()) {
						chkNum2 = i; // ���õ� üũ�ڽ��� �������?
					}
				}
				
				scheduleNum = Integer.parseInt(MPtable2.getValueAt(chkNum2, 4).toString());
				seatNum = Integer.parseInt(MPtable2.getValueAt(chkNum2, 6).toString());
				
				String updateSeat = "update seat set seatUsed = '��밡��' "
						+ " where seat.scheduleNum = " + scheduleNum + " and seatNum = " + seatNum + ";";
				
				try {
					stmt.execute(updateSeat);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						
				MyreserveNum = Integer.parseInt(MPtable.getValueAt(chkNum, 0).toString()); // ������ üũ�ڽ��� ����� �����ȣ
				ticketDelete = "Delete from ticket where reserveNum = " + MyreserveNum + ";";
				deleteSQL = "Delete from reservation Where reserveNum = " + MyreserveNum + ";";
				
				try {
					stmt.execute(ticketDelete);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					stmt.execute(deleteSQL);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				// MPtable1 ����
				
				Vector data1 = new Vector();
				Vector<String> new_columnNames = new Vector<String>();
				String serchReserve = "Select Reservation.reserveNum, movieName, screeningStart, Schedule.theaterNum, seatNum, salePrice"
						+ " from Customer, Reservation, Movie, Schedule, Ticket"
						+ " where Customer.custId = '" + cl.getID() +"' and Reservation.custId = customer.custId  and Reservation.reserveNum = Ticket.reserveNum "
								+ "and Movie.movieNum = Schedule.movieNum and Schedule.scheduleNum = Ticket.scheduleNum;";
				
				
				try {
					rs = stmt.executeQuery(serchReserve);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				for (int i=1; i<=6; i++) {
					try {
						new_columnNames.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=6; j++) {
							row.add(rs.getString(j));
						}
						data1.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm1 = new DefaultTableModel(data1, new_columnNames);
				MPtable.setModel(dtm1);
				
				// MPtable2 ����
				Vector data2 = new Vector();
				Vector<String> new_columnNames2 = new Vector<String>();

				String viewallInfoSQL = "Select screeningStart, screeningDate, startTime, Ticket.*"
						+ " from Reservation, Schedule, Ticket"
						+ " where reservation.reserveNum = " + MyreserveNum + " and reservation.reserveNum = Ticket.reserveNum and Schedule.scheduleNum = Ticket.scheduleNum;"; // �ش� ���ſ� ���� ���� ��� ����
				
				try {
					rs = stmt.executeQuery(viewallInfoSQL);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				for (int i=1; i<=11; i++) {
					try {
						new_columnNames.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					
					while(rs.next()) {
						Vector row = new Vector();
						for (int j=1; j<=11; j++) {
							row.add(rs.getString(j));
						}
						data2.add(row);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm2 = new DefaultTableModel(data2, new_columnNames2);
				MPtable2.setModel(dtm2);
				 /////////////////////////////////////////////////////// ���� ������� �׽�Ʈ �ʿ���.
			}
			
			else if (st == "�Է�") {
				//////////// �˻����
				Mainsc.repaint();
				String scSQL = "select * "
						+ "from movie "
						+ "where movieName like '%"+ movieName.getText() + "%' "
						+ "and movieDirector like '%"+ directorName.getText() + "%' "
						+ "and movieActor like '%"+ actorName.getText() + "%' "
						+ "and movieType like '%"+ movieType.getText() + "%';";
				try {
					rs = stmt.executeQuery(scSQL);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				Vector<String> columnNames = new Vector<String>(); // �ʵ����
				Vector data = new Vector(); // ������ ����
				
				
				for (int i=1; i<=9; i++) {
					try {
						columnNames.add(rsMeta.getColumnName(i));
					} catch (SQLException e1) {
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
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
				table.setModel(dtm);
				
				
				// ������ư �߰� �� ��ġ���� 
				for (int i=0; i<10; i++) {
					Mainsc.add(chk[i]);
					chk[i].setBounds(20, 125+(i*25), 80, 25);
				}				
				// ������ư �߰� �� ��ġ���� 

				// ������ ���� ��ư �߰� �� ��ġ����
				Mainsc.add(booking);
				booking.setBounds(410, 378, 100, 25);
				// ������ ���� ��ư �߰� �� ��ġ����
			
				
				//// else if ���� ��ư �� ////
			}
			// �α��� ���� �����гο����� ��ư��

			else if (st == "�����ٺ���") {
				Mainsc.repaint();
				String SQL2; // ������

				Vector<String> columnNames2 = new Vector<String>(); // Mysql�� ���̺� ���� �������� ���� ���͵�
				Vector data2 = new Vector();
				
				
				int chkNum = 0;
				
				// � üũ�ڽ��� üũ�Ǿ����� üũ�ϱ�
				for (int i=0; i<10; i++) {
					if (chk[i].isSelected()) {
						chkNum = i;
						break;
					}
				}
			
				int movieNumber = Integer.parseInt(table.getValueAt(chkNum, 0).toString()); // �ش��ϴ� üũ�ڽ��� ����� ��ȭ��ȣ
				SQL2 = "select schedule.scheduleNum, movie.movieName,schedule.theaterNum, schedule.screeningDate, "
						+ "schedule.screeningSession, schedule.screeningStart, schedule.startTime "
						+ "from Schedule,Movie where Schedule.movieNum = Movie.movieNum and Movie.movieNum = " + movieNumber + ";"
						;
				// ������ ������ ����
				// ��������
				try {
					rs = stmt.executeQuery(SQL2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					rsMeta = rs.getMetaData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// ��������
				
				// ���� ������ �÷����� ���Ϳ� rsMeta�� �޼ҵ�� �÷��̸��� ����
				for (int j=1; j<=7; j++) {
					try {
						columnNames2.add(rsMeta.getColumnName(j));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				try {
					
					while(rs.next()) {
						Vector row2 = new Vector();
						for (int j=1; j<=7; j++) {
							row2.add(rs.getString(j));
						}
						data2.add(row2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				DefaultTableModel dtm2 = new DefaultTableModel(data2, columnNames2);
				ScheduleTable.setModel(dtm2);
				Mainsc.add(reserVation);
				reserVation.setBounds(520, 378, 100, 25);
				
				// ������ư �߰� �� ��ġ���� 
				for (int i=0; i<7; i++) {
					Mainsc.add(res[i]);
					res[i].setBounds(20, 435+(i*25), 80, 25);
				}				
				// ������ư �߰� �� ��ġ���� 
				
				
				//// �����ٺ��� else if�� �� ////
			}
			
			else if (st == "�����ϱ�") {
				int schNum = 1;
				
				for (int i=0; i<10; i++) {
					if (res[i].isSelected()) {
						schNum = i;
						break;
					}
				}
			
				int schNumber = Integer.parseInt(ScheduleTable.getValueAt(schNum, 0).toString()); // �ش��ϴ� üũ�ڽ��� ����� �����ٹ�ȣ
				int theNumber = Integer.parseInt(ScheduleTable.getValueAt(schNum, 2).toString()); // �ش��ϴ� üũ�ڽ��� ����� �󿵰���ȣ
				
				new movie_reservation(db_forGUI, schNumber, theNumber);
				
			}
			
		}
		
	}
}
