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
		//클릭한 버튼의 위치에서 지뢰가 없는 부분들을 모두 열어주기 위해 
		//지뢰 정보가 있는 배열과 버튼 배열을 받아 저장한다.
		this.row = row;
		this.col = col;
		this.arrMine = arrMine;
		this.arrButton_mine = arrButton_mine;
	}
	
	@Override
	public void handle(MouseEvent event) {
		//만약에 이벤트가 마우스의 주버튼(왼쪽버튼) 클릭이라면
		if(event.isPrimaryButtonDown()){
			//빈 블럭을 연다
			openEmptyBlock(row, col);
		}else if(event.isSecondaryButtonDown()){//만약에 이벤트가 마우스 두번째버튼(오른쪽버튼) 클릭이라면
			String flag = this.arrButton_mine[row][col].getText();
			//오른쪽 버튼을 클릭할때마다 값을 바꾸어 넣는다.
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
		
		//좌표에 해당하는 블럭을 연다.
		arrButton_mine[row][col].setVisible(false);
		
		//만약에 블럭이 공백 블럭이었다면
		if(arrMine[row][col]==0){
			//클릭한 버튼으로부터 위 아래 좌 우 의 블럭을 검사하여 연결되는 빈 블럭이 있다면
			//다시 openEmptyBlock 메서드를 재귀호출하여 숫자블럭에 막힐때까지 열도록 한다.
			
			//around2[0]>=0 && around2[0]<arrMine.length --> 블럭의 세로 범위가 전체 배열의 범위를 초과하지 않도록
			//around2[1]>=0 && around2[1]<arrMine[0].length --> 블럭의 가로 범위가 전체 배열의 범위를 초과하지 않도록
			//arrButton_mine[around2[0]][around2[1]].isVisible() --> 만약에 이미 열린 블럭이라면 또 열필요가 없으므로
			
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
