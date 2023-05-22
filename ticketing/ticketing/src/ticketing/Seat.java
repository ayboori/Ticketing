package ticketing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ticketing.Bars;

public class Seat extends JPanel {
   static int selectedseat = 0; // ���õ� �¼��� ����
   static int enableseat = 1; // ������ �� �ִ� �¼��� ����, �ʱⰪ 1
   static int exClickCnt = 0;
   
   public String seatnum;
   JPanel seatPanel;
   JPanel grapePanel;
   SGuide sgui;
   JLabel seatgui;
   JLabel seatnextgui;
   JLabel seatcancelgui;
   Bars.RightBar rbar = new Bars.RightBar();//������
   JButton [][] grape;
   static boolean clk = false;

   public Seat() {
      
      seatPanel = new JPanel();
      seatPanel.setBounds(0,150,800,800); // �г��� ������ ��ġ
      seatPanel.setLayout(null);
      
      JLabel stage = new JLabel("STAGE"); //������ ���� �۾� ���
      stage.setSize(200,50);
      stage.setLocation(130+100,30);
      stage.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
      
      rbar.setBounds(590+100, 0, 800, 460);//�߱�
      
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
      
      if (GrapeThread.level == 1000) { //�����ܰ��� ��
          sgui = new SGuide();
          sgui.setBounds(0,0,600,400);
          seatPanel.add(sgui);
          sgui.setVisible(true);
          
          seatgui = new JLabel("�¼��� �����ϼ���!!");
          seatgui.setBounds(450, 120+280, 200, 40);
          seatgui.setForeground(Purchaser.praccolor);
          seatPanel.add(seatgui);
          seatgui.setVisible(true);
          
          seatcancelgui = new JLabel("������ �¼��� ����غ�����!");
          seatcancelgui.setBounds(450, 120+280, 200, 40);
          seatcancelgui.setForeground(Purchaser.praccolor);
          seatPanel.add(seatcancelgui);
          seatcancelgui.setVisible(false);
          
          seatnextgui = new JLabel("<html>�¼��� ��� �����ϰ�<br/>�����ܰ踦 ���������</html>");
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
              
              seatnum = alpha + "�� " + Integer.toString(beta) + "�� ";
      
      ClickCnt++; // �¼� �����ϰ�, ����ϰ�
      exClickCnt++;
      
      if (Seat.selectedseat < Seat.enableseat) {
          
      if (ClickCnt % 2 == 1) { // �¼� ����
           
            Purchaser.seatName.add(seatnum);
            Purchaser.seatX.add(x);
            Purchaser.seatY.add(y);
            
            grape.setBackground(new Color(253,204,102));
            
            Seat.selectedseat++; // ���õ� �¼� ���� +1
            clk = true;
               
            seatPanel.repaint();
            
            if (GrapeThread.level == 1000) { //(�������)
               if (exClickCnt == 1) { //��ó�� �¼� ����������
                  seatgui.setVisible(false); //"�¼��� �����ϼ���!!" �����
                  seatcancelgui.setVisible(true); //"������ �¼��� ����ϼ���!!" ����
               } else { //�� ���Ŀ� �����ϸ�
                  sgui.setVisible(false); //�����ڽ� �����
                  seatgui.setVisible(false); //"�¼��� �����ϼ���!!" �����
                  seatnextgui.setVisible(true); //�����ܰ褡�� ����
               }
            }
      } else if (ClickCnt % 2 == 0) { // �¼� ���

          Purchaser.seatX.remove(Purchaser.seatName.indexOf(seatnum));
          Purchaser.seatY.remove(Purchaser.seatName.indexOf(seatnum));
         Purchaser.seatName.remove(seatnum);
         
         grape.setBackground(new Color(163,73,164));
         
         Seat.selectedseat--; // ���õ� �¼� ���� -1
         clk = false;
         GrapeThread.twosun--;
         
         seatPanel.repaint();
         
         if (GrapeThread.level == 1000) { //(�������) ���������
            sgui.setVisible(true); //�����ڽ� �����������, Ȥ�� ����
             seatcancelgui.setVisible(false); //"������ �¼��� ����ϼ���!!" �����
             seatgui.setVisible(true); //"�¼��� �����ϼ���!!" ����
             seatnextgui.setVisible(false); //�����ܰ褡�� �����
         }
      }
     } else if (Seat.selectedseat == Seat.enableseat) {
        
        
        if (ClickCnt % 2 == 1) {
           
           JOptionPane.showMessageDialog(null, "�¼� ���� ������ �ʰ��Ͽ����ϴ�.","�¼� �ʰ�", JOptionPane.ERROR_MESSAGE);
           ClickCnt--; // ���� �� �� ���´ϱ�
           
        } else if (ClickCnt % 2 == 0) {
           
            Purchaser.seatX.remove(Purchaser.seatName.indexOf(seatnum));
            Purchaser.seatY.remove(Purchaser.seatName.indexOf(seatnum));
            Purchaser.seatName.remove(seatnum);
             
             grape.setBackground(new Color(163,73,164));
             
             Seat.selectedseat--; // ���õ� �¼� ���� -1
             clk = false;
             GrapeThread.twosun--;
             
             seatPanel.repaint();
             
             if (GrapeThread.level == 1000) { // �¼� ���������
                sgui.setVisible(true); //�����ڽ� �����������, Ȥ�� ����
                 seatcancelgui.setVisible(false); //"������ �¼��� ����ϼ���!!" �����
                 seatgui.setVisible(true); //"�¼��� �����ϼ���!!" ����
                 seatnextgui.setVisible(false); //�����ܰ褡�� �����
             }
           
        }
     }
   }
 }

}