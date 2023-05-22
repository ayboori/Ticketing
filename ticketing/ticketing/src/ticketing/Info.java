package ticketing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Info extends JPanel {
   JPanel informPanel;
   JTextField nameF;
   JTextField numF;
   JTextField emailF;
   boolean onlynum = false;
   
   public Info() {
            
      // øπ∏≈¿⁄ ¡§∫∏ ¥‹∞Ë¿« ∆–≥Œ ±∏«ˆ
      informPanel = new JPanel(); // ¡§∫∏∞° ¥„±‰ ∆–≥Œ
      informPanel.setBounds(100,150,500,650); // ∆–≥Œ¿Ã ∫Œ¬¯µ… ¿ßƒ°
      informPanel.setLayout(null);
      
      JLabel lab = new JLabel("øπ∏≈¿⁄ ¡§∫∏"); //øπ∏≈¿⁄ ¡§∫∏ ±€ææ √‚∑¬
      lab.setSize(200,50);
      lab.setLocation(0,0);
      lab.setFont(new Font(Font.DIALOG,Font.BOLD, 20));      
      
      JLabel nameL = new JLabel("¿Ã∏ß");
      nameL.setSize(50,50);
      nameL.setLocation(0,30);
      nameF = new JTextField(20);
      nameF.setBounds(50,40,150,30);
      
      JLabel numL = new JLabel("ø¨∂Ù√≥");
      numL.setSize(50,50);
      numL.setLocation(0,60+10);
      numF = new JTextField(20);
      numF.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = numF.getText();
            if(korea.matches(".*[a-zA-Z§°-§æ§ø-§”∞°-∆R]+.*")) {
               JOptionPane.showMessageDialog(null,"Ω∫∆‰¿ÃΩ∫ πŸ æ¯¿Ã\nº˝¿⁄∏∏ ¿‘∑¬«œººø‰!");
               numF.setText("");
               onlynum = true;
             } else {
            	 onlynum = false;
             }
         }
      });
      numF.setBounds(50,40+30+10,150,30);
      
      JLabel emailL = new JLabel("¿Ã∏ﬁ¿œ");
      emailL.setSize(50,50);
      emailL.setLocation(0,90+20);
      emailF = new JTextField(20);
      emailF.setBounds(50,40+60+20,150,30);
      
      JLabel noticeL = new JLabel("<html>¡÷¿«ªÁ«◊ * ∫Œ¡§»Æ«— ¡§∫∏ ¿‘∑¬¿∏∑Œ ¿Œ«— πÆ¡¶ πﬂª˝ Ω√ √•¿”¿∫ ∫ª¿Œø°∞‘ ¿÷Ω¿¥œ¥Ÿ.<br/>"
            + "∆ºƒœ¿∫ ¿Ø∞°¡ı±«¿∏∑Œ ∫ª¿Œø°∞‘ ¡˜¡¢ ¿¸¥ﬁ«ÿæﬂ«œ∏Á, ∫–Ω«µ» ∆ºƒœ¿∫ ¿Áπﬂ±« µ«¡ˆ æ Ω¿¥œ¥Ÿ.<br/>"
            + "ø¨∂Ù√≥¥¬ ∞¯ø¨ √Îº“øÕ ∞∞¿∫ ¿ØªÁ Ω√ æ»≥ª πﬁ¿∏Ω« ø¨∂Ù√≥¿Ãπ«∑Œ <br/>¡§»Æ»˜ ¿‘∑¬«ÿ¡÷Ω√±‚ πŸ∂¯¥œ¥Ÿ.</html>");
      noticeL.setSize(500,300);
      noticeL.setLocation(0,400-150);  

      informPanel.add(lab);
      informPanel.add(nameL);   
      informPanel.add(nameF);
      informPanel.add(numL);   
      informPanel.add(numF);
      informPanel.add(emailL);
      informPanel.add(emailF);
      informPanel.add(noticeL);
      // ¡§∫∏µÈ¿ª ¿‘∑¬πﬁ¥¬ ∂Û∫ß, ≈ÿΩ∫∆Æ« µÂ∏¶ ∆–≥Œø° ∫Ÿ¿”
      
      if (GrapeThread.level == 1000) {  
          IGuide igui = new IGuide();
          igui.setBounds(45, 35, 160, 40);
          informPanel.add(igui);
          JLabel namegui = new JLabel("<html>¿Ã∏ß¿ª ¿˚¿∏ººø‰.<br/>±◊∏Æ∞Ì ø¨∂Ù√≥ √¢¿ª º±≈√«’¥œ¥Ÿ.</html>");
          namegui.setBounds(210, 30, 200, 40);
          namegui.setForeground(Purchaser.praccolor);
          informPanel.add(namegui);
           
          JLabel numbergui = new JLabel("<html>ø¨∂Ù√≥∏¶ ¿˚¿∏ººø‰.<br/>±◊∏Æ∞Ì ¿Ã∏ﬁ¿œ √¢¿ª º±≈√«’¥œ¥Ÿ.</html>");
          numbergui.setBounds(210, 65+10, 200, 40);
          numbergui.setForeground(Purchaser.praccolor);
          informPanel.add(numbergui);
          numbergui.setVisible(false);
          
          JLabel emailgui = new JLabel("<html>¿Ã∏ﬁ¿œ¿ª ¿˚¿∏ººø‰.<br/>±◊∏Æ∞Ì ¥Ÿ¿Ω πˆ∆∞¿ª º±≈√«’¥œ¥Ÿ.</html>");
          emailgui.setBounds(210, 95+20, 200, 40);
          emailgui.setForeground(Purchaser.praccolor);
          informPanel.add(emailgui);
          emailgui.setVisible(false);
          
          nameF.addFocusListener(new FocusAdapter() {
             @Override
             public void focusGained(FocusEvent e) {
                igui.setBounds(45, 35, 160, 40);
                informPanel.add(igui);
                namegui.setVisible(true);
                numbergui.setVisible(false);
                emailgui.setVisible(false);
             }
          });
          
          numF.addFocusListener(new FocusAdapter() {
             @Override
             public void focusGained(FocusEvent e) {
                igui.setBounds(45, 65+10, 160, 40);
                namegui.setVisible(false);
                numbergui.setVisible(true);
                emailgui.setVisible(false);
                informPanel.add(igui);
             }
          });
          
          emailF.addFocusListener(new FocusAdapter() {
             @Override
             public void focusGained(FocusEvent e) {
                igui.setBounds(45, 95+20, 160, 40);
                namegui.setVisible(false);
                numbergui.setVisible(false);
                emailgui.setVisible(true);
                informPanel.add(igui);
             }
          });
       }
   }
    class IGuide extends JPanel {
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 160, 40);
         g.clearRect(5,5,135,15);
      }
    }
}