//completion

package ticketing;

import java.awt.Container;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.*;

public class Completion extends JFrame{
   JPanel Finalp = new JPanel();//큰 패널
   private JLabel Info = new JLabel("예매 정보");//에매 정보 메인
   private JLabel PayInfo = new JLabel("결제 상세 정보");//결제 상세 정보 메인
   private JLabel ResNum = new JLabel();//예매 번호
   private JLabel Product = new JLabel();//상품 이름
   private JLabel Where = new JLabel();//장소
   private JLabel When = new JLabel();//언제
   private JLabel Set = new JLabel();//좌석
   private JLabel Who = new JLabel();//예매자
   private JLabel WhoPhone = new JLabel();//에매자 연락처
   private JLabel Payoption = new JLabel();//결제 방법
   private JLabel Payer = new JLabel();//입금자명
   private JLabel Price = new JLabel();//가격
   private JLabel Account = new JLabel();//계좌

   public Completion() {
      Purchaser p = new Purchaser(); // 구매자 정보 객체 생성
      
      Finalp.setBounds(100,150,400,800);
      Finalp.setLayout(null);
       
       //예매 정보 메인
       Info.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      Info.setSize(500,20);
       Info.setLocation(0,0);
       
       //예매번호
       int Num = (int)(Math.random()*1000000000+1);//책 p.372참고
       p.reservnum = "T" + Num;
       ResNum.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       ResNum.setText("예매번호:    "+ p.reservnum);
       ResNum.setSize(500,20);
       ResNum.setLocation(25,50);
       
       //상품
       Product.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Product.setText("공연:    " + p.perform);
       Product.setSize(500,20);
       Product.setLocation(25,75);
       
       //장소
       Where.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Where.setText("장소:   " + p.performplace);
       Where.setSize(500,20);
       Where.setLocation(25,100);
       
       //일시
       When.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       When.setText("일시:    " + p.performdate);
       When.setSize(500,20);
       When.setLocation(25,125);
       
       //좌석
       Set.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       String finalSeat = " ";
       for(int i=0; i<Seat.selectedseat; i++) {
          finalSeat += Purchaser.seatName.get(i)+ "   ";
       }
       Set.setText("좌석:     "+finalSeat);
       Set.setSize(500,20);
       Set.setLocation(25,150);
       
       //예매자
       Who.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Who.setText("예매자:    " + p.name);
       Who.setSize(500,20);
       Who.setLocation(25,175);
       
       //예매지 연락처
       WhoPhone.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       WhoPhone.setText("예매자 연락처:    " + p.num);
       WhoPhone.setSize(500,20);
       WhoPhone.setLocation(25,200);
       
       //결제 상세 정보 메인
       PayInfo.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      PayInfo.setSize(500,20);
       PayInfo.setLocation(0,250);
       
       //결제 방법
       Payoption.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Payoption.setText("결재 방법:    " + p.realPayOp);
       Payoption.setSize(500,20);
       Payoption.setLocation(25,285);
       
       //입금 통장
       if (Purchaser.realPayOp == "무통장입금") {
          Account.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
          Account.setText("입금 은행 :    " + Purchaser.bank + "은행 "
                + (1000 + (int)(Math.random()*1000)) + " "
                + (100 + (int)(Math.random()*100)) + " "
                + (100000 + (int)(Math.random()*100000)));//책 p.372참고);
          Account.setSize(500,20);
          Account.setLocation(25,310);          
       }
       //여기 추가했엉
       else if(Purchaser.realPayOp == "신용카드") {
          Account.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
          Account.setText("카드사 :     "+Purchaser.card);
          Account.setSize(500,20);
          Account.setLocation(25,310);
       }
       
       //가격
       Price.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Price.setText("가격:    " + p.performprice);
       Price.setSize(500,20);
       Price.setLocation(25,310+25);
       
       //입금자명
       Payer.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Payer.setText("입금자명:    " + p.name);
       Payer.setSize(500,20);
       Payer.setLocation(25,335+25);
       

       
       Finalp.add(Info);//예매 정보 메인 표시
       Finalp.add(ResNum);//예매 번호 표시
       Finalp.add(Product);//상품 표시
       Finalp.add(Where);//장소 표시
       Finalp.add(When);//일시 표시
       Finalp.add(Set);//좌석 표시
       Finalp.add(Who);//예매자 표시
       Finalp.add(WhoPhone);//예매자 연락처 표시ㄴ
       Finalp.add(PayInfo);//결제 상세 정보 표시
       Finalp.add(Payoption);//결제방법 표시
       Finalp.add(Price);//가격 표시
       Finalp.add(Payer);//입금자명 표시
       Finalp.add(Account);//가격 표시

       
   }
   

}