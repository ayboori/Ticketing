package ticketing;

import java.awt.Color;
import java.util.ArrayList;

public class Purchaser {
	static String name; //�̸�
	static String num; //��ȭ��ȣ
	static String email; //�̸���
	static String realPayOp; // ���������� ������ ���� �ɼ�
	static String accountnum; // ���¹�ȣ
	static String bank; //������ �Ա��� �� ����
	static String card; //ī���
	
	static String reservnum; // �����ȣ
	static String perform; // ����
	static String performplace;
	static String performdate; // ���� ��¥
	static String performprice; // ���� ����
	
	static Color praccolor = Color.decode("#18A8F1");; // ������� �۾� ��
	
	static ArrayList<String> seatName = new ArrayList<String>(Seat.enableseat);
	static ArrayList<Integer> seatX = new ArrayList<Integer>(Seat.enableseat);
	static ArrayList<Integer> seatY = new ArrayList<Integer>(Seat.enableseat);
		
	static void SetSeats(String tempseat) {
		seatName.add(tempseat);
	}
}
