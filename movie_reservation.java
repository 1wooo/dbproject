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
	
	JButton confirm = new JButton("Ȯ��");
	
	JComboBox payMethods;
	
	JLabel label = new JLabel("�¼��� �����ϰ� Ȯ���� �����ּ���");
	JLabel sc = new JLabel("Screen");
	
	int schNumber = 0;
	int theaterNumber = 0;
	
	public movie_reservation(DB db, int scheduleNumber, int theaterNum) {
		schNumber = scheduleNumber; // ������ ������ ��ȣ
		theaterNumber = theaterNum; // ������ ������ ��ȣ�� �ش��ϴ� �󿵰���ȣ
		rs = db.res;
		stmt = db.stmt;
		
		setTitle("����ý���");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(reserveFrame);
		reserveFrame.setLayout(null);
		
		// üũ�ڽ� ����
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
		
		String [] pay = {"����(7000��)", "ī��(8000��)"};
		payMethods = new JComboBox(pay);
		setCombo();
		
		setSize(500,350);
		setVisible(true);
	}
	
	void setSeat() {
		int H = 120; // ������ư ����
		int W = 115;
		String selectuseudSeat = "select seatUsed, seatNum from seat where seat.scheduleNum = " + schNumber + ";";
		try {
			rs = stmt.executeQuery(selectuseudSeat);
		} catch (SQLException e) {
			e.printStackTrace();
		} // �̹� ����� ��Ʈ�� �ɷ����� ���� �۾� ����

		
		
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
				if (rs.getString(1).equals("�����")) {
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
			
			
			if (st == "Ȯ��") {
				
				// �������̺� ������ INSERT ������ �ۼ����� /////////////////
				int MaxresNum = 0;
				String getMaxResQR = "select MAX(reserveNum) from reservation;"; // ������
				String InsertQR = ""; // �������̺� ���� ����
				String payMethod = payMethods.getSelectedItem().toString(); // ������� �޺��ڽ� ���ð�
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
				// �����ȣ �ִ� ã�� �������� ///////
				
				try {
					rs.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// ���������� ù��° ������ �ѱ�� ���� next �޼ҵ� ����
				
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
				// ���ο� �����ȣ ����
				
				if (payMethod.equals("ī��(8000��)")) {
					status = "�����Ϸ�";
					ticketChk = "�߱ǿϷ�";
					payAmount = 8000;
				}
				else if (payMethod.equals("����(7000��)")) {
					status = "�������";
					ticketChk = "�߱Ǵ��";
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
				} // ���� ���̺� ������ INSERT ������ ����Ϸ� /////////////////////
				
				
				// Ƽ�����̺� ������ INSERT ������ �ۼ����� //////////////////
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
				} // Ƽ�ϳѹ� �ִ� ã�� ���� ���� ////
				
				try {
					rs.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// ���������� ù��° ������ �ѱ�� ���� next �޼ҵ� ����
				
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
				// ���ο� Ƽ�Ϲ�ȣ ����
				
				for (int j=0; j<15; j++) {
					if (seat[j].isSelected()) {
						seatNum = j+1;
						break;
					}
				} // ���õ� �¼���ȣ �޾ƿ���
				
				InsertTicNum = "INSERT INTO ticket VALUES(" + Maxticket + ", "
							 + schNumber + ", " + theaterNumber + ", " + seatNum + ", "
							 + MaxresNum + ", " + "'" + ticketChk + "'" + ", " + stdPrice + ", "
							 + salePrice + ");";
				
				try {
					stmt.execute(InsertTicNum);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} // Ƽ�����̺� INSERT ������ ���� �Ϸ� ////////////////////////////
				
				String updateSeat = "update seat set seatUsed = '�����' where seat.scheduleNum = " + schNumber + " and seatNum = " + seatNum +";";
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
