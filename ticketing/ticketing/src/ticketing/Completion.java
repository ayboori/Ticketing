//completion

package ticketing;

import java.awt.Container;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.*;

public class Completion extends JFrame{
   JPanel Finalp = new JPanel();//ū �г�
   private JLabel Info = new JLabel("���� ����");//���� ���� ����
   private JLabel PayInfo = new JLabel("���� �� ����");//���� �� ���� ����
   private JLabel ResNum = new JLabel();//���� ��ȣ
   private JLabel Product = new JLabel();//��ǰ �̸�
   private JLabel Where = new JLabel();//���
   private JLabel When = new JLabel();//����
   private JLabel Set = new JLabel();//�¼�
   private JLabel Who = new JLabel();//������
   private JLabel WhoPhone = new JLabel();//������ ����ó
   private JLabel Payoption = new JLabel();//���� ���
   private JLabel Payer = new JLabel();//�Ա��ڸ�
   private JLabel Price = new JLabel();//����
   private JLabel Account = new JLabel();//����

   public Completion() {
      Purchaser p = new Purchaser(); // ������ ���� ��ü ����
      
      Finalp.setBounds(100,150,400,800);
      Finalp.setLayout(null);
       
       //���� ���� ����
       Info.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      Info.setSize(500,20);
       Info.setLocation(0,0);
       
       //���Ź�ȣ
       int Num = (int)(Math.random()*1000000000+1);//å p.372����
       p.reservnum = "T" + Num;
       ResNum.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       ResNum.setText("���Ź�ȣ:    "+ p.reservnum);
       ResNum.setSize(500,20);
       ResNum.setLocation(25,50);
       
       //��ǰ
       Product.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Product.setText("����:    " + p.perform);
       Product.setSize(500,20);
       Product.setLocation(25,75);
       
       //���
       Where.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Where.setText("���:   " + p.performplace);
       Where.setSize(500,20);
       Where.setLocation(25,100);
       
       //�Ͻ�
       When.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       When.setText("�Ͻ�:    " + p.performdate);
       When.setSize(500,20);
       When.setLocation(25,125);
       
       //�¼�
       Set.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       String finalSeat = " ";
       for(int i=0; i<Seat.selectedseat; i++) {
          finalSeat += Purchaser.seatName.get(i)+ "   ";
       }
       Set.setText("�¼�:     "+finalSeat);
       Set.setSize(500,20);
       Set.setLocation(25,150);
       
       //������
       Who.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Who.setText("������:    " + p.name);
       Who.setSize(500,20);
       Who.setLocation(25,175);
       
       //������ ����ó
       WhoPhone.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       WhoPhone.setText("������ ����ó:    " + p.num);
       WhoPhone.setSize(500,20);
       WhoPhone.setLocation(25,200);
       
       //���� �� ���� ����
       PayInfo.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      PayInfo.setSize(500,20);
       PayInfo.setLocation(0,250);
       
       //���� ���
       Payoption.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Payoption.setText("���� ���:    " + p.realPayOp);
       Payoption.setSize(500,20);
       Payoption.setLocation(25,285);
       
       //�Ա� ����
       if (Purchaser.realPayOp == "�������Ա�") {
          Account.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
          Account.setText("�Ա� ���� :    " + Purchaser.bank + "���� "
                + (1000 + (int)(Math.random()*1000)) + " "
                + (100 + (int)(Math.random()*100)) + " "
                + (100000 + (int)(Math.random()*100000)));//å p.372����);
          Account.setSize(500,20);
          Account.setLocation(25,310);          
       }
       //���� �߰��߾�
       else if(Purchaser.realPayOp == "�ſ�ī��") {
          Account.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
          Account.setText("ī��� :     "+Purchaser.card);
          Account.setSize(500,20);
          Account.setLocation(25,310);
       }
       
       //����
       Price.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Price.setText("����:    " + p.performprice);
       Price.setSize(500,20);
       Price.setLocation(25,310+25);
       
       //�Ա��ڸ�
       Payer.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
       Payer.setText("�Ա��ڸ�:    " + p.name);
       Payer.setSize(500,20);
       Payer.setLocation(25,335+25);
       

       
       Finalp.add(Info);//���� ���� ���� ǥ��
       Finalp.add(ResNum);//���� ��ȣ ǥ��
       Finalp.add(Product);//��ǰ ǥ��
       Finalp.add(Where);//��� ǥ��
       Finalp.add(When);//�Ͻ� ǥ��
       Finalp.add(Set);//�¼� ǥ��
       Finalp.add(Who);//������ ǥ��
       Finalp.add(WhoPhone);//������ ����ó ǥ�ä�
       Finalp.add(PayInfo);//���� �� ���� ǥ��
       Finalp.add(Payoption);//������� ǥ��
       Finalp.add(Price);//���� ǥ��
       Finalp.add(Payer);//�Ա��ڸ� ǥ��
       Finalp.add(Account);//���� ǥ��

       
   }
   

}