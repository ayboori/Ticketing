package ticketing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ticketing.Bars;

public class Seat extends JPanel {
   static int selectedseat = 0; // 선택된 좌석의 갯수
   static int enableseat = 1; // 선택할 수 있는 좌석의 갯수, 초기값 1
   static int exClickCnt = 0;
   
   public String seatnum;
   JPanel seatPanel;
   JPanel grapePanel;
   SGuide sgui;
   JLabel seatgui;
   JLabel seatnextgui;
   JLabel seatcancelgui;
   Bars.RightBar rbar = new Bars.RightBar();//우측바
   JButton [][] grape;
   static boolean clk = false;

   public Seat() {
      
      seatPanel = new JPanel();
      seatPanel.setBounds(0,150,800,800); // 패널이 부착될 위치
      seatPanel.setLayout(null);
      
      JLabel stage = new JLabel("STAGE"); //예매자 정보 글씨 출력
      stage.setSize(200,50);
      stage.setLocation(130+100,30);
      stage.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
      
      rbar.setBounds(590+100, 0, 800, 460);//추기
      
      grape = new JButton[20][12];
      
      for (int i = 0; i < 20; i++) {
         for (int j = 0; j < 12; j++) {
            
            grape[i][j] = new JButton();
            
            seatPanel.add(grape[i][j]);    
            
            grape[i][j].setSize(17,17);
            grape[i][j].setBackground(new Color(163,73,164));
            
               grape[i][j].setLocation(100+22*i ,130+22*j);

               Actions ac = new Actions(i, j);
               grape[i][j].addActionListener(ac);
         }
      }  
      
      seatPanel.add(stage);
      seatPanel.add(rbar);
      
      if (GrapeThread.level == 1000) { //연습단계일 때
          sgui = new SGuide();
          sgui.setBounds(0,0,600,400);
          seatPanel.add(sgui);
          sgui.setVisible(true);
          
          seatgui = new JLabel("좌석을 선택하세요!!");
          seatgui.setBounds(450, 120+280, 200, 40);
          seatgui.setForeground(Purchaser.praccolor);
          seatPanel.add(seatgui);
          seatgui.setVisible(true);
          
          seatcancelgui = new JLabel("선택한 좌석을 취소해보세요!");
          seatcancelgui.setBounds(450, 120+280, 200, 40);
          seatcancelgui.setForeground(Purchaser.praccolor);
          seatPanel.add(seatcancelgui);
          seatcancelgui.setVisible(false);
          
          seatnextgui = new JLabel("<html>좌석을 모두 선택하고<br/>다음단계를 누르세요↓</html>");
          seatnextgui.setBounds(700,480,200,50);
          seatnextgui.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
          seatnextgui.setForeground(Purchaser.praccolor);
          seatPanel.add(seatnextgui);
          seatnextgui.setVisible(false);
      }
    }
   
   class SGuide extends JPanel {
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(90, 120, 455, 280);
         g.clearRect(95,125,445,270);
      }
    }
   

class Actions implements ActionListener {
   String name;
   int x,y;
   String alpha;
   int beta;
   int ClickCnt = 0;
   
   public Actions(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      JButton grape = (JButton)e.getSource();
      switch(y) {
      case 0:
         alpha = "A" ; break;
      case 1:
              alpha = "B" ; break;
      case 2:
              alpha = "C" ; break;
      case 3:
              alpha = "D" ; break;
      case 4:
              alpha = "E" ; break;
      case 5:
              alpha = "F" ; break;
      case 6:
              alpha = "G" ; break;
      case 7:
              alpha = "H" ;  break;
      case 8:
              alpha = "I" ; break;
      case 9:
              alpha = "J" ; break;
      case 10:
              alpha = "K" ; break;
      case 11:
              alpha = "L" ; break;
    } 
              beta = x+1;
              
              seatnum = alpha + "열 " + Integer.toString(beta) + "번 ";
      
      ClickCnt++; // 좌석 선택하고, 취소하고
      exClickCnt++;
      
      if (Seat.selectedseat < Seat.enableseat) {
          
      if (ClickCnt % 2 == 1) { // 좌석 선택
           
            Purchaser.seatName.add(seatnum);
            Purchaser.seatX.add(x);
            Purchaser.seatY.add(y);
            
            grape.setBackground(new Color(253,204,102));
            
            Seat.selectedseat++; // 선택된 좌석 갯수 +1
            clk = true;
               
            seatPanel.repaint();
            
            if (GrapeThread.level == 1000) { //(연습모드)
               if (exClickCnt == 1) { //맨처음 좌석 선택했을때
                  seatgui.setVisible(false); //"좌석을 선택하세요!!" 지우기
                  seatcancelgui.setVisible(true); //"선택한 좌석을 취소하세요!!" 띄우기
               } else { //그 이후에 선택하면
                  sgui.setVisible(false); //빨간박스 지우기
                  seatgui.setVisible(false); //"좌석을 선택하세요!!" 지우기
                  seatnextgui.setVisible(true); //다음단계ㄱㄱ 띄우기
               }
            }
      } else if (ClickCnt % 2 == 0) { // 좌석 취소

          Purchaser.seatX.remove(Purchaser.seatName.indexOf(seatnum));
          Purchaser.seatY.remove(Purchaser.seatName.indexOf(seatnum));
         Purchaser.seatName.remove(seatnum);
         
         grape.setBackground(new Color(163,73,164));
         
         Seat.selectedseat--; // 선택된 좌석 갯수 -1
         clk = false;
         GrapeThread.twosun--;
         
         seatPanel.repaint();
         
         if (GrapeThread.level == 1000) { //(연습모드) 취소했을때
            sgui.setVisible(true); //빨간박스 띄워져있으셈, 혹은 띄우기
             seatcancelgui.setVisible(false); //"선택한 좌석을 취소하세요!!" 지우기
             seatgui.setVisible(true); //"좌석을 선택하세요!!" 띄우기
             seatnextgui.setVisible(false); //다음단계ㄱㄱ 지우기
         }
      }
     } else if (Seat.selectedseat == Seat.enableseat) {
        
        
        if (ClickCnt % 2 == 1) {
           
           JOptionPane.showMessageDialog(null, "좌석 선택 갯수를 초과하였습니다.","좌석 초과", JOptionPane.ERROR_MESSAGE);
           ClickCnt--; // 선택 안 한 상태니까
           
        } else if (ClickCnt % 2 == 0) {
           
            Purchaser.seatX.remove(Purchaser.seatName.indexOf(seatnum));
            Purchaser.seatY.remove(Purchaser.seatName.indexOf(seatnum));
            Purchaser.seatName.remove(seatnum);
             
             grape.setBackground(new Color(163,73,164));
             
             Seat.selectedseat--; // 선택된 좌석 갯수 -1
             clk = false;
             GrapeThread.twosun--;
             
             seatPanel.repaint();
             
             if (GrapeThread.level == 1000) { // 좌석 취소했을때
                sgui.setVisible(true); //빨간박스 띄워져있으셈, 혹은 띄우기
                 seatcancelgui.setVisible(false); //"선택한 좌석을 취소하세요!!" 지우기
                 seatgui.setVisible(true); //"좌석을 선택하세요!!" 띄우기
                 seatnextgui.setVisible(false); //다음단계ㄱㄱ 지우기
             }
           
        }
     }
   }
 }

}