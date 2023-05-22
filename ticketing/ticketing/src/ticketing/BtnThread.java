package ticketing;

import javax.swing.*;

//예매하기 버튼 숫자 감소 스레드
public class BtnThread extends Thread {
	int timer;
	JButton timerButton;
	JRadioButton[] levelR;
	boolean practicemode = false;
	Seat seat;
	
   public BtnThread(JButton timerButton, JRadioButton[] levelR) {
	   this.timerButton = timerButton;
	   this.levelR = levelR;
   }
   
   public Seat returnSeat() {
       return seat;
   }
   
   public void SetLevel(int level) {
	   if (level ==1000) {
		   practicemode = true;
	   }
   }
   
   @Override
   public void run() {
      timer = 10;
      while(true) {
         if(timer >0) { //예매시간 되기 전 글씨 출력
            timerButton.setText("예매 시간 " + Integer.toString(timer) + "초 전");
            timer--;    
         }
         else { // 예매하기 시간이 되었을 경우
            timerButton.setText("예매하기");
            timerButton.setEnabled(true); // 예매시간 되었을 떄 버튼 활성화         
            for(int i=0; i<3 ;i++) {
            	levelR[i].setEnabled(false); 
            }// 예매하기 활성화되면 난이도 변경 불가
            
            if(practicemode) {
            	seat = new Seat(); // 좌석 객체 여기서 생성 : 예매하기 버튼 오랫동안 안 누르면 좌석이 사라져 있도록 하기
               	PracGrapeThread pgth = new PracGrapeThread(seat.grape);
            	pgth.start();
            }else {
            	seat = new Seat(); // 좌석 객체 여기서 생성 : 예매하기 버튼 오랫동안 안 누르면 좌석이 사라져 있도록 하기
             	GrapeThread gth = new GrapeThread(seat.grape);
       	 		gth.start();
            }
       	 	return;
         }

         try {
             Thread.sleep(1000);
         }
         catch(InterruptedException e) {
            return;
         }        
     }
  }
}