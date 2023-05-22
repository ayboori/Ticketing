//Bars

package ticketing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bars {
   // 상단에 현재 진행 단계를 보여주는 바 그림
   static class UpperBar extends JPanel{
      int x = 10; // 앞 부분 공백 크기 저장 변수
      int y = 50; // 윗 부분 공백 크기 지정 변수
      int w = 970/5;; // 네모의 가로 너비를 지정하는 변수
      int h = 50; // 네모의 세로 길이 저장하는 변수
      
      Color highlight = new Color(253,204,102); //하이라이트 색은 오렌지
      Color normal = new Color(240,240,240); //바탕색은 옅은 회색
      Color text = Color.BLACK;
      Color [] c = {normal,normal,normal,normal,normal};
      
      public void paintComponent(Graphics g) {         
         // page 값에 따라, 현재 페이지의 배경 색을 하이라이트색, 현재 페이지가 아닌 색을 노멀색으로 변경
         switch(Ticketing.page) {
            case 0: c[0] = highlight;
               break;
            case 1: c[0] = normal;
                  c[1] = highlight;
                  c[2] = normal;                           
               break;
            case 2: c[1] = normal;
                  c[2] = highlight;
                  c[3] = normal;                  
               break;
            case 3: c[2] = normal;
                  c[3] = highlight;
                  c[4] = normal;
               break;
            case 4: c[3] = normal;
                  c[4] = highlight;
               break;
         }
         
         super.paintComponent(g);
                  
         // 상단 로고 출력
         g.setColor(Color.GRAY); // 로고 글씨는 회색으로 설정
         g.setFont(new Font("맑은 고딕",Font.BOLD,25));// 로고의 글씨체, 굵기, 크기 설정
         g.drawString("일석이조 티켓예매",10,40); // 로고 출력
      
         g.setFont(new Font("맑은 고딕",Font.BOLD,20));// 단계 바의 글씨체, 굵기, 크기 설정
         
         // 단계 바 그림
         g.setColor(c[0]);
         g.fillRect(x, y, w, h); // 꽉 찬 네모 그림
         g.setColor(text); //글씨와 같은 색으로 테두리 색상 설정
         g.drawRect(x, y, w, h); // 테두리 그림
         g.drawString("날짜 선택",x+50,y+32); // 글씨 출력

         g.setColor(c[1]);
         g.fillRect(w +x, y, w, h);
         g.setColor(text);
         g.drawRect(w +x, y, w, h);
         g.drawString("좌석 선택",w+x+50,y+32);

         g.setColor(c[2]);
         g.fillRect(w*2 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*2 + x, y, w, h);
         g.drawString("예매자 정보 입력",w*2 +x+20,y+32);

         g.setColor(c[3]);
         g.fillRect(w*3 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*3 + x, y, w, h);
         g.drawString("결제 정보 입력",w*3 +x+30,y+32);

         g.setColor(c[4]);
         g.fillRect(w*4 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*4 + x, y, w, h);
         g.drawString("결제 완료",w*4 +x+50,y+32);
         // 위의 switch에서 설정한 값에 따라 함수 호출 시마다 다른 색상 그림      
         }
   }
   
   static class RightBar extends JPanel{
       //이미지 출력
      ImageIcon schicago = new ImageIcon("Schicago.png");//작은 시카고 이미지
      ImageIcon srebecca = new ImageIcon("Srebecca.png");//작은 레베카 이미지
      
      //이미지 객체
      Image Cimg = schicago.getImage();
      Image Rimg = srebecca.getImage();
      
      public void paintComponent(Graphics g){
         
         g.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
               
         //이미지 출력 및 추가적인 내용 출력
         if(Purchaser.perform == "시카고") {
            g.drawImage(Cimg,0,0,150,130,this);
            g.drawString(Purchaser.performdate,150,35);
            g.drawString("~11월 7일 19시 30분", 150, 55);
            g.drawString("충무아트센터 대극장",150,80);
            g.drawString("14세 이상 관람 가능",150,100);
            g.drawString("관람시간: 145분",150,120);
         }
         else if (Purchaser.perform == "레베카") {
            g.drawImage(Rimg,0,0,150,130,this);
            g.drawString(Purchaser.performdate,150,35);
            g.drawString("~11월 7일 19시 30분", 150, 55);
            g.drawString("디큐브아트센터",150,80);
            g.drawString("8세 이상 관람 가능",150,100);
            g.drawString("관람시간: 170분",150,120);
         }
         
         //My예매 정보 출력
         g.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
         g.drawString("My예매정보",0,170);
         
         g.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));

         //일자 출력
         g.drawString("공연 일자:  "+Purchaser.performdate,0,200);
         
         if(Seat.selectedseat== 0) {
                g.drawString("좌석:", 0, 225);
         } 
         else {
            if(Purchaser.seatName.isEmpty()) {
                g.drawString("좌석:", 0, 225);            	
            }else {
            	for(int number=0; number<Seat.selectedseat; number++) {
                    if(number == 0) {
                           g.drawString("좌석:"+Purchaser.seatName.get(number), 0, 225);
                    }
                    else {
                          g.drawString("       "+Purchaser.seatName.get(number), 0, 225+(number*25));
                    }
                 }
            }
         }
         
         //가격 출력
         g.drawString("가격:  "+Purchaser.performprice,0,250+25*3);
         
         //예매자 이름 출력S
         g.drawString("예매자:  ",0,275+25*3);
         //이메일
         g.drawString("E-Mail:",0,300+25*3);
         
         
         if(Ticketing.page == 3) {
            //Name.setVisible(false);
            g.drawString("예매자:  "+Purchaser.name,0,275+25*3);
            g.drawString("E-Mail:  "+Purchaser.email,0,300+25*3);
         }
      }
   }
}