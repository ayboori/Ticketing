package ticketing;

import javax.swing.*;

//�����ϱ� ��ư ���� ���� ������
public class BtnThread extends Thread {
	int timer;
	JButton timerButton;
	JRadioButton[] levelR;
	boolean practicemode = false;
	Seat seat;
	
   public BtnThread(JButton timerButton, JRadioButton[] levelR) {
	   this.timerButton = timerButton;
	   this.levelR = levelR;
   }
   
   public Seat returnSeat() {
       return seat;
   }
   
   public void SetLevel(int level) {
	   if (level ==1000) {
		   practicemode = true;
	   }
   }
   
   @Override
   public void run() {
      timer = 10;
      while(true) {
         if(timer >0) { //���Žð� �Ǳ� �� �۾� ���
            timerButton.setText("���� �ð� " + Integer.toString(timer) + "�� ��");
            timer--;    
         }
         else { // �����ϱ� �ð��� �Ǿ��� ���
            timerButton.setText("�����ϱ�");
            timerButton.setEnabled(true); // ���Žð� �Ǿ��� �� ��ư Ȱ��ȭ         
            for(int i=0; i<3 ;i++) {
            	levelR[i].setEnabled(false); 
            }// �����ϱ� Ȱ��ȭ�Ǹ� ���̵� ���� �Ұ�
            
            if(practicemode) {
            	seat = new Seat(); // �¼� ��ü ���⼭ ���� : �����ϱ� ��ư �������� �� ������ �¼��� ����� �ֵ��� �ϱ�
               	PracGrapeThread pgth = new PracGrapeThread(seat.grape);
            	pgth.start();
            }else {
            	seat = new Seat(); // �¼� ��ü ���⼭ ���� : �����ϱ� ��ư �������� �� ������ �¼��� ����� �ֵ��� �ϱ�
             	GrapeThread gth = new GrapeThread(seat.grape);
       	 		gth.start();
            }
       	 	return;
         }

         try {
             Thread.sleep(1000);
         }
         catch(InterruptedException e) {
            return;
         }        
     }
  }
}