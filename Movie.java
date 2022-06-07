package DBPR;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

	
public class Movie extends JFrame{

	static GUI mv;
	static String today = "2021-12-20";
	
	public static void main(String args[]) {
		
		DB db = new DB();

		
		mv = new GUI(db);

	}
}


class DB {
	Connection con = null;
	Statement stmt = null;
	Statement stmt2 = null;
	ResultSet res = null;
	ResultSetMetaData Meta = null;

	
	String username = "madang";
	String password = "madang";
	String url = "jdbc:mysql://localhost:3306/madang";
	
	public DB() {

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			System.out.println("DB 연결 성공");
			getseat();

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
		
		
	}
	
	public void inSert(String sql) {
		try {
			stmt2.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void getseat() {
		String upDateDB = "";
		
		String getSeat = "select * from Seat;";
		String getscheduleNum = "select MAX(scheduleNum) from schedule;";
		boolean resofseat = true;
		int resofSchedule = 0; // 스케줄넘버 최대값 저장용
		
		try {
			res = stmt.executeQuery(getSeat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resofseat = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} // 빈 테이블이면 false 리턴
		
		if (resofseat == false) {
		
			try {
				res = stmt.executeQuery(getscheduleNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				res.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				resofSchedule = Integer.parseInt(res.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 스케줄넘버 최댓값 가져오기
			
			String getThSQL =  "select theaterNum from schedule order by scheduleNum;";
			int getTheaterNum = 0;
			
			try {
				res = stmt.executeQuery(getThSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

			for (int i=1; i<=resofSchedule; i++) {
				try {
					res.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					try {
						getTheaterNum = Integer.parseInt(res.getString(1));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				for (int j=1; j<=15; j++) {
					upDateDB = "INSERT INTO Seat VALUES(" + i  + "," + j + "," + getTheaterNum + "," +"'사용가능');";
					//System.out.println(upDateDB);
					inSert(upDateDB);
				}
			}
			
			String scheduleNum = "Select scheduleNum, theaterNum from schedule order by scheduleNum;";
			
			int [] theaterNum = new int[10];
			try {
				res = stmt.executeQuery(scheduleNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

			for (int i=0; i<10; i++) {
				try {
					res.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					theaterNum[i] = Integer.parseInt((res.getString(2)));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}

			}
			
			String intoQR = "";
			String ticketchk = "select payMethod from Reservation group by reserveNum;";
			String [] payMethods = new String[10];
			String [] tchk = new String[10];
			int [] salePrice = new int[10];
			int cardPrice = 8000;
			int cashPrice = 7000;
			
			try {
				res = stmt.executeQuery(ticketchk);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			for (int i=0; i<10; i++) {
				try {
					res.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					payMethods[i] = res.getString(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 결제방법 삽입
			
			for (int i=0; i<10; i++) {
				if (payMethods[i].equals("카드(8000)")) {
					tchk[i] = "발권완료";
					salePrice[i] = cardPrice;
				}
				else if (payMethods[i].equals("현금(7000)")) {
					tchk[i] = "발권대기";
					salePrice[i] = cashPrice;
				}
			} // 발권현황 배열에 삽입
			
			
			
			for (int i=0; i<10; i++) {
				
				String firstSeatUpdate = "update seat set seatUsed = '사용중' where seat.scheduleNum = " + (i+1) + " and seatNum = " + (i+1) +";";
				try {
					stmt.execute(firstSeatUpdate);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				intoQR = "INSERT INTO Ticket VALUES(" + (i+1) + ", " + (i+1) + ", " + theaterNum[i] 
						+ ", " + (i+1) + ", " + (i+1) + ", " + "'" + tchk[i] + "'" + ", " + 8000
						+ ", " + salePrice[i] + ");";
				
				try {
					stmt.execute(intoQR);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

			} // 티켓 테스트테이블 삽입
			
		}
		
		
	}
}