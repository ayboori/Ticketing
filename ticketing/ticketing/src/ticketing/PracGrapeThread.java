package ticketing;

import java.awt.Color;

import javax.swing.JButton;

public class PracGrapeThread extends Thread {
	JButton[][] grape;
	 int[][] temp;
	 int[][] tgrape;
	 int k=0; // 임시 배열 값 세팅 위한 변수
	 boolean loop = true;
 	 int x=0;
 	 int y=0;       
   
   public PracGrapeThread(JButton[][] grape) {
      this.grape = grape;
      temp = new int[20][12];
      tgrape = new int[20][12];
      
      for(int i = 0; i<20;i++) {
          for(int j = 0; j<12 ; j++) {
             temp[i][j] = 500;
             tgrape[i][j]= k++;
          }
       }
   }
	public void alreadySelected(int a, int b) {
        for (int i = 0 ; i< Purchaser.seatName.size() ; i++) {
        	if (!Purchaser.seatName.isEmpty() && a == Purchaser.seatX.get(i) && b == Purchaser.seatY.get(i)) { //이미 선택한 좌석의 값을 뽑은 경우
            	loop = true; // 이선좌 루프를 위한 플래스 설정
        	}
        } // 이선좌 검사        
	} // 이선좌 검사 함수
      
      @Override
      public void run() {
         while(true) {    
                while (loop || temp[x][y] == tgrape[x][y]) { //이선좌일 때 or 이미 지운 좌석일 때
                	// 다른 좌석이 사라지도록 랜덤 다시 뽑기
                    x = (int)(Math.random()*20);
                    y = (int)(Math.random()*12);
                    loop = false; // 검사 전에는 이선좌 여부 false로 세팅해두기
                    alreadySelected(x,y); // 뽑고 다시 이선좌 검사
                }

             temp[x][y] = tgrape[x][y]; // 뽑은 숫자들은 임시 배열 값을 set, 중복 삭제 방지
             
             grape[x][y].setEnabled(false);
             grape[x][y].setBackground(Color.GRAY);
                 
               try {
                   Thread.sleep(1000);
               }
               catch(InterruptedException e) {
                  return;
               }        
         }
      }
 }
   