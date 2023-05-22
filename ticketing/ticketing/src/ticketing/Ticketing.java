package ticketing;

import javax.swing.*;

import ticketing.Bars.RightBar;

import java.awt.*;
import java.awt.event.*;

public class Ticketing extends JFrame {
   static int page = 0; // ���� �ܰ� ��, �ʱⰪ�� 0
   Seat seat;
   
   public Ticketing(BtnThread bth) {
      setTitle("Ƽ���� ���� ���α׷�");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container contentPane = getContentPane();
      contentPane.setLayout(null);
   
      Bars.UpperBar bar = new Bars.UpperBar(); // ���� ���¸� ��Ÿ���� ��ܹ�
      bar.setBounds(0,0,1000,130);
      bar.setBackground(new Color(255,255,153));
      
      Bars.RightBar rbar = new Bars.RightBar();//�߰�
      rbar.setBounds(690, 150, 300, 460);//�߱�
      
      //�¼� �����ϴ� â ��ü
      seat = bth.returnSeat(); //�Է¹��� seat ��ü�� �� ����
      seat.seatPanel.setBounds(0,150,1000,550);
      contentPane.add(seat.seatPanel);
      seat.seatPanel.setVisible(true);
      
      //������ ���� �Է¹޴� â ��ü
      Info i = new Info();
      i.informPanel.setBounds(100,150,500,800);
      contentPane.add(i.informPanel);
      i.informPanel.setVisible(false);
      
      //���� ���� �Է¹޴� â ��ü
      Pay pay = new Pay();
      pay.PayPanel.setBounds(100,150,500,800);
      contentPane.add(pay.PayPanel);
      pay.PayPanel.setVisible(false);   
         
        JButton beforeBtn = new JButton("���� �ܰ�");// "���� �ܰ�" Ŭ�� �� ������ ������ �����ϴ� �̺�Ʈ ������      
        beforeBtn.setVisible(false);
        beforeBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           if (page ==2) { //������ ���� ������ ���� ���
              beforeBtn.setVisible(false);
              i.informPanel.setVisible(false);   
              seat.seatPanel.setVisible(true);// �¼�â ��ü true �ϱ�
              page --;
              GrapeThread.pick = false; // �̼��� �� ����
              bar.repaint();
           }      
           else if (page == 3) { //���� ���� �Է� ������ ���� ���
             beforeBtn.setVisible(true);
              pay.PayPanel.setVisible(false);   
              i.informPanel.setVisible(true);   
              page --;
              bar.repaint();
           }
        }
     });             
        beforeBtn.setSize(300,70);
        beforeBtn.setLocation(690,620); // �����ܰ� ��ư�� ũ��, ��ġ

     // "���� �ܰ�" Ŭ�� �� ������ ������ �����ϴ� �̺�Ʈ ������
      JButton nextBtn = new JButton("���� �ܰ�");
      nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          try {
              Thread.sleep(1000);
          }
          catch(InterruptedException e1) {
             return;
          }
         if (page==1) { //�¼�â ������ ���� ���
            if (Seat.selectedseat != Seat.enableseat) {
               JOptionPane.showMessageDialog(null, "������ �ż����� ���� �����߽��ϴ�.\n" + Seat.enableseat + " �Ÿ� �����ؾ� �մϴ�.","Ƽ�� �ż� ����", JOptionPane.ERROR_MESSAGE); 
            }else if(GrapeThread.pick) {
                JOptionPane.showMessageDialog(null, "�ٸ� ���Բ��� �̹� ������ �¼��Դϴ�.\n�ٸ� �¼��� �����ϼ���.","�ϼ����� Ƽ�Ͽ���", JOptionPane.ERROR_MESSAGE);   
                
               for (int i = 0 ; i<Purchaser.seatName.size(); i++) {
                  if(GrapeThread.alreadyx[i]> -1 && GrapeThread.alreadyy[i] >-1) { // ���� �¼��� ����� ��쿡 
                     seat.grape[GrapeThread.alreadyx[i]][GrapeThread.alreadyy[i]].setEnabled(false); 
                     seat.grape[GrapeThread.alreadyx[i]][GrapeThread.alreadyy[i]].setBackground(Color.GRAY);
                     // �̹� �ٸ� ������� ���� �¼����� �ٽ� ���� �� ���� enable
                     Seat.selectedseat --; // �� �� enable �� ������ ������ �˼� -1
                     GrapeThread.pick = false; 
                     
                      // ��� �� ������ ��ü���� �������� ���� �� �� ������ �ϱ�
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
               beforeBtn.setVisible(true); // �¼�â���� ���� ��ư ���̰� �ϱ�
            }
         }
         else if (page ==2) { //������ ���� ������ ���� ���
            if(i.nameF.getText().equals("") || i.numF.getText().equals("") || i.emailF.getText().equals("")) { 
               JOptionPane.showMessageDialog(null, "��� ������ �Է��ϼ���","Error", JOptionPane.WARNING_MESSAGE);
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
               rbar.repaint();//���⵵!!1
            }// �ؽ�Ʈ�ʵ尡 ������� ��� �ڷ� �Ѿ�� �ʰ� ���� �޼��� ���
         }
         else if (page == 3) { //���� ���� �Է� ������ ���� ���
             if(pay.Payop == null) { 
                JOptionPane.showMessageDialog(null, "���� ����� �����ϼ���","Error", JOptionPane.WARNING_MESSAGE);
             } else if(pay.Payop.equals("�ſ�ī��") && pay.number.getText().equals("")){
                 JOptionPane.showMessageDialog(null, "ī�� ��ȣ�� �Է��ϼ���","Error", JOptionPane.WARNING_MESSAGE);               
             }
             else if(pay.onlynum){ }
             else {//������ ��� ä������ �� ���������� �Ѿ
                if(pay.Payop.equals("�ſ�ī��")) {
                   Purchaser.realPayOp="�ſ�ī��";
                   Purchaser.card = pay.CreditCB.getSelectedItem().toString();//����� ���ƾ�
                }
                else if(pay.Payop.equals("�������Ա�")) {
                       Purchaser.realPayOp="�������Ա�";
                       Purchaser.bank = pay.BankbookCB.getSelectedItem().toString();
                }
             
             pay.PayPanel.setVisible(false);   

             //���� �Ϸ�â ��ü
             Completion com = new Completion();
             com.Finalp.setBounds(100,150,500,800);
             contentPane.add(com.Finalp);      
             
             page ++;
             bar.repaint();
             rbar.setVisible(false);//���� �߰� �߾��!!
             nextBtn.setVisible(false); // ���� ��ư�� ���̻� �ʿ� �����Ƿ� ������ �ʰ� ��
             beforeBtn.setVisible(false); // ���� ��ư�� ���̸� �� �ǹǷ� ������ �ʰ� ��
             }
          }
       }
    });       

    nextBtn.setSize(300,70);
    nextBtn.setLocation(690,700); // �����ܰ� ��ư�� ũ��, ��ġ   
    contentPane.add(bar);
    contentPane.add(rbar);//�߰��߰�
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