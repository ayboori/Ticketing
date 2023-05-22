package ticketing;

import javax.swing.*;

import ticketing.Pay.MyItemListener;

import java.awt.*;
import java.awt.event.*;

class DateSelect extends JPanel {
   String[] date = {"11�� 5�� 19�� 30��", "11�� 6�� 19�� 30��", "11�� 7�� 19�� 30��"};
   String[] seatselect = {"1��", "2��", "3��", "4��"};
   JPanel dPanel;
   JPanel levelPanel;
   JPanel calPanel;
   JComboBox strCombo;
   JComboBox seatCombo;
   JRadioButton levelR[] = new JRadioButton[3];
   String levelName[] = {"����", "��", "��"}; 
   String umm;
   Seat seat;
   
   JLabel guide1 = new JLabel("�� ���� ��� ���� �� ���� ����� �˷������");
   JLabel guide2 = new JLabel("������� ������ ��¥�� �����ϼ���");
   JLabel guide3 = new JLabel("<html>������ Ƽ����<br/>������ �����ϼ���<br/>��</html>");
   JLabel guide4 = new JLabel("<html>'�����ϱ�'�� �ٲ�� ����<br/>������ ��ư�� �������� ��</html>");
   
   public DateSelect() {      
      
      dPanel = new JPanel();
      dPanel.setBounds(0,0,1000,800);
      dPanel.setLayout(null);
      
      levelPanel = new JPanel();
      levelPanel.setLayout(new FlowLayout());
      levelPanel.setBounds(370, 270, 300, 300);
      
      JLabel lvLabel = new JLabel("���̵�");
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
      
      //������ ���� ����
      JLabel la = new JLabel("������ ����");
      la.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
      la.setLocation(100,100);
      la.setSize(200,40);
      
      calPanel = new JPanel();
      calPanel.setLayout(new FlowLayout());
      calPanel.setBounds(100, 160, 300, 300);
      ImageIcon calender = new ImageIcon("calender.jpg");
      JLabel cal = new JLabel(calender);
      calPanel.add(cal);
      
      JLabel seatla = new JLabel("�ż� ����");
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
      btn.setEnabled(false); // ó���� ��� �Ұ�

     //��ư ���� ���� ������
      BtnThread th = new BtnThread(btn,levelR);
      
      //=================== ���̵���ο� ���̺�� (�ʱ⿡�� ��� ����)====================
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
      //���� �ܰ� ���� ��ư �׼Ǹ�����
      btn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {  
             JButton btn = (JButton)e.getSource();
             Purchaser.performdate = strCombo.getSelectedItem().toString();
             umm = seatCombo.getSelectedItem().toString();
             if (umm == "1��") {
            	 seat.enableseat = 1;
             } else if (umm == "2��") {
            	 seat.enableseat = 2;
             } else if (umm == "3��") {
            	 seat.enableseat = 3;
             } else if (umm == "4��") {
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

      //���ǻ���
      JLabel warn = new JLabel("<html>���ǻ���<br/>-�����, ���������� ���ΰ��� ���� �� ������ɸ� �����ϸ�, ���忡 �������� �������� ���� �Ұ��մϴ�.<br/>-���������� ����Ͽ� ������ Ƽ���� �κ���Ұ� �Ұ��մϴ�.<br/>-������ ���ϱ����� ��� �����ϸ�, ���� ���� ��ǰ ���� �ÿ��� ��� �Ұ��մϴ�.<br/>-ATM��⿡�� ��������Ա��� �ȵɼ� ������ ���ͳ�/����ŷ�� �����ø� ������ �Աݿ� �ٸ� ���������� �������ּ���.</html>");
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
      th.start(); //������ ���� 
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