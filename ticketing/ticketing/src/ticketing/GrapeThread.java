package ticketing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GrapeThread extends Thread {
   JButton[][] grape;
     int[][] temp;
     int[][] tgrape;
     int k;
     static int level; //좌석 사라지는 속도 설정하는 난이도 변수
     static boolean pick = false; // 이선좌 여부
     boolean loop = true; // 최초 한 번은 검사
     boolean gameover = false;
     static int [] alreadyx;
     static int [] alreadyy;
     int deleteindex =-1;
     String tempgrape;
     static int twosun; // 이선좌 검사 위한 선택된 좌석 or 사라진 좌석 갯수 세는 변수
     
   public GrapeThread(JButton[][] grape) {
      this.grape = grape;
        k = 0;
        temp = new int[20][12];
        tgrape = new int[20][12];
        alreadyx = new int[4];
        alreadyy = new int[4];
        
        for(int i = 0 ; i<4 ; i++) {
           alreadyx[i] = -1;
           alreadyy[i] = -1;        
        } // 이선좌 없을 때의 기본값은 -1
        
        for(int i = 0; i<20;i++) {
           for(int j = 0; j<12 ; j++) {
              temp[i][j]=500;
              tgrape[i][j]= k++;
           }
        }
   }
   
   public void alreadySelected(int a, int b) {
        for (int i = 0 ; i< Purchaser.seatName.size() ; i++) {
           if (!Purchaser.seatName.isEmpty() && a == Purchaser.seatX.get(i) && b == Purchaser.seatY.get(i)) { //이미 선택한 좌석의 값을 뽑은 경우
               pick = true;// 이선좌 메세지 띄우기 위한 플래그 설정 - 한 번이라도 있었으면 메세지 띄워야 하니까
               loop = true; // 이선좌 루프를 위한 플래스 설정
                alreadyx[i] = a; //지우려는 좌석의 X값을 alreadyx에 저장
                alreadyy[i] = b; //지우려는 좌석의 Y값을 alreadyy에 저장
           }
        } // 이선좌 검사        
   } // 이선좌 검사 함수
   
   @Override
      public void run() {
        int x=0;
        int y=0;       
         while(true) {
        	 while (loop || temp[x][y] == tgrape[x][y]) { //이선좌일 때 or 이미 지운 좌석일 때
                   // 다른 좌석이 사라지도록 랜덤 다시 뽑기
                    x = (int)(Math.random()*20);
                    y = (int)(Math.random()*12);
                    loop = false; // 검사 전에는 이선좌 여부 false로 세팅해두기
                    alreadySelected(x,y); // 뽑고 다시 이선좌 검사
                } 
                twosun =0;
                for(int i = 0; i<20;i++) { // 내가 선택함 or 사라짐이면 twosun +1
                    for(int j = 0; j<12 ; j++) {
                       if(grape[i][j].getBackground().equals(Color.GRAY) || grape[i][j].getBackground().equals(new Color(253,204,102))) {
                          twosun++;
                       }
                    }
                 }
                if(twosun >= 20*12-1 && Seat.selectedseat != Seat.enableseat) { //모든 좌석이 선택되거나 사라진 상태이고, 내가 선택해야 하는 자리만큼 선택하지 못하면
                   gameover = true;
                }
                
                temp[x][y] = tgrape[x][y]; // 뽑은 숫자들은 임시 배열 값을 set, 중복 삭제 방지
                 
                 grape[x][y].setEnabled(false); 
                 grape[x][y].setBackground(Color.GRAY);
                 
                 //////////////////////////////게임오버
                 int result = 100;
                 if(gameover) { // 좌석 사라진 뒤에 메세지 떠야하기 때문에 
                    result = JOptionPane.showConfirmDialog(null, "게임 오버 \n 처음부터 다시 하시겠습니까?", "일석이조 티켓예매", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) { // 재시도 모든 변수 값 비우기
                       Ticketing.page = 0; Seat.selectedseat = 0; Seat.enableseat = 0;
                       Purchaser.seatName.clear(); Purchaser.seatX.clear(); Purchaser.seatY.clear();
                       gameover = false; pick = false; 
                       for(int i = 0; i<20;i++) {
                           for(int j = 0; j<12 ; j++) {
                              temp[i][j] = 500;
                              tgrape[i][j]= k++;
                           }
                        }
                         for(int i = 0 ; i<4 ; i++) {
                            alreadyx[i] = -1;
                            alreadyy[i] = -1;        
                         } // 이선좌 없을 때의 기본값은 -1
                       new SelectCon1();
                       return;
                    }else  {
                       System.exit(0); //프로그램 종료
                    }
                 }         
                 
               try {
                   Thread.sleep(level);
               }
               catch(InterruptedException e) {
                  return;
               }
         }
     }
 }
   