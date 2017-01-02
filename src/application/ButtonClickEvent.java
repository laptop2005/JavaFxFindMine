package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonClickEvent implements EventHandler<MouseEvent> {

	private int row;
	private int col;
	private char[][] arrMine = null;
	private Button[][] arrButton_mine = null;
	
	public ButtonClickEvent(int row, int col, char[][] arrMine, Button[][] arrButton_mine) {
		//Ŭ���� ��ư�� ��ġ���� ���ڰ� ���� �κе��� ��� �����ֱ� ���� 
		//���� ������ �ִ� �迭�� ��ư �迭�� �޾� �����Ѵ�.
		this.row = row;
		this.col = col;
		this.arrMine = arrMine;
		this.arrButton_mine = arrButton_mine;
	}
	
	@Override
	public void handle(MouseEvent event) {
		//���࿡ �̺�Ʈ�� ���콺�� �ֹ�ư(���ʹ�ư) Ŭ���̶��
		if(event.isPrimaryButtonDown()){
			//�� ���� ����
			openEmptyBlock(row, col);
		}else if(event.isSecondaryButtonDown()){//���࿡ �̺�Ʈ�� ���콺 �ι�°��ư(�����ʹ�ư) Ŭ���̶��
			String flag = this.arrButton_mine[row][col].getText();
			//������ ��ư�� Ŭ���Ҷ����� ���� �ٲپ� �ִ´�.
			if(flag.equals("")){
				this.arrButton_mine[row][col].setText("o");
			}else if(flag.equals("o")){
				this.arrButton_mine[row][col].setText("x");
			}else if(flag.equals("x")){
				this.arrButton_mine[row][col].setText("");
			}
		}
	}

	private void openEmptyBlock(int row, int col){
		
		//��ǥ�� �ش��ϴ� ���� ����.
		arrButton_mine[row][col].setVisible(false);
		
		//���࿡ ���� ���� ���̾��ٸ�
		if(arrMine[row][col]==0){
			//Ŭ���� ��ư���κ��� �� �Ʒ� �� �� �� ���� �˻��Ͽ� ����Ǵ� �� ���� �ִٸ�
			//�ٽ� openEmptyBlock �޼��带 ���ȣ���Ͽ� ���ں��� ���������� ������ �Ѵ�.
			
			//around2[0]>=0 && around2[0]<arrMine.length --> ���� ���� ������ ��ü �迭�� ������ �ʰ����� �ʵ���
			//around2[1]>=0 && around2[1]<arrMine[0].length --> ���� ���� ������ ��ü �迭�� ������ �ʰ����� �ʵ���
			//arrButton_mine[around2[0]][around2[1]].isVisible() --> ���࿡ �̹� ���� ���̶�� �� ���ʿ䰡 �����Ƿ�
			
			int[] around2 = AroundMine.getAroundMine2(row, col);
			if( around2[0]>=0 && around2[0]<arrMine.length
					&& around2[1]>=0 && around2[1]<arrMine[0].length
					&& arrButton_mine[around2[0]][around2[1]].isVisible()){
				openEmptyBlock(around2[0],around2[1]);
			}
			int[] around4 = AroundMine.getAroundMine4(row, col);
			if( around4[0]>=0 && around4[0]<arrMine.length
					&& around4[1]>=0 && around4[1]<arrMine[0].length
					&& arrButton_mine[around4[0]][around4[1]].isVisible()){
				openEmptyBlock(around4[0],around4[1]);
			}
			int[] around5 = AroundMine.getAroundMine5(row, col);
			if( around5[0]>=0 && around5[0]<arrMine.length
					&& around5[1]>=0 && around5[1]<arrMine[0].length
					&& arrButton_mine[around5[0]][around5[1]].isVisible()){
				openEmptyBlock(around5[0],around5[1]);
			}
			int[] around7 = AroundMine.getAroundMine7(row, col);
			if( around7[0]>=0 && around7[0]<arrMine.length
					&& around7[1]>=0 && around7[1]<arrMine[0].length
					&& arrButton_mine[around7[0]][around7[1]].isVisible()){
				openEmptyBlock(around7[0],around7[1]);
			}
		}
	}
}
