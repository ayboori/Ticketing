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
   private String [] text = {"신용카드","무통장 입금"};
   private String [] CreditCom = {"신한카드","농협카드","하나카드"};//카드 회사
   private String [] BankbookCom = {"신한","농협","하나"};//은행이름
   JPanel PayPanel = new JPanel();//전제 패널
   private JPanel creditCard = new JPanel();//신용카드
   private JPanel bankbook = new JPanel();//무통장
   private JPanel Group = new JPanel();//카드선택
   private JPanel button = new JPanel();//버튼
   private JPanel payment = new JPanel();//결제 하기
   JTextField number = new JTextField(3);
   private JButton next = new JButton("다음 단계");
   private JLabel paymentL = new JLabel("결제하기");
   private JLabel warningL = new JLabel("<html>관람일 9일전 ~ 7일 전까지    티켓금액의 10%<br/> 관람일 6일전 ~3일전    "
         + "티켓금액의 20%<br/> 관람일 2일전~1일전까지    티켓금액의30%<br/> 예매수수료는 예매일이후 취소에는 환불되지 않습니다.<br/>단.예매 당일  밤12시 이전 취소시에는 취소 수수료 없음</html>");
   JComboBox<String> CreditCB = new JComboBox<String>(CreditCom);//카드 콤보
   JComboBox<String> BankbookCB = new JComboBox<String>(BankbookCom);//은행 콤보
   String CrditCBN;//카드사
   String Payop;//결제 방법
   
   boolean onlynum = false;
   
   public Pay() {
      JPanel radioPanel = new JPanel();
      ButtonGroup g = new ButtonGroup(); //2개의 라디오 버튼을 묶을 버튼 그룹
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
     
     JLabel payguide = new JLabel("결제할 방법을 선택하세요.");
     payguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//결제방법 레이블 폰트
     payguide.setForeground(Purchaser.praccolor);
     
     JLabel numguide = new JLabel("카드 번호 앞자리 4자리를 입력 하세요!");
     numguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//카드번호 레이블 폰트
     numguide.setForeground(Purchaser.praccolor);
     
     JLabel comguide = new JLabel("카드사를 선택하세요!");
     comguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//카드콤보 레이블 폰트
     comguide.setForeground(Purchaser.praccolor);
     
     JLabel botton = new JLabel("다음 버튼을 누르세요!");
      botton.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//카드콤보 레이블 폰트
      botton.setForeground(Purchaser.praccolor);
      
      JLabel bguide = new JLabel("은행사를 선택하세요!");
      bguide.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//카드콤보 레이블 폰트
      bguide.setForeground(Purchaser.praccolor);
      
      JLabel bottom = new JLabel("<html>카드번호,카드사,은행사 <br/>선택한 후  다음 버튼 누르세요→</html>");
      bottom.setFont(new Font(Font.DIALOG,Font.BOLD, 15));//카드콤보 레이블 폰트
      bottom.setForeground(Purchaser.praccolor);
             
      //결제 하기 예매 티켓
      paymentL.setFont(new Font(Font.DIALOG,Font.BOLD, 20));//레이블 폰트
      payment.add(paymentL);//결제확인 레이블 추가
      
      //주의 사항
      warningL.setSize(500,300);
      warningL.setLocation(0,400-150);
      
      //신용카드
      creditCard.setLayout(null);
      JLabel cardnum = new JLabel("카드번호");
      cardnum.setBounds(10, 0, 60, 30);
      creditCard.add(cardnum);
      number.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            String korea = number.getText();
            if(korea.matches(".*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
               onlynum = true;
               JOptionPane.showMessageDialog(null,"숫자만 입력하세요!!!");
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
         number.addFocusListener(new FocusAdapter() {//카드번호 가이드
              @Override
              public void focusGained(FocusEvent e) {
                 cardgui.setVisible(false);
                 numguide.setVisible(false);
                 cardgui.setBounds(70, 0, 60, 40);
                 numguide.setBounds(5,40,500,30);
                 creditCard.add(cardgui);//카드번호 네모 가이드
                 creditCard.add(numguide);//카드번호 가이드
                 pgui.setVisible(false);//결제 빨간 네모 삭제
                 payguide.setVisible(false);//결제 방법 삭제 
                 cardgui.setVisible(true);//카드 빨간 네모 보이게 하기
                 numguide.setVisible(true);//카드번호 가이드 보이게 하기
                 ccomgui.setVisible(false);//카드 콤보 빨간 네모 삭제
                 comguide.setVisible(false);//카드 콤보 가이드 보이게 하기
              }
           });
         
         CreditCB.addFocusListener(new FocusAdapter() {//카드사 콤보 박스 가이드
              @Override
              public void focusGained(FocusEvent e) {
                 ccomgui.setVisible(false);//카드 콤보 빨간 네모 삭제
                 comguide.setVisible(false);//카드 콤보 가이드 보이게 하기
                 ccomgui.setBounds(205, 0, 110, 100);
                 creditCard.add(ccomgui);
                 comguide.setBounds(200,100,500,30);
                 creditCard.add(comguide);
                 pgui.setVisible(false);//결제 빨간 네모 삭제
                 payguide.setVisible(false);//결제 방법 삭제 
                 cardgui.setVisible(false);//카드 빨간 네모 삭제
                 numguide.setVisible(false);//카드번호 가이드 삭제
                 comguide.setVisible(true);//카드 콤보 가이드 보이게 하기
                 ccomgui.setVisible(true);//카드 콤보 빨간 네모 보이게 하기
              }
           });
       }
      creditCard.setVisible(false);
      
      //무통장 입금
      bankbook.setLayout(null);
      JLabel nobank = new JLabel("무통장");
      nobank.setBounds(10, 0, 50, 30);
      bankbook.add(nobank);
      BankbookCB.setBounds(70, 5, 50, 30);
      bankbook.add(BankbookCB);
      if (GrapeThread.level == 1000) { //은행사 가이드
         BankbookCB.addFocusListener(new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
             pgui.setVisible(false);//결제 빨간 네모 삭제
             payguide.setVisible(false);//결제 방법 삭제 
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
      
    //신용카드, 무통장입금 넣는 팬넬
      Group.setLayout(null);
      creditCard.setBounds(100, 10, 400,200);
      Group.add(creditCard);
      bankbook.setBounds(150, 10, 500-150,200);
      Group.add(bankbook);
      
      PayPanel.setLayout(null);
     
      payment.setBounds(0, 0, 100, 50);//결제 하기 라벨
      radioPanel.setBounds(50, 60, 340, 30);//레디오 버튼 붙이기
      Group.setBounds(0, 110, 500, 200);//결제 정보
      button.setBounds(690, 700, 300 ,70 );//다음단계 버튼
      PayPanel.setBounds(100,150,500,650);//큰패널의 위치
      
      //결제 정보 큰패널에 패널 및 등등 넣기
      PayPanel.add(payment);
      PayPanel.add(warningL);
      PayPanel.add(radioPanel);
      if (GrapeThread.level == 1000) {
         payguide.setBounds(130,25,360,40);
         PayPanel.add(payguide);//결제방법 선택 가이드 라벨
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//결제방법 가이드 그림 그리기
          pgui.setBounds(45, 55, 360, 40);
          PayPanel.add(pgui);//결제방법 가이드 그림 그리기
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
               Payop = "신용카드";//신용카드 내용 저장   
            }
            else if(radio[1].isSelected()) {
               if(creditCard.isVisible()== true) {
                  creditCard.setVisible(false);
                  bankbook.setVisible(true);
               }
               bankbook.setVisible(true);
               Payop = "무통장입금";//무통장 내용 저장
            }   
         }
      }
   
   class PGuide extends JPanel {//결제 방법  가이드 그래픽
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 350, 40);
         g.clearRect(5,5, 340, 30);
      }
    }
   
   class CardGuide extends JPanel {//카드번호 가이드 그래픽
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 40);
         g.clearRect(5,5, 50, 30);
      }
    }
   
   class CComGuide extends JPanel {//카드 콥보 박스 가이드 그래픽
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 110, 100);
         g.clearRect(5,5, 100, 90);
      }
    }
   
   class BComGuide extends JPanel {//은행사 콤보박스 가이드 그래픽
       public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(Purchaser.praccolor);
         g.fillRect(0, 0, 60, 100);
         g.clearRect(5,5, 50, 90);
      }
    }
   
   
   public void OnlyNumber(KeyEvent kevt, JTextField txtF) {//텍스트 필드 숫자만 입력하는 메소드
      char c = kevt.getKeyChar();
      
      if(!Character.isDigit(c)) {
         JOptionPane.showMessageDialog(null,"숫자만 입력하세요!!!");
         kevt.consume();
         return;
      }
   }
   
   public class JTextFieldLimit extends PlainDocument{//텍스트 필드 입력 크기 제한 메소드
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