package ticketing;

import javax.swing.*;
import javax.swing.text.PlainDocument;

import ticketing.Info.IGuide;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import java.awt.ActiveEvent.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

public class Pay extends JFrame{
   private JRadioButton [] radio = new JRadioButton [2];
   private String [] text = {"½Å¿ëÄ«µå","¹«ÅëÀå ÀÔ±İ"};
   private String [] CreditCom = {"½ÅÇÑÄ«µå","³óÇùÄ«µå","ÇÏ³ªÄ«µå"};//Ä«µå È¸»ç
   private String [] BankbookCom = {"½ÅÇÑ","³óÇù","ÇÏ³ª"};//ÀºÇàÀÌ¸§
   JPanel PayPanel = new JPanel();//ÀüÁ¦ ÆĞ³Î
   private JPanel creditCard = new JPanel();//½Å¿ëÄ«µå
   private JPanel bankbook = new JPanel();//¹«ÅëÀå
   private JPanel Group = new JPanel();//Ä«µå¼±ÅÃ
   private JPanel button = new JPanel();//¹öÆ°
   private JPanel payment = new JPanel();//°áÁ¦ ÇÏ±â
   JTextField number = new JTextField(3);
   private JButton next = new JButton("´ÙÀ½ ´Ü°è");
   private JLabel paymentL = new JLabel("°áÁ¦ÇÏ±â");
   private JLabel warningL = new JLabel("<html>°ü¶÷ÀÏ 9ÀÏÀü ~ 7ÀÏ Àü±îÁö    Æ¼ÄÏ±İ¾×ÀÇ 10%<br/> °ü¶÷ÀÏ 6ÀÏÀü ~3ÀÏÀü    "
         + "Æ¼ÄÏ±İ¾×ÀÇ 20%<br/> °ü¶÷ÀÏ 2ÀÏÀü~1ÀÏÀü±îÁö    Æ¼ÄÏ±İ¾×ÀÇ30%<br/> ¿¹¸Å¼ö¼ö·á´Â ¿¹¸ÅÀÏÀÌÈÄ Ãë¼Ò¿¡´Â È¯ºÒµÇÁö ¾Ê½À´Ï´Ù.<br/>´Ü.¿¹¸Å ´çÀÏ  ¹ã12½Ã ÀÌÀü Ãë¼Ò½Ã¿¡´Â Ãë¼Ò ¼ö¼ö·á ¾øÀ½</html>");
   JComboBox<String> CreditCB = new JComboBox<String>(CreditCom);//Ä«µå ÄŞº¸
   JComboBox<String> BankbookCB = new JComboBox<String>(BankbookCom);//ÀºÇà ÄŞº¸
   String CrditCBN;//Ä«µå»ç
   String Payop;//°áÁ¦ ¹æ¹ı
   
   boolean onlynum = false;
   
   public Pay() {
      JPanel radioPanel = new JPanel();
      ButtonGroup g = new ButtonGroup(); //2°³ÀÇ ¶óµğ¿À ¹öÆ°À» ¹­À» ¹öÆ° ±×·ì
      for(int i=0; i<radio.length;i++) {
         radio[i] = new JRadioButton(text[i]);
         g.add(radio[i]);
         radioPanel.add(radio[i]);
         radio[i].addItemListener(new MyItemListener());
      }
      
     PGuide pgui = new PGuide();
     CardGuide cardgui = new CardGuide();
     CComGuide ccomgui = new CComGuide();
     BComGuide bgui = new BComGuide();
     
     JLabel payguide = new JLabel("°áÁ¦ÇÒ ¹æ¹ıÀ» ¼±ÅÃÇÏ¼¼¿ä.");
     payguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//°áÁ¦¹æ¹ı ·¹ÀÌºí ÆùÆ®
     payguide.setForeground(Purchaser.praccolor);
     
     JLabel numguide = new JLabel("Ä«µå ¹øÈ£ ¾ÕÀÚ¸® 4ÀÚ¸®¸¦ ÀÔ·Â ÇÏ¼¼¿ä!");
     numguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//Ä«µå¹øÈ£ ·¹ÀÌºí ÆùÆ®
     numguide.setForeground(Purchaser.praccolor);
     
     JLabel comguide = new JLabel("Ä«µå»ç¸¦ ¼±ÅÃÇÏ¼¼¿ä!");
     comguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//Ä«µåÄŞº¸ ·¹ÀÌºí ÆùÆ®
     comguide.setForeground(Purchaser.praccolor);
     
     JLabel botton = new JLabel("´ÙÀ½ ¹öÆ°À» ´©¸£¼¼¿ä!");
      botton.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//Ä«µåÄŞº¸ ·¹ÀÌºí ÆùÆ®
      botton.setForeground(Purchaser.praccolor);
      
      JLabel bguide = new JLabel("ÀºÇà»ç¸¦ ¼±ÅÃÇÏ¼¼¿ä!");
      bguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//Ä«µåÄŞº¸ ·¹ÀÌºí ÆùÆ®
      bguide.setForeground(Purchaser.praccolor);
      
      JLabel bottom = new JLabel("<html>Ä«µå¹øÈ£,Ä«µå»ç,ÀºÇà»ç <br/>¼±ÅÃÇÑ ÈÄ  ´ÙÀ½ ¹öÆ° ´©¸£¼¼¿ä¡æ</html>");
      bottom.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//Ä«µåÄŞº¸ ·¹ÀÌºí ÆùÆ®
      bottom.setForeground(Purchaser.praccolor);
             
      //°áÁ¦ ÇÏ±â ¿¹¸Å Æ¼ÄÏ
      paymentL.setFont(new Font(Font.DIALOG,Font.BOLD, 20));//·¹ÀÌºí ÆùÆ®
      payment.add(paymentL);//°áÁ¦È®ÀÎ ·¹ÀÌºí Ãß°¡
      
      //ÁÖÀÇ »çÇ×
      warningL.setSize(500,300);
      warningL.setLocation(0,400-150);
      
      //½Å¿ëÄ«µå
      creditCard.setLayout(null);
      JLabel cardnum = new JLabel("Ä«µå¹øÈ£");
      cardnum.setBounds(10, 0, 60, 30);
      creditCard.add(cardnum);
      number.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = number.getText();
            if(korea.matches(".*[a-zA-Z¤¡-¤¾¤¿-¤Ó°¡-ÆR]+.*")) {
               onlynum = true;
               JOptionPane.showMessageDialog(null,"¼ıÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä!!!");
               number.setText("");
             } else {
                onlynum = false;
             }
         }
      });
      number.setDocument(new JTextFieldLimit(4));
      number.setBounds(65+10, 5, 50, 30);
      creditCard.add(number);
      JLabel xxx = new JLabel("xxxx-xxxx");
      xxx.setBounds(120+10, 0, 60, 30);
      creditCard.add(xxx);
      CreditCB.setBounds(205+5, 5, 100, 30);
      creditCard.add(CreditCB);
      if (GrapeThread.level == 1000) {
         number.addFocusListener(new FocusAdapter() {//Ä«µå¹øÈ£ °¡ÀÌµå
              @Override
              public void focusGained(FocusEvent e) {
                 cardgui.setVisible(false);
                 numguide.setVisible(false);
                 cardgui.setBounds(70, 0, 60, 40);
                 numguide.setBounds(5,40,500,30);
                 creditCard.add(cardgui);//Ä«µå¹øÈ£ ³×¸ğ °¡ÀÌµå
                 creditCard.add(numguide);//Ä«µå¹øÈ£ °¡ÀÌµå
                 pgui.setVisible(false);//°áÁ¦ »¡°£ ³×¸ğ »èÁ¦
                 payguide.setVisible(false);//°áÁ¦ ¹æ¹ı »èÁ¦ 
                 cardgui.setVisible(true);//Ä«µå »¡°£ ³×¸ğ º¸ÀÌ°Ô ÇÏ±â
                 numguide.setVisible(true);//Ä«µå¹øÈ£ °¡ÀÌµå º¸ÀÌ°Ô ÇÏ±â
                 ccomgui.setVisible(false);//Ä«µå ÄŞº¸ »¡°£ ³×¸ğ »èÁ¦
                 comguide.setVisible(false);//Ä«µå ÄŞº¸ °¡ÀÌµå º¸ÀÌ°Ô ÇÏ±â
              }
           });
         
         CreditCB.addFocusListener(new FocusAdapter() {//Ä«µå»ç ÄŞº¸ ¹Ú½º °¡ÀÌµå
              @Override
              public void focusGained(FocusEvent e) {
                 ccomgui.setVisible(false);//Ä«µå ÄŞº¸ »¡°£ ³×¸ğ »èÁ¦
                 comguide.setVisible(false);//Ä«µå ÄŞº¸ °¡ÀÌµå º¸ÀÌ°Ô ÇÏ±â
                 ccomgui.setBounds(205, 0, 110, 100);
                 creditCard.add(ccomgui);
                 comguide.setBounds(200,100,500,30);
                 creditCard.add(comguide);
                 pgui.setVisible(false);//°áÁ¦ »¡°£ ³×¸ğ »èÁ¦
                 payguide.setVisible(false);//°áÁ¦ ¹æ¹ı »èÁ¦ 
                 cardgui.setVisible(false);//Ä«µå »¡°£ ³×¸ğ »èÁ¦
                 numguide.setVisible(false);//Ä«µå¹øÈ£ °¡ÀÌµå »èÁ¦
                 comguide.setVisible(true);//Ä«µå ÄŞº¸ °¡ÀÌµå º¸ÀÌ°Ô ÇÏ±â
                 ccomgui.setVisible(true);//Ä«µå ÄŞº¸ »¡°£ ³×¸ğ º¸ÀÌ°Ô ÇÏ±â
              }
           });
       }
      creditCard.setVisible(false);
      
      //¹«ÅëÀå ÀÔ±İ
      bankbook.setLayout(null);
      JLabel nobank = new JLabel("¹«ÅëÀå");
      nobank.setBounds(10, 0, 50, 30);
      bankbook.add(nobank);
      BankbookCB.setBounds(70, 5, 50, 30);
      bankbook.add(BankbookCB);
      if (GrapeThread.level == 1000) { //ÀºÇà»ç °¡ÀÌµå
         BankbookCB.addFocusListener(new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
             pgui.setVisible(false);//°áÁ¦ »¡°£ ³×¸ğ »èÁ¦
             payguide.setVisible(false);//°áÁ¦ ¹æ¹ı »èÁ¦ 
             bgui.setVisible(false);
             bguide.setVisible(false);
             bgui.setBounds(65, 0, 60, 100);
             bankbook.add(bgui);
             bguide.setBounds(50, 70, 500, 90);
             bankbook.add(bguide);
             bgui.setVisible(true);
             bguide.setVisible(true);
          }
        });
      }
      bankbook.setVisible(false);
      
    //½Å¿ëÄ«µå, ¹«ÅëÀåÀÔ±İ ³Ö´Â ÆÒ³Ú
      Group.setLayout(null);
      creditCard.setBounds(100, 10, 400,200);
      Group.add(creditCard);
      bankbook.setBounds(150, 10, 500-150,200);
      Group.add(bankbook);
      
      PayPanel.setLayout(null);
     
      payment.setBounds(0, 0, 100, 50);//°áÁ¦ ÇÏ±â ¶óº§
      radioPanel.setBounds(50, 60, 340, 30);//·¹µğ¿À ¹öÆ° ºÙÀÌ±â
      Group.setBounds(0, 110, 500, 200);//°áÁ¦ Á¤º¸
      button.setBounds(690, 700, 300 ,70 );//´ÙÀ½´Ü°è ¹öÆ°
      PayPanel.setBounds(100,150,500,650);//Å«ÆĞ³ÎÀÇ À§Ä¡
      
      //°áÁ¦ Á¤º¸ Å«ÆĞ³Î¿¡ ÆĞ³Î ¹× µîµî ³Ö±â
      PayPanel.add(payment);
      PayPanel.add(warningL);
      PayPanel.add(radioPanel);
      if (GrapeThread.level == 1000) {
         payguide.setBounds(130,25,360,40);
         PayPanel.add(payguide);//°áÁ¦¹æ¹ı ¼±ÅÃ °¡ÀÌµå ¶óº§
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//°áÁ¦¹æ¹ı °¡ÀÌµå ±×¸² ±×¸®±â
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//°áÁ¦¹æ¹ı °¡ÀÌµå ±×¸² ±×¸®±â
          bottom.setBounds(280,530,1100,100);
         PayPanel.add(bottom);
       }
      PayPanel.add(Group);
   }
   
   class MyItemListener implements ItemListener {
         public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED)
               return;
            if(radio[0].isSelected()) {
               if(bankbook.isVisible() == true) {
                      bankbook.setVisible(false);
                      creditCard.setVisible(true);
               }
               creditCard.setVisible(true);
               Payop = "½Å¿ëÄ«µå";//½Å¿ëÄ«µå ³»¿ë ÀúÀå   
            }
            else if(radio[1].isSelected()) {
               if(creditCard.isVisible()== true) {
                  creditCard.setVisible(false);
                  bankbook.setVisible(true);
               }
               bankbook.setVisible(true);
               Payop = "¹«ÅëÀåÀÔ±İ";//¹«ÅëÀå ³»¿ë ÀúÀå
            }   
         }
      }
   
   class PGuide extends JPanel {//°áÁ¦ ¹æ¹ı  °¡ÀÌµå ±×·¡ÇÈ
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 350, 40);
         g.clearRect(5,5, 340, 30);
      }
    }
   
   class CardGuide extends JPanel {//Ä«µå¹øÈ£ °¡ÀÌµå ±×·¡ÇÈ
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 40);
         g.clearRect(5,5, 50, 30);
      }
    }
   
   class CComGuide extends JPanel {//Ä«µå Äßº¸ ¹Ú½º °¡ÀÌµå ±×·¡ÇÈ
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 110, 100);
         g.clearRect(5,5, 100, 90);
      }
    }
   
   class BComGuide extends JPanel {//ÀºÇà»ç ÄŞº¸¹Ú½º °¡ÀÌµå ±×·¡ÇÈ
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 100);
         g.clearRect(5,5, 50, 90);
      }
    }
   
   
   public void OnlyNumber(KeyEvent kevt, JTextField txtF) {//ÅØ½ºÆ® ÇÊµå ¼ıÀÚ¸¸ ÀÔ·ÂÇÏ´Â ¸Ş¼Òµå
      char c = kevt.getKeyChar();
      
      if(!Character.isDigit(c)) {
         JOptionPane.showMessageDialog(null,"¼ıÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä!!!");
         kevt.consume();
         return;
      }
   }
   
   public class JTextFieldLimit extends PlainDocument{//ÅØ½ºÆ® ÇÊµå ÀÔ·Â Å©±â Á¦ÇÑ ¸Ş¼Òµå
      public int limit;
      
      public JTextFieldLimit(int limit) {
         super();
         this.limit = limit;
      }
      public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
         if(str == null) return;
         if(getLength()+str.length() <= limit) super.insertString(offset, str, attr);
      }
   }
}