package ticketing;

import javax.swing.*;

import ticketing.Bars.RightBar;

import java.awt.*;
import java.awt.event.*;

public class Ticketing extends JFrame {
   static int page = 0; // 진행 단계 값, 초기값은 0
   Seat seat;
   
   public Ticketing(BtnThread bth) {
      setTitle("티켓팅 연습 프로그램");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container contentPane = getContentPane();
      contentPane.setLayout(null);
   
      Bars.UpperBar bar = new Bars.UpperBar(); // 진행 상태를 나타내는 상단바
      bar.setBounds(0,0,1000,130);
      bar.setBackground(new Color(255,255,153));
      
      Bars.RightBar rbar = new Bars.RightBar();//추가
      rbar.setBounds(690, 150, 300, 460);//추기
      
      //좌석 선택하는 창 객체
      seat = bth.returnSeat(); //입력받은 seat 객체로 값 세팅
      seat.seatPanel.setBounds(0,150,1000,550);
      contentPane.add(seat.seatPanel);
      seat.seatPanel.setVisible(true);
      
      //예매자 정보 입력받는 창 객체
      Info i = new Info();
      i.informPanel.setBounds(100,150,500,800);
      contentPane.add(i.informPanel);
      i.informPanel.setVisible(false);
      
      //결제 정보 입력받는 창 객체
      Pay pay = new Pay();
      pay.PayPanel.setBounds(100,150,500,800);
      contentPane.add(pay.PayPanel);
      pay.PayPanel.setVisible(false);   
         
        JButton beforeBtn = new JButton("이전 단계");// "이전 단계" 클릭 시 정보를 변수에 저장하는 이벤트 리스너      
        beforeBtn.setVisible(false);
        beforeBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           if (page ==2) { //예매자 정보 나오고 있을 경우
              beforeBtn.setVisible(false);
              i.informPanel.setVisible(false);   
              seat.seatPanel.setVisible(true);// 좌석창 객체 true 하기
              page --;
              GrapeThread.pick = false; // 이선좌 된 변수
              bar.repaint();
           }      
           else if (page == 3) { //결제 정보 입력 나오고 있을 경우
             beforeBtn.setVisible(true);
              pay.PayPanel.setVisible(false);   
              i.informPanel.setVisible(true);   
              page --;
              bar.repaint();
           }
        }
     });             
        beforeBtn.setSize(300,70);
        beforeBtn.setLocation(690,620); // 다음단계 버튼의 크기, 위치

     // "다음 단계" 클릭 시 정보를 변수에 저장하는 이벤트 리스너
      JButton nextBtn = new JButton("다음 단계");
      nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          try {
              Thread.sleep(1000);
          }
          catch(InterruptedException e1) {
             return;
          }
         if (page==1) { //좌석창 나오고 있을 경우
            if (Seat.selectedseat != Seat.enableseat) {
               JOptionPane.showMessageDialog(null, "선택한 매수보다 적게 선택했습니다.\n" + Seat.enableseat + " 매를 선택해야 합니다.","티켓 매수 오류", JOptionPane.ERROR_MESSAGE); 
            }else if(GrapeThread.pick) {
                JOptionPane.showMessageDialog(null, "다른 고객님께서 이미 선택한 좌석입니다.\n다른 좌석을 선택하세요.","일석이조 티켓예매", JOptionPane.ERROR_MESSAGE);   
                
               for (int i = 0 ; i<Purchaser.seatName.size(); i++) {
                  if(GrapeThread.alreadyx[i]> -1 && GrapeThread.alreadyy[i] >-1) { // 잡은 좌석이 사라진 경우에 
                     seat.grape[GrapeThread.alreadyx[i]][GrapeThread.alreadyy[i]].setEnabled(false); 
                     seat.grape[GrapeThread.alreadyx[i]][GrapeThread.alreadyy[i]].setBackground(Color.GRAY);
                     // 이미 다른 사람들이 잡은 좌석들은 다시 잡을 수 없게 enable
                     Seat.selectedseat --; // 한 번 enable 될 때마다 선택한 죄석 -1
                     GrapeThread.pick = false; 
                     
                      // 취소 될 값들은 객체에서 지워버려 선택 안 된 것으로 하기
                     Purchaser.seatName.remove(i);
                     Purchaser.seatX.remove(i);
                     Purchaser.seatY.remove(i);
                     seat.seatPanel.repaint();
                  }
               }
            }
            else{
               seat.seatPanel.setVisible(false);
               i.informPanel.setVisible(true);
               page ++;
               bar.repaint();
               beforeBtn.setVisible(true); // 좌석창부터 이전 버튼 보이게 하기
            }
         }
         else if (page ==2) { //예매자 정보 나오고 있을 경우
            if(i.nameF.getText().equals("") || i.numF.getText().equals("") || i.emailF.getText().equals("")) { 
               JOptionPane.showMessageDialog(null, "모든 정보를 입력하세요","Error", JOptionPane.WARNING_MESSAGE);
            }
            else if(i.onlynum){
            	
            }else {
               Purchaser.name = i.nameF.getText();
               Purchaser.num = i.numF.getText();
               Purchaser.email = i.emailF.getText();
               
               i.informPanel.setVisible(false);   
               pay.PayPanel.setVisible(true);   
               page ++;
               bar.repaint();
               rbar.repaint();//여기도!!1
            }// 텍스트필드가 비어있을 경우 뒤로 넘어가지 않고 에러 메세지 출력
         }
         else if (page == 3) { //결제 정보 입력 나오고 있을 경우
             if(pay.Payop == null) { 
                JOptionPane.showMessageDialog(null, "결제 방법을 선택하세요","Error", JOptionPane.WARNING_MESSAGE);
             } else if(pay.Payop.equals("신용카드") && pay.number.getText().equals("")){
                 JOptionPane.showMessageDialog(null, "카드 번호를 입력하세요","Error", JOptionPane.WARNING_MESSAGE);               
             }
             else if(pay.onlynum){ }
             else {//정보가 모두 채워졌을 때 정상적으로 넘어감
                if(pay.Payop.equals("신용카드")) {
                   Purchaser.realPayOp="신용카드";
                   Purchaser.card = pay.CreditCB.getSelectedItem().toString();//여기는 고쳤엉
                }
                else if(pay.Payop.equals("무통장입금")) {
                       Purchaser.realPayOp="무통장입금";
                       Purchaser.bank = pay.BankbookCB.getSelectedItem().toString();
                }
             
             pay.PayPanel.setVisible(false);   

             //결제 완료창 객체
             Completion com = new Completion();
             com.Finalp.setBounds(100,150,500,800);
             contentPane.add(com.Finalp);      
             
             page ++;
             bar.repaint();
             rbar.setVisible(false);//여기 추가 했어요!!
             nextBtn.setVisible(false); // 다음 버튼이 더이상 필요 없으므로 보이지 않게 함
             beforeBtn.setVisible(false); // 이전 버튼이 보이면 안 되므로 보이지 않게 함
             }
          }
       }
    });       

    nextBtn.setSize(300,70);
    nextBtn.setLocation(690,700); // 다음단계 버튼의 크기, 위치   
    contentPane.add(bar);
    contentPane.add(rbar);//추가추가
    contentPane.add(nextBtn);
    contentPane.add(beforeBtn);
    
    setSize(1000,800);
    setVisible(true);
    setResizable(false);
    }

   
   public static void main(String []args) {
      new SelectCon1();
   }
}