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
			//������Ʈ���� �ʱ� ����
			this.initComponent();
			//���ڸ� ����
			this.setMine();
			//������Ʈ �̺�Ʈ ����
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
		//���ڰ� ����ִ��� ǥ���ϱ� ���� 2���� �迭
		arrMine = new char[10][10];
		
		//���ڰ���
		int mineCount = 0;
		
		//���ڰ����� 10���� �ɶ����� �����ϰ� ���ڸ� ����
		while(mineCount<10){
			//������ ������ġ
			int row = (int)(Math.random()*10);
			//������ ������ġ
			int col = (int)(Math.random()*10);
			
			//�̹� ���ڰ� �ִ� �ڸ����� �˻� �� �� ���ڸ� ����
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
		 *  ���� �ֺ��� ���ڸ� �˻��Ͽ� ���� ����������� ��
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
		//���� �ֺ��� ��ǥ�� �迭���� ����� �ʴ� ���� ����� 
		if(around[0]>=0&&around[0]<arrMine.length
				&&around[1]>=0&&around[1]<arrMine[0].length){
			//�ش���ġ�� ���� �����´�.
			char c = arrMine[around[0]][around[1]];
			//�ٸ� ������ �ƴҰ�쿡�� ���ڸ� �ִ´�.
			if(c!='M'){
				//�ش���ġ�� char�� 0�̶�� ���� ���� ���ڰ� ���� ���¶�� ������
				//1�� �־��ش�.
				if(c==0){
					arrMine[around[0]][around[1]] = '1';
				}else{
					//�װ� �ƴ϶�� �̹� �ٸ� ���ڰ� �ִ� ���¿����Ƿ�
					//�� ���ڸ� 1 ���������ش�.
					arrMine[around[0]][around[1]]++;
				}
				//�󺧿� �ؽ�Ʈ ���� �ش� ��ǥ�� �����ϰ� �� arrMine�� ������ �־��ش�.
				arrLabel_mine[around[0]][around[1]].setText(arrMine[around[0]][around[1]]+"");
			}
		}
	}
	
	
	private void initComponent(){
		//���� ��ư �ڿ� �����ִ� ���ڿ� ���� �󺧵��� ���� �׸����г�
		gridPane_label = new GridPane();
		gridPane_label.setId("gridPane_label");
		gridPane_label.setMinWidth(400);
		gridPane_label.setMinHeight(400);
		gridPane_label.setGridLinesVisible(true);
		
		//������ ������ Ŭ���ϰ� �� ��ư���� ��ġ�� �׸����г�
		gridPane_button = new GridPane();
		gridPane_button.setId("gridPane_button");
		gridPane_button.setMinWidth(400);
		gridPane_button.setMinHeight(400);
		gridPane_button.setGridLinesVisible(true);
		
		arrLabel_mine = new Label[10][10];
		arrButton_mine = new Button[10][10];
		
		//�ݺ������� �� �׸��� �гΰ� ��ư �׸��� �гο� 
		//������ �󺧰� ��ư�� ��ü�� �����Ͽ� �ٿ� �ִ´�.
		for(int row=0;row<arrButton_mine.length;row++){
			for(int col=0;col<arrButton_mine[row].length;col++){
				//��ư �ڿ� ǥ�õ� �󺧰�ü
				arrLabel_mine[row][col] = new Label("");
				arrLabel_mine[row][col].setMinWidth(40);
				arrLabel_mine[row][col].setMinHeight(40);
				arrLabel_mine[row][col].setAlignment(Pos.CENTER);
				gridPane_label.add(arrLabel_mine[row][col], col, row);
				
				//��ư��ü
				arrButton_mine[row][col] = new Button("");
				arrButton_mine[row][col].setMinWidth(40);
				arrButton_mine[row][col].setMinHeight(40);
				gridPane_button.add(arrButton_mine[row][col], col, row);
			}
		}
	}
	
	private void setEvent(){
		//��ư�� ������ŭ �ݺ����� ���� ������ ��ư�� �̺�Ʈ�� �����Ѵ�.
		for(int row=0;row<arrButton_mine.length;row++){
			for(int col=0;col<arrButton_mine.length;col++){
				//�̺�Ʈ ��ü�� EventHandler<MouseEvent> �� ������(implements) ButtonClickEvent
				ButtonClickEvent event = new ButtonClickEvent(row,col,arrMine,arrButton_mine);
				//���콺 ��ư�� ������ �Ͼ�� �� �̺�Ʈ�� �����Ѵ�.
				arrButton_mine[row][col].setOnMousePressed(event);
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
