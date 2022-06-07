package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class movie_reservation extends JFrame{
	
	ResultSet rs = null;
	Statement stmt = null;
	
	JPanel reserveFrame = new JPanel();
	
	JRadioButton [] seat = new JRadioButton[15];
	ButtonGroup seatchks = new ButtonGroup();
	
	JButton confirm = new JButton("확인");
	
	JComboBox payMethods;
	
	JLabel label = new JLabel("좌석을 선택하고 확인을 눌러주세요");
	JLabel sc = new JLabel("Screen");
	
	int schNumber = 0;
	int theaterNumber = 0;
	
	public movie_reservation(DB db, int scheduleNumber, int theaterNum) {
		schNumber = scheduleNumber; // 예매할 스케줄 번호
		theaterNumber = theaterNum; // 예매할 스케줄 번호에 해당하는 상영관번호
		rs = db.res;
		stmt = db.stmt;
		
		setTitle("예약시스템");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(reserveFrame);
		reserveFrame.setLayout(null);
		
		// 체크박스 생성
		for (int i=0; i<15; i++) {
			int seatnum = i+1;
			
			seat[i] = new JRadioButton(Integer.toString(seatnum));
			seatchks.add(seat[i]);

		}
		
		reserveFrame.add(confirm);
		setSeat();
		setBtn();
		setLabel();
		
		ButtonListener btnA = new ButtonListener();
		
		confirm.addActionListener(btnA);
		
		String [] pay = {"현금(7000원)", "카드(8000원)"};
		payMethods = new JComboBox(pay);
		setCombo();
		
		setSize(500,350);
		setVisible(true);
	}
	
	void setSeat() {
		int H = 120; // 라디오버튼 높이
		int W = 115;
		String selectuseudSeat = "select seatUsed, seatNum from seat where seat.scheduleNum = " + schNumber + ";";
		try {
			rs = stmt.executeQuery(selectuseudSeat);
		} catch (SQLException e) {
			e.printStackTrace();
		} // 이미 예약된 시트를 걸러내기 위한 작업 시작

		
		
		for (int i=0; i<15; i++) {
			reserveFrame.add(seat[i]);
			seat[i].setBounds(W, H, 40, 25);
			W += 45;
			
			try {
				rs.next();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				if (rs.getString(1).equals("사용중")) {
					seat[i].setEnabled(false);
				}
				
				if ((i+1)%5 == 0) {
					H += 25;
					W = 115;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void setBtn() {
		reserveFrame.add(confirm);
		confirm.setBounds(380, 220, 80, 30);
	}
	
	void setLabel() {
		reserveFrame.add(label);
		reserveFrame.add(sc);
		
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setForeground(Color.BLUE);
		
		sc.setBounds(198, 80, 100, 40);
		label.setBounds(151, 10, 200, 40);
	}
	
	void setCombo() {
		reserveFrame.add(payMethods);
		payMethods.setBounds(115, 220, 180, 25);
	}
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton tmp = (JButton)e.getSource();
			String st = tmp.getText();
			
			
			if (st == "확인") {
				
				// 예약테이블에 적용할 INSERT 쿼리문 작성시작 /////////////////
				int MaxresNum = 0;
				String getMaxResQR = "select MAX(reserveNum) from reservation;"; // 쿼리문
				String InsertQR = ""; // 예약테이블 삽입 쿼리
				String payMethod = payMethods.getSelectedItem().toString(); // 결제방법 콤보박스 선택값
;				String status = "";
				String custId = Movie.mv.cl.getID();
				String payDate = Movie.today;
				String ticketChk = "";
				int payAmount = 0;
								
				try {
					rs = stmt.executeQuery(getMaxResQR);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 예약번호 최댓값 찾는 쿼리실행 ///////
				
				try {
					rs.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 쿼리실행후 첫번째 행으로 넘기기 위해 next 메소드 실행
				
				try {
					if (rs.getString(1) == null) {
						MaxresNum = 1;
					}
					else {
						MaxresNum = rs.getInt(1) + 1;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 새로운 예약번호 생성
				
				if (payMethod.equals("카드(8000원)")) {
					status = "결제완료";
					ticketChk = "발권완료";
					payAmount = 8000;
				}
				else if (payMethod.equals("현금(7000원)")) {
					status = "결제대기";
					ticketChk = "발권대기";
					payAmount = 7000;
				}
				
				InsertQR = "INSERT INTO Reservation VALUES(" + MaxresNum +", " 
						+ "'" + payMethod + "'"
						+", " + "'" + status + "'" + ", " + payAmount + ", "
						+ "'" + custId + "'" + ", " + "STR_TO_DATE('" + payDate + "'" 
						+", " + "'%Y-%m-%d'));";
				
				try {
					stmt.execute(InsertQR);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // 예약 테이블에 적용할 INSERT 쿼리문 실행완료 /////////////////////
				
				
				// 티켓테이블에 적용할 INSERT 쿼리문 작성시작 //////////////////
				int Maxticket = 0;
				int seatNum = 0;
				int stdPrice = 8000;
				int salePrice = payAmount;
				String getMaxtic = "select MAX(ticketNum) from ticket;";
				String InsertTicNum = "";
				
				try {
					rs = stmt.executeQuery(getMaxtic);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 티켓넘버 최댓값 찾는 쿼리 실행 ////
				
				try {
					rs.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 쿼리실행후 첫번째 행으로 넘기기 위해 next 메소드 실행
				
				try {
					if (rs.getString(1) == null) {
						Maxticket = 1;
					}
					else {
						Maxticket = rs.getInt(1) + 1;
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 새로운 티켓번호 생성
				
				for (int j=0; j<15; j++) {
					if (seat[j].isSelected()) {
						seatNum = j+1;
						break;
					}
				} // 선택된 좌석번호 받아오기
				
				InsertTicNum = "INSERT INTO ticket VALUES(" + Maxticket + ", "
							 + schNumber + ", " + theaterNumber + ", " + seatNum + ", "
							 + MaxresNum + ", " + "'" + ticketChk + "'" + ", " + stdPrice + ", "
							 + salePrice + ");";
				
				try {
					stmt.execute(InsertTicNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // 티켓테이블 INSERT 쿼리문 실행 완료 ////////////////////////////
				
				String updateSeat = "update seat set seatUsed = '사용중' where seat.scheduleNum = " + schNumber + " and seatNum = " + seatNum +";";
				try {
					stmt.execute(updateSeat);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				dispose();
				
			}
			
		}
		
	}
}
