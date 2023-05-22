package ticketing;

import javax.swing.*;

import ticketing.Pay.MyItemListener;

import java.awt.*;
import java.awt.event.*;

class DateSelect extends JPanel {
   String[] date = {"11월 5일 19시 30분", "11월 6일 19시 30분", "11월 7일 19시 30분"};
   String[] seatselect = {"1매", "2매", "3매", "4매"};
   JPanel dPanel;
   JPanel levelPanel;
   JPanel calPanel;
   JComboBox strCombo;
   JComboBox seatCombo;
   JRadioButton levelR[] = new JRadioButton[3];
   String levelName[] = {"연습", "중", "상"}; 
   String umm;
   Seat seat;
   
   JLabel guide1 = new JLabel("↑ 연습 모드 선택 시 예매 방법을 알려드려요");
   JLabel guide2 = new JLabel("←공연을 관람할 날짜를 선택하세요");
   JLabel guide3 = new JLabel("<html>예매할 티켓의<br/>개수를 선택하세요<br/>↓</html>");
   JLabel guide4 = new JLabel("<html>'예매하기'로 바뀌는 순간<br/>빠르게 버튼을 누르세요 →</html>");
   
   public DateSelect() {      
      
      dPanel = new JPanel();
      dPanel.setBounds(0,0,1000,800);
      dPanel.setLayout(null);
      
      levelPanel = new JPanel();
      levelPanel.setLayout(new FlowLayout());
      levelPanel.setBounds(370, 270, 300, 300);
      
      JLabel lvLabel = new JLabel("난이도");
      lvLabel.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      lvLabel.setLocation(470,240);
      lvLabel.setSize(100,40);
      
      ButtonGroup lvgroup = new ButtonGroup();
      
      for(int i=0; i<levelR.length; i++){
          levelR[i] = new JRadioButton(levelName[i]);
          lvgroup.add(levelR[i]);
          levelPanel.add(levelR[i]);
          levelR[i].setBackground(Color.white);
          levelR[i].addItemListener(new MyGrapeListener());

      }
      levelR[0].setSelected(true);
      
      //관람일 선택 제목
      JLabel la = new JLabel("관람일 선택");
      la.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      la.setLocation(100,100);
      la.setSize(200,40);
      
      calPanel = new JPanel();
      calPanel.setLayout(new FlowLayout());
      calPanel.setBounds(100, 160, 300, 300);
      ImageIcon calender = new ImageIcon("calender.jpg");
      JLabel cal = new JLabel(calender);
      calPanel.add(cal);
      
      JLabel seatla = new JLabel("매수 선택");
      seatla.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      seatla.setLocation(700,640);
      seatla.setSize(100,40);
      
      seatCombo = new JComboBox(seatselect);      
      seatCombo.setLocation(790, 640);
      seatCombo.setSize(70,40);
      seatCombo.setBackground(Color.white);

      strCombo = new JComboBox(date);      
      strCombo.setLocation(450,350);
      strCombo.setSize(200,40);
      
      JButton btn = new JButton();
      btn.setEnabled(false); // 처음엔 사용 불가

     //버튼 숫자 감소 스레드
      BtnThread th = new BtnThread(btn,levelR);
      
      //=================== 가이드라인용 레이블들 (초기에는 모두 보임)====================
      guide1.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      guide1.setBounds(450,300,500,50);
      guide1.setForeground(Purchaser.praccolor);
      dPanel.add(guide1);
      
      guide2.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      guide2.setBounds(650,350,500,50);
      guide2.setForeground(Purchaser.praccolor);
      dPanel.add(guide2);
      
      guide3.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      guide3.setBounds(790,550,500,100);
      guide3.setForeground(Purchaser.praccolor);
      dPanel.add(guide3);
      
      guide4.setFont(new Font(Font.DIALOG,Font.BOLD, 15));
      guide4.setBounds(500,680,500,100);
      guide4.setForeground(Purchaser.praccolor);
      dPanel.add(guide4);
      //=======================================================================
      //다음 단계 선택 버튼 액션리스너
      btn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {  
             JButton btn = (JButton)e.getSource();
             Purchaser.performdate = strCombo.getSelectedItem().toString();
             umm = seatCombo.getSelectedItem().toString();
             if (umm == "1매") {
            	 seat.enableseat = 1;
             } else if (umm == "2매") {
            	 seat.enableseat = 2;
             } else if (umm == "3매") {
            	 seat.enableseat = 3;
             } else if (umm == "4매") {
            	 seat.enableseat = 4;
             }

             dPanel.setVisible(false);
             th.SetLevel(GrapeThread.level);
             Ticketing.page++;
             new Ticketing(th); 
          }
          
      });
      btn.setSize(300,70);
      btn.setLocation(690 ,700);      

      //유의사항
      JLabel warn = new JLabel("<html>유의사항<br/>-장애인, 국가유공자 할인가격 예매 시 현장수령만 가능하며, 현장에 증빙서류 미지참시 할인 불가합니다.<br/>-할인쿠폰을 사용하여 예매한 티켓은 부분취소가 불가합니다.<br/>-관람일 전일까지만 취소 가능하며, 당일 관람 상품 예매 시에는 취소 불가합니다.<br/>-ATM기기에서 가상계좌입금이 안될수 있으니 인터넷/폰뱅킹이 어려우시면 무통장 입금외 다른 결제수단을 선택해주세요.</html>");
      warn.setLocation(100,400);
      warn.setSize(500,300);
      
      dPanel.add(la);
      dPanel.add(calPanel);
      dPanel.add(btn);
      dPanel.add(warn);
      dPanel.add(strCombo);
      dPanel.add(lvLabel);
      dPanel.add(levelPanel);
      dPanel.add(seatCombo);
      dPanel.add(seatla);

      setVisible(true);  
      dPanel.setBackground(Color.white);
      calPanel.setBackground(Color.white);
      levelPanel.setBackground(Color.white);
      strCombo.setBackground(Color.white);
      th.start(); //스레드 시작 
   }
   
   public void PracLine() {
       guide1.setVisible(true);
       guide2.setVisible(true);
       guide3.setVisible(true);
       guide4.setVisible(true);
   }

   public void DeletePrac() {
	   guide1.setVisible(false);
	   guide2.setVisible(false);
	   guide3.setVisible(false);
	   guide4.setVisible(false);
   }
   class MyGrapeListener implements ItemListener {
       public void itemStateChanged(ItemEvent e) {
          if (levelR[0].isSelected()) {  
             GrapeThread.level = 1000;
             PracLine();
          } else if (levelR[1].isSelected()) {
             GrapeThread.level = 100;
             DeletePrac();
          } else if (levelR[2].isSelected()) {
             GrapeThread.level = 50;
             DeletePrac();
          }
       }
    }
}