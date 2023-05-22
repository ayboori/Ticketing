package ticketing;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

// 공연 선택 화면
public class SelectCon1 extends JFrame {
   
   Container c = getContentPane();
   JPanel cPanel = new JPanel();
   
   public SelectCon1() {
      setTitle("공연선택");
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
                         
         //뮤지컬 시카고
         chiBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   cPanel.setVisible(false);
                   Purchaser.perform = "시카고";
                   Purchaser.performplace = "충무아트센터 대극장";
                   Purchaser.performprice = "150,000원";
                   
                 //날짜 선택 창 객체
                 DateSelect d = new DateSelect();
                 c.add(d.dPanel);
                 
               }
           });
         
         //뮤지컬 레베카
         rebBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   cPanel.setVisible(false);
                   Purchaser.perform = "레베카";
                   Purchaser.performplace = "디큐브아트센터";
                   Purchaser.performprice = "140,000원";
                   
                 //날짜 선택 창 객체
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

