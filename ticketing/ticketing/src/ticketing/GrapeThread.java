package ticketing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GrapeThread extends Thread {
   JButton[][] grape;
     int[][] temp;
     int[][] tgrape;
     int k;
     static int level; //�¼� ������� �ӵ� �����ϴ� ���̵� ����
     static boolean pick = false; // �̼��� ����
     boolean loop = true; // ���� �� ���� �˻�
     boolean gameover = false;
     static int [] alreadyx;
     static int [] alreadyy;
     int deleteindex =-1;
     String tempgrape;
     static int twosun; // �̼��� �˻� ���� ���õ� �¼� or ����� �¼� ���� ���� ����
     
   public GrapeThread(JButton[][] grape) {
      this.grape = grape;
        k = 0;
        temp = new int[20][12];
        tgrape = new int[20][12];
        alreadyx = new int[4];
        alreadyy = new int[4];
        
        for(int i = 0 ; i<4 ; i++) {
           alreadyx[i] = -1;
           alreadyy[i] = -1;        
        } // �̼��� ���� ���� �⺻���� -1
        
        for(int i = 0; i<20;i++) {
           for(int j = 0; j<12 ; j++) {
              temp[i][j]=500;
              tgrape[i][j]= k++;
           }
        }
   }
   
   public void alreadySelected(int a, int b) {
        for (int i = 0 ; i< Purchaser.seatName.size() ; i++) {
           if (!Purchaser.seatName.isEmpty() && a == Purchaser.seatX.get(i) && b == Purchaser.seatY.get(i)) { //�̹� ������ �¼��� ���� ���� ���
               pick = true;// �̼��� �޼��� ���� ���� �÷��� ���� - �� ���̶� �־����� �޼��� ����� �ϴϱ�
               loop = true; // �̼��� ������ ���� �÷��� ����
                alreadyx[i] = a; //������� �¼��� X���� alreadyx�� ����
                alreadyy[i] = b; //������� �¼��� Y���� alreadyy�� ����
           }
        } // �̼��� �˻�        
   } // �̼��� �˻� �Լ�
   
   @Override
      public void run() {
        int x=0;
        int y=0;       
         while(true) {
        	 while (loop || temp[x][y] == tgrape[x][y]) { //�̼����� �� or �̹� ���� �¼��� ��
                   // �ٸ� �¼��� ��������� ���� �ٽ� �̱�
                    x = (int)(Math.random()*20);
                    y = (int)(Math.random()*12);
                    loop = false; // �˻� ������ �̼��� ���� false�� �����صα�
                    alreadySelected(x,y); // �̰� �ٽ� �̼��� �˻�
                } 
                twosun =0;
                for(int i = 0; i<20;i++) { // ���� ������ or ������̸� twosun +1
                    for(int j = 0; j<12 ; j++) {
                       if(grape[i][j].getBackground().equals(Color.GRAY) || grape[i][j].getBackground().equals(new Color(253,204,102))) {
                          twosun++;
                       }
                    }
                 }
                if(twosun >= 20*12-1 && Seat.selectedseat != Seat.enableseat) { //��� �¼��� ���õǰų� ����� �����̰�, ���� �����ؾ� �ϴ� �ڸ���ŭ �������� ���ϸ�
                   gameover = true;
                }
                
                temp[x][y] = tgrape[x][y]; // ���� ���ڵ��� �ӽ� �迭 ���� set, �ߺ� ���� ����
                 
                 grape[x][y].setEnabled(false); 
                 grape[x][y].setBackground(Color.GRAY);
                 
                 //////////////////////////////���ӿ���
                 int result = 100;
                 if(gameover) { // �¼� ����� �ڿ� �޼��� �����ϱ� ������ 
                    result = JOptionPane.showConfirmDialog(null, "���� ���� \n ó������ �ٽ� �Ͻðڽ��ϱ�?", "�ϼ����� Ƽ�Ͽ���", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) { // ��õ� ��� ���� �� ����
                       Ticketing.page = 0; Seat.selectedseat = 0; Seat.enableseat = 0;
                       Purchaser.seatName.clear(); Purchaser.seatX.clear(); Purchaser.seatY.clear();
                       gameover = false; pick = false; 
                       for(int i = 0; i<20;i++) {
                           for(int j = 0; j<12 ; j++) {
                              temp[i][j] = 500;
                              tgrape[i][j]= k++;
                           }
                        }
                         for(int i = 0 ; i<4 ; i++) {
                            alreadyx[i] = -1;
                            alreadyy[i] = -1;        
                         } // �̼��� ���� ���� �⺻���� -1
                       new SelectCon1();
                       return;
                    }else  {
                       System.exit(0); //���α׷� ����
                    }
                 }         
                 
               try {
                   Thread.sleep(level);
               }
               catch(InterruptedException e) {
                  return;
               }
         }
     }
 }
   