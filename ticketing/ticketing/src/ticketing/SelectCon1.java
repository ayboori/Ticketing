package ticketing;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

// ���� ���� ȭ��
public class SelectCon1 extends JFrame {
   
   Container c = getContentPane();
   JPanel cPanel = new JPanel();
   
   public SelectCon1() {
      setTitle("��������");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      c.setLayout(null);
                
      ImageIcon chicago = new ImageIcon("chicago.jpg");
      ImageIcon rebecca = new ImageIcon("rebecca.jpg");
      JButton chiBtn = new JButton(chicago);
      JButton rebBtn = new JButton(rebecca);

         cPanel.setLayout(null);
         cPanel.setBounds(0, 0, 1000, 800);
         chiBtn.setBorderPainted(false);
         chiBtn.setFocusPainted(false);
         chiBtn.setContentAreaFilled(false);
         chiBtn.setBounds(130,120,350,500);
         cPanel.add(chiBtn);
         rebBtn.setBorderPainted(false);
         rebBtn.setFocusPainted(false);
         rebBtn.setContentAreaFilled(false);
         rebBtn.setBounds(500,120,350,500);
         cPanel.add(rebBtn);
         c.add(cPanel);
                         
         //������ ��ī��
         chiBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   cPanel.setVisible(false);
                   Purchaser.perform = "��ī��";
                   Purchaser.performplace = "�湫��Ʈ���� �����";
                   Purchaser.performprice = "150,000��";
                   
                 //��¥ ���� â ��ü
                 DateSelect d = new DateSelect();
                 c.add(d.dPanel);
                 
               }
           });
         
         //������ ����ī
         rebBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   cPanel.setVisible(false);
                   Purchaser.perform = "����ī";
                   Purchaser.performplace = "��ť���Ʈ����";
                   Purchaser.performprice = "140,000��";
                   
                 //��¥ ���� â ��ü
                 DateSelect d = new DateSelect();
                 c.add(d.dPanel);
               }
              
           });
         
         setSize(1000,800);
         setVisible(true); 
         setResizable(false);
   }
   
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      
      new SelectCon1();
   }
}

