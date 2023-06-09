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
            
      // 예매자 정보 단계의 패널 구현
      informPanel = new JPanel(); // 정보가 담긴 패널
      informPanel.setBounds(100,150,500,650); // 패널이 부착될 위치
      informPanel.setLayout(null);
      
      JLabel lab = new JLabel("예매자 정보"); //예매자 정보 글씨 출력
      lab.setSize(200,50);
      lab.setLocation(0,0);
      lab.setFont(new Font(Font.DIALOG,Font.BOLD, 20));      
      
      JLabel nameL = new JLabel("이름");
      nameL.setSize(50,50);
      nameL.setLocation(0,30);
      nameF = new JTextField(20);
      nameF.setBounds(50,40,150,30);
      
      JLabel numL = new JLabel("연락처");
      numL.setSize(50,50);
      numL.setLocation(0,60+10);
      numF = new JTextField(20);
      numF.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = numF.getText();
            if(korea.matches(".*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
               JOptionPane.showMessageDialog(null,"스페이스 바 없이\n숫자만 입력하세요!");
               numF.setText("");
               onlynum = true;
             } else {
            	 onlynum = false;
             }
         }
      });
      numF.setBounds(50,40+30+10,150,30);
      
      JLabel emailL = new JLabel("이메일");
      emailL.setSize(50,50);
      emailL.setLocation(0,90+20);
      emailF = new JTextField(20);
      emailF.setBounds(50,40+60+20,150,30);
      
      JLabel noticeL = new JLabel("<html>주의사항 * 부정확한 정보 입력으로 인한 문제 발생 시 책임은 본인에게 있습니다.<br/>"
            + "티켓은 유가증권으로 본인에게 직접 전달해야하며, 분실된 티켓은 재발권 되지 않습니다.<br/>"
            + "연락처는 공연 취소와 같은 유사 시 안내 받으실 연락처이므로 <br/>정확히 입력해주시기 바랍니다.</html>");
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
      // 정보들을 입력받는 라벨, 텍스트필드를 패널에 붙임
      
      if (GrapeThread.level == 1000) {  
          IGuide igui = new IGuide();
          igui.setBounds(45, 35, 160, 40);
          informPanel.add(igui);
          JLabel namegui = new JLabel("<html>이름을 적으세요.<br/>그리고 연락처 창을 선택합니다.</html>");
          namegui.setBounds(210, 30, 200, 40);
          namegui.setForeground(Purchaser.praccolor);
          informPanel.add(namegui);
           
          JLabel numbergui = new JLabel("<html>연락처를 적으세요.<br/>그리고 이메일 창을 선택합니다.</html>");
          numbergui.setBounds(210, 65+10, 200, 40);
          numbergui.setForeground(Purchaser.praccolor);
          informPanel.add(numbergui);
          numbergui.setVisible(false);
          
          JLabel emailgui = new JLabel("<html>이메일을 적으세요.<br/>그리고 다음 버튼을 선택합니다.</html>");
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