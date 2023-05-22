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
   // ��ܿ� ���� ���� �ܰ踦 �����ִ� �� �׸�
   static class UpperBar extends JPanel{
      int x = 10; // �� �κ� ���� ũ�� ���� ����
      int y = 50; // �� �κ� ���� ũ�� ���� ����
      int w = 970/5;; // �׸��� ���� �ʺ� �����ϴ� ����
      int h = 50; // �׸��� ���� ���� �����ϴ� ����
      
      Color highlight = new Color(253,204,102); //���̶���Ʈ ���� ������
      Color normal = new Color(240,240,240); //�������� ���� ȸ��
      Color text = Color.BLACK;
      Color [] c = {normal,normal,normal,normal,normal};
      
      public void paintComponent(Graphics g) {         
         // page ���� ����, ���� �������� ��� ���� ���̶���Ʈ��, ���� �������� �ƴ� ���� ��ֻ����� ����
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
                  
         // ��� �ΰ� ���
         g.setColor(Color.GRAY); // �ΰ� �۾��� ȸ������ ����
         g.setFont(new Font("���� ���",Font.BOLD,25));// �ΰ��� �۾�ü, ����, ũ�� ����
         g.drawString("�ϼ����� Ƽ�Ͽ���",10,40); // �ΰ� ���
      
         g.setFont(new Font("���� ���",Font.BOLD,20));// �ܰ� ���� �۾�ü, ����, ũ�� ����
         
         // �ܰ� �� �׸�
         g.setColor(c[0]);
         g.fillRect(x, y, w, h); // �� �� �׸� �׸�
         g.setColor(text); //�۾��� ���� ������ �׵θ� ���� ����
         g.drawRect(x, y, w, h); // �׵θ� �׸�
         g.drawString("��¥ ����",x+50,y+32); // �۾� ���

         g.setColor(c[1]);
         g.fillRect(w +x, y, w, h);
         g.setColor(text);
         g.drawRect(w +x, y, w, h);
         g.drawString("�¼� ����",w+x+50,y+32);

         g.setColor(c[2]);
         g.fillRect(w*2 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*2 + x, y, w, h);
         g.drawString("������ ���� �Է�",w*2 +x+20,y+32);

         g.setColor(c[3]);
         g.fillRect(w*3 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*3 + x, y, w, h);
         g.drawString("���� ���� �Է�",w*3 +x+30,y+32);

         g.setColor(c[4]);
         g.fillRect(w*4 + x, y, w, h);
         g.setColor(text);
         g.drawRect(w*4 + x, y, w, h);
         g.drawString("���� �Ϸ�",w*4 +x+50,y+32);
         // ���� switch���� ������ ���� ���� �Լ� ȣ�� �ø��� �ٸ� ���� �׸�      
         }
   }
   
   static class RightBar extends JPanel{
       //�̹��� ���
      ImageIcon schicago = new ImageIcon("Schicago.png");//���� ��ī�� �̹���
      ImageIcon srebecca = new ImageIcon("Srebecca.png");//���� ����ī �̹���
      
      //�̹��� ��ü
      Image Cimg = schicago.getImage();
      Image Rimg = srebecca.getImage();
      
      public void paintComponent(Graphics g){
         
         g.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));
               
         //�̹��� ��� �� �߰����� ���� ���
         if(Purchaser.perform == "��ī��") {
            g.drawImage(Cimg,0,0,150,130,this);
            g.drawString(Purchaser.performdate,150,35);
            g.drawString("~11�� 7�� 19�� 30��", 150, 55);
            g.drawString("�湫��Ʈ���� �����",150,80);
            g.drawString("14�� �̻� ���� ����",150,100);
            g.drawString("�����ð�: 145��",150,120);
         }
         else if (Purchaser.perform == "����ī") {
            g.drawImage(Rimg,0,0,150,130,this);
            g.drawString(Purchaser.performdate,150,35);
            g.drawString("~11�� 7�� 19�� 30��", 150, 55);
            g.drawString("��ť���Ʈ����",150,80);
            g.drawString("8�� �̻� ���� ����",150,100);
            g.drawString("�����ð�: 170��",150,120);
         }
         
         //My���� ���� ���
         g.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
         g.drawString("My��������",0,170);
         
         g.setFont(new Font(Font.DIALOG,Font.PLAIN, 15));

         //���� ���
         g.drawString("���� ����:  "+Purchaser.performdate,0,200);
         
         if(Seat.selectedseat== 0) {
                g.drawString("�¼�:", 0, 225);
         } 
         else {
            if(Purchaser.seatName.isEmpty()) {
                g.drawString("�¼�:", 0, 225);            	
            }else {
            	for(int number=0; number<Seat.selectedseat; number++) {
                    if(number == 0) {
                           g.drawString("�¼�:"+Purchaser.seatName.get(number), 0, 225);
                    }
                    else {
                          g.drawString("       "+Purchaser.seatName.get(number), 0, 225+(number*25));
                    }
                 }
            }
         }
         
         //���� ���
         g.drawString("����:  "+Purchaser.performprice,0,250+25*3);
         
         //������ �̸� ���S
         g.drawString("������:  ",0,275+25*3);
         //�̸���
         g.drawString("E-Mail:",0,300+25*3);
         
         
         if(Ticketing.page == 3) {
            //Name.setVisible(false);
            g.drawString("������:  "+Purchaser.name,0,275+25*3);
            g.drawString("E-Mail:  "+Purchaser.email,0,300+25*3);
         }
      }
   }
}