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
            
      // ������ ���� �ܰ��� �г� ����
      informPanel = new JPanel(); // ������ ��� �г�
      informPanel.setBounds(100,150,500,650); // �г��� ������ ��ġ
      informPanel.setLayout(null);
      
      JLabel lab = new JLabel("������ ����"); //������ ���� �۾� ���
      lab.setSize(200,50);
      lab.setLocation(0,0);
      lab.setFont(new Font(Font.DIALOG,Font.BOLD, 20));      
      
      JLabel nameL = new JLabel("�̸�");
      nameL.setSize(50,50);
      nameL.setLocation(0,30);
      nameF = new JTextField(20);
      nameF.setBounds(50,40,150,30);
      
      JLabel numL = new JLabel("����ó");
      numL.setSize(50,50);
      numL.setLocation(0,60+10);
      numF = new JTextField(20);
      numF.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = numF.getText();
            if(korea.matches(".*[a-zA-Z��-����-�Ӱ�-�R]+.*")) {
               JOptionPane.showMessageDialog(null,"�����̽� �� ����\n���ڸ� �Է��ϼ���!");
               numF.setText("");
               onlynum = true;
             } else {
            	 onlynum = false;
             }
         }
      });
      numF.setBounds(50,40+30+10,150,30);
      
      JLabel emailL = new JLabel("�̸���");
      emailL.setSize(50,50);
      emailL.setLocation(0,90+20);
      emailF = new JTextField(20);
      emailF.setBounds(50,40+60+20,150,30);
      
      JLabel noticeL = new JLabel("<html>���ǻ��� * ����Ȯ�� ���� �Է����� ���� ���� �߻� �� å���� ���ο��� �ֽ��ϴ�.<br/>"
            + "Ƽ���� ������������ ���ο��� ���� �����ؾ��ϸ�, �нǵ� Ƽ���� ��߱� ���� �ʽ��ϴ�.<br/>"
            + "����ó�� ���� ��ҿ� ���� ���� �� �ȳ� ������ ����ó�̹Ƿ� <br/>��Ȯ�� �Է����ֽñ� �ٶ��ϴ�.</html>");
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
      // �������� �Է¹޴� ��, �ؽ�Ʈ�ʵ带 �гο� ����
      
      if (GrapeThread.level == 1000) {  
          IGuide igui = new IGuide();
          igui.setBounds(45, 35, 160, 40);
          informPanel.add(igui);
          JLabel namegui = new JLabel("<html>�̸��� ��������.<br/>�׸��� ����ó â�� �����մϴ�.</html>");
          namegui.setBounds(210, 30, 200, 40);
          namegui.setForeground(Purchaser.praccolor);
          informPanel.add(namegui);
           
          JLabel numbergui = new JLabel("<html>����ó�� ��������.<br/>�׸��� �̸��� â�� �����մϴ�.</html>");
          numbergui.setBounds(210, 65+10, 200, 40);
          numbergui.setForeground(Purchaser.praccolor);
          informPanel.add(numbergui);
          numbergui.setVisible(false);
          
          JLabel emailgui = new JLabel("<html>�̸����� ��������.<br/>�׸��� ���� ��ư�� �����մϴ�.</html>");
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