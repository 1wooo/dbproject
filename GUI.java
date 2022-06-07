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
	
	login cl; // 로그인 전용 클래스

	DB db_forGUI;
	JPanel BG = new JPanel(); // 로그인 이전 패널
	JPanel Mainsc = new JPanel(); // 로그인 이후 패널
	JPanel Managesc = new JPanel(); // 관리자 패널
	
	// 로그인 이전 버튼
	JButton Manager = new JButton("관리자");
	JButton Client = new JButton("회원");
	// 로그인 이전 버튼들
	
	// 로그인 이후 버튼들
	
	// 기본네비게이션 버튼들 
	JButton Showing = new JButton("예매");
	JButton Mypage = new JButton("마이페이지");
	// 기본네비게이션 버튼들
	
	// 관리자 컴포넌트들
	
	JTextField SQLtext = new JTextField();
	JButton SQLex = new JButton("실행");
	
	JButton movie = new JButton("영화테이블");
	JButton schedule = new JButton("스케줄테이블");
	JButton reservation = new JButton("예약테이블");
	JButton ticket = new JButton("티켓테이블");
	JButton customer = new JButton("고객테이블");
	JButton seat = new JButton("좌석테이블");
	JButton theater = new JButton("상영관테이블");
	
	JButton goHome = new JButton("홈으로");

	JTable seeTable = new JTable();
	JScrollPane seeTable_scroll = new JScrollPane(seeTable);
	
	// 관리자 컴포넌트들
	// 검색관련 컴포넌트들
	JLabel mN = new JLabel("영화명");
	JLabel dN = new JLabel("감독명");
	JLabel aN = new JLabel("배우명");
	JLabel mT = new JLabel("장르");
	JTextField movieName = new JTextField();	
	JTextField directorName = new JTextField();
	JTextField actorName = new JTextField();
	JTextField movieType = new JTextField();
	JButton searchMovie = new JButton("입력");
	// 검색관련 컴포넌트들
	
	// 예매관련 컴포넌트들
	JButton booking = new JButton("스케줄보기");
	JButton reserVation = new JButton("예매하기");
			
		// 예매테이블들
	JTable table = new JTable();
	JTable ScheduleTable = new JTable();
	JScrollPane tablescroll = new JScrollPane(table);
	JScrollPane scheduleScroll = new JScrollPane(ScheduleTable);
	
		// 영화 예매를 위한 라디오 박스들
	JRadioButton [] chk = new JRadioButton[10];
	JRadioButton [] res = new JRadioButton[7];
	ButtonGroup chkbtns = new ButtonGroup();
	ButtonGroup resbtns = new ButtonGroup();
	// 예매관련 컴포넌트들
	
	
	// 마이페이지 패널 컴포넌트들
	JButton revSearch = new JButton("예매정보조회");
	JButton deleteBtn = new JButton("예매취소");
	
		// 마이페이지 테이블
	JTable MPtable = new JTable();
	JTable MPtable2 = new JTable();
	JScrollPane MPscroll = new JScrollPane(MPtable);
	JScrollPane MPscroll2 = new JScrollPane(MPtable2);
	
	JRadioButton [] Myreserve = new JRadioButton[10]; // MPtable1 옆에 달아놓을 체크박스들
	ButtonGroup Myrevbtns = new ButtonGroup();
	JRadioButton [] Myreserve2 = new JRadioButton[7];
	ButtonGroup Myrevbtns2 = new ButtonGroup();
	
	// 마이페이지 패널 컴포넌트들
	
	public GUI(DB passdb) {
		db_forGUI = passdb; // Database 메인에서 생성된거 넘겨받기
		rs = db_forGUI.res;
		stmt = db_forGUI.stmt;
		rsMeta = db_forGUI.Meta;
		
		setTitle("17011395 김원우");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(BG);
		panel();
		
		setSize(1000, 890);
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
		
		// 기본 네이게이션 컴포넌트 셋팅
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

		// 기본 네이게이션 컴포넌트 셋팅
		
		// 검색기능
		searchMovie.addActionListener(bt); // 검색 입력 버튼
		booking.addActionListener(bt); // 스케줄보기 버튼
		// 검색기능
		
		// 예매하기 버튼
		reserVation.addActionListener(bt);
		// 예매하기 버튼
		
		// 예매기능을 위한 라디오버튼 박스
		for (int i=0; i<10; i++) {
			chk[i] = new JRadioButton("선택" + (i+1));
			chkbtns.add(chk[i]);
		} 
		
		for (int i=0; i<7; i++) {
			res[i] = new JRadioButton("예매" + (i+1));
			resbtns.add(res[i]);
		}
		// 라디오 버튼 그룹화
	
		
		
		// 로그인 이후 메인패널
	
	}
	
	void MypagePanel() {
		Mainsc.removeAll();
		Mainsc.add(Mypage);
		Mainsc.add(Showing);
		Mainsc.add(goHome);
		Mainsc.add(MPscroll);
		Mainsc.add(MPscroll2);
		Mainsc.repaint();
		
		Mainsc.add(revSearch); // 예매정보조회버튼 올리기
		Mainsc.add(deleteBtn);
		
		ButtonListener myPagebt = new ButtonListener();
		
		revSearch.setBounds(380,70, 120, 25); // 예매정보조회 버튼 위치셋팅
		revSearch.addActionListener(myPagebt); // 예매정보조회 액션리스너 달기
		deleteBtn.setBounds(410, 378, 100, 25); // 예매취소버튼 위치셋팅
		deleteBtn.addActionListener(myPagebt); // 예매취소버튼 액션리스너 달기
		
		
		MPtable.setRowHeight(25);
		MPtable2.setRowHeight(25);
		MPscroll.setBounds(100, 100, 800, 274);
		MPscroll2.setBounds(100, 410, 800, 200);
		// 패널에 테이블들 행높이설정 및 위치설정
		
		for (int i=0; i<10; i++) {
			Myreserve[i] = new JRadioButton("선택" + (i+1));
			Myrevbtns.add(Myreserve[i]);
			Mainsc.add(Myreserve[i]);
			Myreserve[i].setBounds(20, 125+(i*25), 80, 25);
		} // 체크박스들 달기
		
		for (int i=0; i<7; i++) {
			Myreserve2[i] = new JRadioButton("선택" + (i+1));
			Myrevbtns2.add(Myreserve2[i]);
		} // MPtable2 옆에 달 체크박스들
	
		
	}
	
	void manegerPanel() {
		BG.setVisible(false);
		setContentPane(Managesc);
		Managesc.setLayout(null);
		Managesc.setVisible(true);
		
		Managesc.add(SQLtext);
		Managesc.add(SQLex);
		// 쿼리입력관련 컴포넌트들
		
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
		
		// 테이블보기 관련 컴포넌트들
		
		
	}
	
	public class ButtonListener implements ActionListener {
		int MyreserveNum = 0; // MPtable1 에서 체크된 예매내역에 대한 예매번호

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String st = b.getText(); // 버튼 텍스트 받아오기
			
			// 로그인 이전 시작패널에서의 버튼들
			if (st == "회원") {
				cl = new login(db_forGUI);
			} 
			else if (st == "관리자") {
				//관리자 버튼 눌렀을때
			
				manegerPanel();
				
				
			}
			// 로그인 이전 시작패널에서의 버튼들
			
			// 로그인 이후 메인패널에서의 버튼들
			else if (st == "예매") {
				Mainsc.removeAll();
				Mainsc.add(Mypage);
				Mainsc.add(Showing);
				Mainsc.add(goHome);
				Mainsc.repaint();
				// 테이블들 추가 및 사이즈 설정
				Mainsc.add(tablescroll);
				Mainsc.add(scheduleScroll);
				table.setRowHeight(25);
				tablescroll.setBounds(100, 100, 800, 274);
				ScheduleTable.setRowHeight(25);
				scheduleScroll.setBounds(100, 410, 800, 200);
				// 테이블들 추가 및 사이즈 설정
				
				// 검색용 텍스트필드와 버튼
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
				// 검색용 텍스트필드와 버튼

				// 라벨들
				Mainsc.add(mN);
				Mainsc.add(dN);
				Mainsc.add(aN);
				Mainsc.add(mT);				
				mN.setBounds(120, 30, 80, 25);
				dN.setBounds(240, 30, 80, 25);
				aN.setBounds(360, 30, 80, 25);
				mT.setBounds(480, 30, 80, 25);
				// 라벨들
				
			}
			else if (st == "마이페이지") {
				MypagePanel(); // 패널에 컴포넌트 올리기
				Vector<String> columnNames = new Vector<String>(); // 쿼리 컬럼정보 삽입용 MPtable1 전용

				String serchReserve = "Select Reservation.reserveNum, movieName, screeningStart, Schedule.theaterNum, seatNum, salePrice"
						+ " from Customer, Reservation, Movie, Schedule, Ticket"
						+ " where Customer.custId = '" + cl.getID() +"' and Reservation.custId = customer.custId  and Reservation.reserveNum = Ticket.reserveNum "
								+ "and Movie.movieNum = Schedule.movieNum and Schedule.scheduleNum = Ticket.scheduleNum;";
				// 나의 예매정보조회 쿼리
				
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
				MPtable.setModel(dtm); // 테이블에 쿼리 실행결과 넣기
				
			}
			
			else if (st == "실행") {
				String sql = SQLtext.getText();
				JOptionPane warning = new JOptionPane();

				try {
					stmt.execute(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				warning.showMessageDialog(null,"쿼리가 실행되었습니다. 테이블을 조회해주세요.");	

				
			}
			else if (st == "영화테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
				
			}
			else if (st == "스케줄테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "예약테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "티켓테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "고객테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "좌석테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "상영관테이블") {
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
				} // 테이블 데이터 벡터에 삽입

				DefaultTableModel dtm = new DefaultTableModel(tableData, colN);
				seeTable.setModel(dtm);
			}
			else if (st == "홈으로") {
				Mainsc.setVisible(false);
				Managesc.setVisible(false);
				BG.setVisible(true);
				setContentPane(BG);
			}
			
 			else if (st == "예매정보조회") {
				// 마이페이지에서 내가 선택한 예매에 대한 정보 출력 구현
				Vector<String> columnNames2 = new Vector<String>(); // Mysql의 테이블 정보 가져오기 위한 벡터들 MPtable2 전용

				for (int i=0; i<7; i++) {
					Mainsc.add(Myreserve2[i]);
					Myreserve2[i].setBounds(20, 435+(i*25), 80, 25);
				} // MPtable2 옆에 달 체크박스들
				
				
				int chkNum = 0;
				Vector data2 = new Vector();
				
				for (int i=0; i<10; i++) {
					if (Myreserve[i].isSelected()) {
						chkNum = i; // 선택된 체크박스가 몇번인지?
					}
				}
				
				MyreserveNum = Integer.parseInt(MPtable.getValueAt(chkNum, 0).toString()); // 선택한 체크박스랑 연결된 예약번호
				
				
				String viewallInfoSQL = "Select screeningStart, screeningDate, startTime, Ticket.*"
						+ " from Reservation, Schedule, Ticket"
						+ " where reservation.reserveNum = " + MyreserveNum + " and reservation.reserveNum = Ticket.reserveNum and Schedule.scheduleNum = Ticket.scheduleNum;"; // 해당 예매에 대한 정보 출력 쿼리
				
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
				} // 컬럼이름 벡터에 삽입
				
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
				} // 테이블 데이터 벡터에 삽입
				
				DefaultTableModel dtm = new DefaultTableModel(data2, columnNames2);
				MPtable2.setModel(dtm);
				
				
			}
 			
			else if (st == "예매취소") {
				int chkNum = 0;
				int chkNum2 = 0;
				int MyreserveNum = 0;
				int scheduleNum = 0;
				int seatNum = 0;
				
				String deleteSQL = "";
				String ticketDelete = "";
				
				for (int i=0; i<7; i++) {
					if (Myreserve[i].isSelected()) {
						chkNum = i; // 선택된 체크박스가 몇번인지?
					}
				}
				
				for (int i=0; i<7; i++) {
					if (Myreserve2[i].isSelected()) {
						chkNum2 = i; // 선택된 체크박스가 몇번인지?
					}
				}
				
				scheduleNum = Integer.parseInt(MPtable2.getValueAt(chkNum2, 4).toString());
				seatNum = Integer.parseInt(MPtable2.getValueAt(chkNum2, 6).toString());
				
				String updateSeat = "update seat set seatUsed = '사용가능' "
						+ " where seat.scheduleNum = " + scheduleNum + " and seatNum = " + seatNum + ";";
				
				try {
					stmt.execute(updateSeat);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						
				MyreserveNum = Integer.parseInt(MPtable.getValueAt(chkNum, 0).toString()); // 선택한 체크박스랑 연결된 예약번호
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
				
				
				// MPtable1 갱신
				
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
				
				// MPtable2 갱신
				Vector data2 = new Vector();
				Vector<String> new_columnNames2 = new Vector<String>();

				String viewallInfoSQL = "Select screeningStart, screeningDate, startTime, Ticket.*"
						+ " from Reservation, Schedule, Ticket"
						+ " where reservation.reserveNum = " + MyreserveNum + " and reservation.reserveNum = Ticket.reserveNum and Schedule.scheduleNum = Ticket.scheduleNum;"; // 해당 예매에 대한 정보 출력 쿼리
				
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
				 /////////////////////////////////////////////////////// 위의 삭제기능 테스트 필요함.
			}
			
			else if (st == "입력") {
				//////////// 검색기능
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
				
				Vector<String> columnNames = new Vector<String>(); // 필드생성
				Vector data = new Vector(); // 데이터 생성
				
				
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
				
				
				// 라디오버튼 추가 후 위치설정 
				for (int i=0; i<10; i++) {
					Mainsc.add(chk[i]);
					chk[i].setBounds(20, 125+(i*25), 80, 25);
				}				
				// 라디오버튼 추가 후 위치설정 

				// 스케줄 보기 버튼 추가 후 위치설정
				Mainsc.add(booking);
				booking.setBounds(410, 378, 100, 25);
				// 스케줄 보기 버튼 추가 후 위치설정
			
				
				//// else if 예매 버튼 끝 ////
			}
			// 로그인 이후 메인패널에서의 버튼들

			else if (st == "스케줄보기") {
				Mainsc.repaint();
				String SQL2; // 쿼리문

				Vector<String> columnNames2 = new Vector<String>(); // Mysql의 테이블 정보 가져오기 위한 벡터들
				Vector data2 = new Vector();
				
				
				int chkNum = 0;
				
				// 어떤 체크박스가 체크되었는지 체크하기
				for (int i=0; i<10; i++) {
					if (chk[i].isSelected()) {
						chkNum = i;
						break;
					}
				}
			
				int movieNumber = Integer.parseInt(table.getValueAt(chkNum, 0).toString()); // 해당하는 체크박스에 연결된 영화번호
				SQL2 = "select schedule.scheduleNum, movie.movieName,schedule.theaterNum, schedule.screeningDate, "
						+ "schedule.screeningSession, schedule.screeningStart, schedule.startTime "
						+ "from Schedule,Movie where Schedule.movieNum = Movie.movieNum and Movie.movieNum = " + movieNumber + ";"
						;
				// 쿼리문 변수에 삽입
				// 쿼리실행
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
				// 쿼리실행
				
				// 쿼리 실행후 컬럼네임 벡터에 rsMeta의 메소드로 컬럼이름들 삽입
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
				
				// 라디오버튼 추가 후 위치설정 
				for (int i=0; i<7; i++) {
					Mainsc.add(res[i]);
					res[i].setBounds(20, 435+(i*25), 80, 25);
				}				
				// 라디오버튼 추가 후 위치설정 
				
				
				//// 스케줄보기 else if문 끝 ////
			}
			
			else if (st == "예매하기") {
				int schNum = 1;
				
				for (int i=0; i<10; i++) {
					if (res[i].isSelected()) {
						schNum = i;
						break;
					}
				}
			
				int schNumber = Integer.parseInt(ScheduleTable.getValueAt(schNum, 0).toString()); // 해당하는 체크박스에 연결된 스케줄번호
				int theNumber = Integer.parseInt(ScheduleTable.getValueAt(schNum, 2).toString()); // 해당하는 체크박스에 연결된 상영관번호
				
				new movie_reservation(db_forGUI, schNumber, theNumber);
				
			}
			
		}
		
	}
}
