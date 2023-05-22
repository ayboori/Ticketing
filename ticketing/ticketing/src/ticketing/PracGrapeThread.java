package ticketing;

import java.awt.Color;

import javax.swing.JButton;

public class PracGrapeThread extends Thread {
	JButton[][] grape;
	 int[][] temp;
	 int[][] tgrape;
	 int k=0; // �ӽ� �迭 �� ���� ���� ����
	 boolean loop = true;
 	 int x=0;
 	 int y=0;       
   
   public PracGrapeThread(JButton[][] grape) {
      this.grape = grape;
      temp = new int[20][12];
      tgrape = new int[20][12];
      
      for(int i = 0; i<20;i++) {
          for(int j = 0; j<12 ; j++) {
             temp[i][j] = 500;
             tgrape[i][j]= k++;
          }
       }
   }
	public void alreadySelected(int a, int b) {
        for (int i = 0 ; i< Purchaser.seatName.size() ; i++) {
        	if (!Purchaser.seatName.isEmpty() && a == Purchaser.seatX.get(i) && b == Purchaser.seatY.get(i)) { //�̹� ������ �¼��� ���� ���� ���
            	loop = true; // �̼��� ������ ���� �÷��� ����
        	}
        } // �̼��� �˻�        
	} // �̼��� �˻� �Լ�
      
      @Override
      public void run() {
         while(true) {    
                while (loop || temp[x][y] == tgrape[x][y]) { //�̼����� �� or �̹� ���� �¼��� ��
                	// �ٸ� �¼��� ��������� ���� �ٽ� �̱�
                    x = (int)(Math.random()*20);
                    y = (int)(Math.random()*12);
                    loop = false; // �˻� ������ �̼��� ���� false�� �����صα�
                    alreadySelected(x,y); // �̰� �ٽ� �̼��� �˻�
                }

             temp[x][y] = tgrape[x][y]; // ���� ���ڵ��� �ӽ� �迭 ���� set, �ߺ� ���� ����
             
             grape[x][y].setEnabled(false);
             grape[x][y].setBackground(Color.GRAY);
                 
               try {
                   Thread.sleep(1000);
               }
               catch(InterruptedException e) {
                  return;
               }        
         }
      }
 }
   