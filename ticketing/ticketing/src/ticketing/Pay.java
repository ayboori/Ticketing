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
   private String [] text = {"�ſ�ī��","������ �Ա�"};
   private String [] CreditCom = {"����ī��","����ī��","�ϳ�ī��"};//ī�� ȸ��
   private String [] BankbookCom = {"����","����","�ϳ�"};//�����̸�
   JPanel PayPanel = new JPanel();//���� �г�
   private JPanel creditCard = new JPanel();//�ſ�ī��
   private JPanel bankbook = new JPanel();//������
   private JPanel Group = new JPanel();//ī�弱��
   private JPanel button = new JPanel();//��ư
   private JPanel payment = new JPanel();//���� �ϱ�
   JTextField number = new JTextField(3);
   private JButton next = new JButton("���� �ܰ�");
   private JLabel paymentL = new JLabel("�����ϱ�");
   private JLabel warningL = new JLabel("<html>������ 9���� ~ 7�� ������    Ƽ�ϱݾ��� 10%<br/> ������ 6���� ~3����    "
         + "Ƽ�ϱݾ��� 20%<br/> ������ 2����~1��������    Ƽ�ϱݾ���30%<br/> ���ż������ ���������� ��ҿ��� ȯ�ҵ��� �ʽ��ϴ�.<br/>��.���� ����  ��12�� ���� ��ҽÿ��� ��� ������ ����</html>");
   JComboBox<String> CreditCB = new JComboBox<String>(CreditCom);//ī�� �޺�
   JComboBox<String> BankbookCB = new JComboBox<String>(BankbookCom);//���� �޺�
   String CrditCBN;//ī���
   String Payop;//���� ���
   
   boolean onlynum = false;
   
   public Pay() {
      JPanel radioPanel = new JPanel();
      ButtonGroup g = new ButtonGroup(); //2���� ���� ��ư�� ���� ��ư �׷�
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
     
     JLabel payguide = new JLabel("������ ����� �����ϼ���.");
     payguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//������� ���̺� ��Ʈ
     payguide.setForeground(Purchaser.praccolor);
     
     JLabel numguide = new JLabel("ī�� ��ȣ ���ڸ� 4�ڸ��� �Է� �ϼ���!");
     numguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//ī���ȣ ���̺� ��Ʈ
     numguide.setForeground(Purchaser.praccolor);
     
     JLabel comguide = new JLabel("ī��縦 �����ϼ���!");
     comguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//ī���޺� ���̺� ��Ʈ
     comguide.setForeground(Purchaser.praccolor);
     
     JLabel botton = new JLabel("���� ��ư�� ��������!");
      botton.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//ī���޺� ���̺� ��Ʈ
      botton.setForeground(Purchaser.praccolor);
      
      JLabel bguide = new JLabel("����縦 �����ϼ���!");
      bguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//ī���޺� ���̺� ��Ʈ
      bguide.setForeground(Purchaser.praccolor);
      
      JLabel bottom = new JLabel("<html>ī���ȣ,ī���,����� <br/>������ ��  ���� ��ư ���������</html>");
      bottom.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//ī���޺� ���̺� ��Ʈ
      bottom.setForeground(Purchaser.praccolor);
             
      //���� �ϱ� ���� Ƽ��
      paymentL.setFont(new Font(Font.DIALOG,Font.BOLD, 20));//���̺� ��Ʈ
      payment.add(paymentL);//����Ȯ�� ���̺� �߰�
      
      //���� ����
      warningL.setSize(500,300);
      warningL.setLocation(0,400-150);
      
      //�ſ�ī��
      creditCard.setLayout(null);
      JLabel cardnum = new JLabel("ī���ȣ");
      cardnum.setBounds(10, 0, 60, 30);
      creditCard.add(cardnum);
      number.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = number.getText();
            if(korea.matches(".*[a-zA-Z��-����-�Ӱ�-�R]+.*")) {
               onlynum = true;
               JOptionPane.showMessageDialog(null,"���ڸ� �Է��ϼ���!!!");
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
         number.addFocusListener(new FocusAdapter() {//ī���ȣ ���̵�
              @Override
              public void focusGained(FocusEvent e) {
                 cardgui.setVisible(false);
                 numguide.setVisible(false);
                 cardgui.setBounds(70, 0, 60, 40);
                 numguide.setBounds(5,40,500,30);
                 creditCard.add(cardgui);//ī���ȣ �׸� ���̵�
                 creditCard.add(numguide);//ī���ȣ ���̵�
                 pgui.setVisible(false);//���� ���� �׸� ����
                 payguide.setVisible(false);//���� ��� ���� 
                 cardgui.setVisible(true);//ī�� ���� �׸� ���̰� �ϱ�
                 numguide.setVisible(true);//ī���ȣ ���̵� ���̰� �ϱ�
                 ccomgui.setVisible(false);//ī�� �޺� ���� �׸� ����
                 comguide.setVisible(false);//ī�� �޺� ���̵� ���̰� �ϱ�
              }
           });
         
         CreditCB.addFocusListener(new FocusAdapter() {//ī��� �޺� �ڽ� ���̵�
              @Override
              public void focusGained(FocusEvent e) {
                 ccomgui.setVisible(false);//ī�� �޺� ���� �׸� ����
                 comguide.setVisible(false);//ī�� �޺� ���̵� ���̰� �ϱ�
                 ccomgui.setBounds(205, 0, 110, 100);
                 creditCard.add(ccomgui);
                 comguide.setBounds(200,100,500,30);
                 creditCard.add(comguide);
                 pgui.setVisible(false);//���� ���� �׸� ����
                 payguide.setVisible(false);//���� ��� ���� 
                 cardgui.setVisible(false);//ī�� ���� �׸� ����
                 numguide.setVisible(false);//ī���ȣ ���̵� ����
                 comguide.setVisible(true);//ī�� �޺� ���̵� ���̰� �ϱ�
                 ccomgui.setVisible(true);//ī�� �޺� ���� �׸� ���̰� �ϱ�
              }
           });
       }
      creditCard.setVisible(false);
      
      //������ �Ա�
      bankbook.setLayout(null);
      JLabel nobank = new JLabel("������");
      nobank.setBounds(10, 0, 50, 30);
      bankbook.add(nobank);
      BankbookCB.setBounds(70, 5, 50, 30);
      bankbook.add(BankbookCB);
      if (GrapeThread.level == 1000) { //����� ���̵�
         BankbookCB.addFocusListener(new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
             pgui.setVisible(false);//���� ���� �׸� ����
             payguide.setVisible(false);//���� ��� ���� 
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
      
    //�ſ�ī��, �������Ա� �ִ� �ҳ�
      Group.setLayout(null);
      creditCard.setBounds(100, 10, 400,200);
      Group.add(creditCard);
      bankbook.setBounds(150, 10, 500-150,200);
      Group.add(bankbook);
      
      PayPanel.setLayout(null);
     
      payment.setBounds(0, 0, 100, 50);//���� �ϱ� ��
      radioPanel.setBounds(50, 60, 340, 30);//����� ��ư ���̱�
      Group.setBounds(0, 110, 500, 200);//���� ����
      button.setBounds(690, 700, 300 ,70 );//�����ܰ� ��ư
      PayPanel.setBounds(100,150,500,650);//ū�г��� ��ġ
      
      //���� ���� ū�гο� �г� �� ��� �ֱ�
      PayPanel.add(payment);
      PayPanel.add(warningL);
      PayPanel.add(radioPanel);
      if (GrapeThread.level == 1000) {
         payguide.setBounds(130,25,360,40);
         PayPanel.add(payguide);//������� ���� ���̵� ��
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//������� ���̵� �׸� �׸���
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//������� ���̵� �׸� �׸���
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
               Payop = "�ſ�ī��";//�ſ�ī�� ���� ����   
            }
            else if(radio[1].isSelected()) {
               if(creditCard.isVisible()== true) {
                  creditCard.setVisible(false);
                  bankbook.setVisible(true);
               }
               bankbook.setVisible(true);
               Payop = "�������Ա�";//������ ���� ����
            }   
         }
      }
   
   class PGuide extends JPanel {//���� ���  ���̵� �׷���
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 350, 40);
         g.clearRect(5,5, 340, 30);
      }
    }
   
   class CardGuide extends JPanel {//ī���ȣ ���̵� �׷���
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 40);
         g.clearRect(5,5, 50, 30);
      }
    }
   
   class CComGuide extends JPanel {//ī�� �ߺ� �ڽ� ���̵� �׷���
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 110, 100);
         g.clearRect(5,5, 100, 90);
      }
    }
   
   class BComGuide extends JPanel {//����� �޺��ڽ� ���̵� �׷���
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 100);
         g.clearRect(5,5, 50, 90);
      }
    }
   
   
   public void OnlyNumber(KeyEvent kevt, JTextField txtF) {//�ؽ�Ʈ �ʵ� ���ڸ� �Է��ϴ� �޼ҵ�
      char c = kevt.getKeyChar();
      
      if(!Character.isDigit(c)) {
         JOptionPane.showMessageDialog(null,"���ڸ� �Է��ϼ���!!!");
         kevt.consume();
         return;
      }
   }
   
   public class JTextFieldLimit extends PlainDocument{//�ؽ�Ʈ �ʵ� �Է� ũ�� ���� �޼ҵ�
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