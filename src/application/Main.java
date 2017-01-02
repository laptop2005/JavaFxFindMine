package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private GridPane gridPane_label = null;
	private GridPane gridPane_button = null;
	private char[][] arrMine = null;
	private Label[][] arrLabel_mine = null;
	private Button[][] arrButton_mine = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//컴포넌트들을 초기 설정
			this.initComponent();
			//지뢰를 배포
			this.setMine();
			//컴포넌트 이벤트 설정
			this.setEvent();
			
			Pane root = new Pane();
			root.getChildren().add(gridPane_label);
			root.getChildren().add(gridPane_button);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setMine(){
		//지뢰가 어디있는지 표시하기 위한 2차원 배열
		arrMine = new char[10][10];
		
		//지뢰갯수
		int mineCount = 0;
		
		//지뢰갯수가 10개가 될때까지 랜덤하게 지뢰를 놓음
		while(mineCount<10){
			//지뢰의 가로위치
			int row = (int)(Math.random()*10);
			//지뢰의 세로위치
			int col = (int)(Math.random()*10);
			
			//이미 지뢰가 있던 자리인지 검사 한 후 지뢰를 설정
			if(arrMine[row][col]!='M'){
				arrMine[row][col] = 'M';
				arrLabel_mine[row][col].setText("*");
				mineCount++;
				this.setMineCount(row, col);
			}
		}
	}
	
	private void setMineCount(int row, int col){
		/*
		 *  1 1 1
		 *  1 * 1
		 *  1 1 1
		 *  지뢰 주변의 숫자를 검사하여 값을 증가시켜줘야 함
		 */
		int[] around = null;
		
		around = AroundMine.getAroundMine1(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine2(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine3(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine4(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine5(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine6(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine7(row, col);
		this.increseAroundMineCount(around);
		
		around = AroundMine.getAroundMine8(row, col);
		this.increseAroundMineCount(around);
	}
	
	private void increseAroundMineCount(int[] around){
		//지뢰 주변의 좌표가 배열밖을 벗어나지 않는 범위 내라면 
		if(around[0]>=0&&around[0]<arrMine.length
				&&around[1]>=0&&around[1]<arrMine[0].length){
			//해당위치의 값을 가져온다.
			char c = arrMine[around[0]][around[1]];
			//다른 마인이 아닐경우에만 숫자를 넣는다.
			if(c!='M'){
				//해당위치의 char이 0이라는 것은 아직 숫자가 없는 상태라는 뜻으로
				//1을 넣어준다.
				if(c==0){
					arrMine[around[0]][around[1]] = '1';
				}else{
					//그게 아니라면 이미 다른 숫자가 있던 상태였으므로
					//그 숫자를 1 증가시켜준다.
					arrMine[around[0]][around[1]]++;
				}
				//라벨에 텍스트 값을 해당 좌표와 동일하게 한 arrMine의 값으로 넣어준다.
				arrLabel_mine[around[0]][around[1]].setText(arrMine[around[0]][around[1]]+"");
			}
		}
	}
	
	
	private void initComponent(){
		//지뢰 버튼 뒤에 숨어있는 숫자와 지뢰 라벨들을 넣을 그리드패널
		gridPane_label = new GridPane();
		gridPane_label.setId("gridPane_label");
		gridPane_label.setMinWidth(400);
		gridPane_label.setMinHeight(400);
		gridPane_label.setGridLinesVisible(true);
		
		//실제로 유저가 클릭하게 될 버튼들을 배치할 그리드패널
		gridPane_button = new GridPane();
		gridPane_button.setId("gridPane_button");
		gridPane_button.setMinWidth(400);
		gridPane_button.setMinHeight(400);
		gridPane_button.setGridLinesVisible(true);
		
		arrLabel_mine = new Label[10][10];
		arrButton_mine = new Button[10][10];
		
		//반복문으로 라벨 그리드 패널과 버튼 그리드 패널에 
		//각각의 라벨과 버튼의 객체를 생성하여 붙여 넣는다.
		for(int row=0;row<arrButton_mine.length;row++){
			for(int col=0;col<arrButton_mine[row].length;col++){
				//버튼 뒤에 표시될 라벨객체
				arrLabel_mine[row][col] = new Label("");
				arrLabel_mine[row][col].setMinWidth(40);
				arrLabel_mine[row][col].setMinHeight(40);
				arrLabel_mine[row][col].setAlignment(Pos.CENTER);
				gridPane_label.add(arrLabel_mine[row][col], col, row);
				
				//버튼객체
				arrButton_mine[row][col] = new Button("");
				arrButton_mine[row][col].setMinWidth(40);
				arrButton_mine[row][col].setMinHeight(40);
				gridPane_button.add(arrButton_mine[row][col], col, row);
			}
		}
	}
	
	private void setEvent(){
		//버튼의 갯수만큼 반복문을 돌려 각각의 버튼에 이벤트를 설정한다.
		for(int row=0;row<arrButton_mine.length;row++){
			for(int col=0;col<arrButton_mine.length;col++){
				//이벤트 객체는 EventHandler<MouseEvent> 를 구현한(implements) ButtonClickEvent
				ButtonClickEvent event = new ButtonClickEvent(row,col,arrMine,arrButton_mine);
				//마우스 버튼이 눌릴때 일어나야 할 이벤트를 설정한다.
				arrButton_mine[row][col].setOnMousePressed(event);
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
