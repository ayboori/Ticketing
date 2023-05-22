package ticketing;

import java.awt.Color;
import java.util.ArrayList;

public class Purchaser {
	static String name; //이름
	static String num; //전화번호
	static String email; //이메일
	static String realPayOp; // 최종적으로 저장할 결제 옵션
	static String accountnum; // 계좌번호
	static String bank; //무통장 입금일 시 은행
	static String card; //카드사
	
	static String reservnum; // 예약번호
	static String perform; // 공연
	static String performplace;
	static String performdate; // 공연 날짜
	static String performprice; // 공연 가격
	
	static Color praccolor = Color.decode("#18A8F1");; // 연습모드 글씨 색
	
	static ArrayList<String> seatName = new ArrayList<String>(Seat.enableseat);
	static ArrayList<Integer> seatX = new ArrayList<Integer>(Seat.enableseat);
	static ArrayList<Integer> seatY = new ArrayList<Integer>(Seat.enableseat);
		
	static void SetSeats(String tempseat) {
		seatName.add(tempseat);
	}
}
