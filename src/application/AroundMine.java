package application;

public class AroundMine {
	public static int[] getAroundMine1(int row, int col){
		int[] result = new int[2];
		result[0] = row - 1;
		result[1] = col - 1;
		return result;
	}
	public static int[] getAroundMine2(int row, int col){
		int[] result = new int[2];
		result[0] = row - 1;
		result[1] = col;
		return result;
	}
	public static int[] getAroundMine3(int row, int col){
		int[] result = new int[2];
		result[0] = row - 1;
		result[1] = col + 1;
		return result;
	}
	public static int[] getAroundMine4(int row, int col){
		int[] result = new int[2];
		result[0] = row;
		result[1] = col - 1;
		return result;
	}
	public static int[] getAroundMine5(int row, int col){
		int[] result = new int[2];
		result[0] = row;
		result[1] = col + 1;
		return result;
	}
	public static int[] getAroundMine6(int row, int col){
		int[] result = new int[2];
		result[0] = row + 1;
		result[1] = col - 1;
		return result;
	}
	public static int[] getAroundMine7(int row, int col){
		int[] result = new int[2];
		result[0] = row + 1;
		result[1] = col;
		return result;
	}
	public static int[] getAroundMine8(int row, int col){
		int[] result = new int[2];
		result[0] = row + 1;
		result[1] = col + 1;
		return result;
	}
}
