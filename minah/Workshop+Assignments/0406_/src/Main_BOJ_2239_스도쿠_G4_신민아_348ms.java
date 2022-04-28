import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_2239_스도쿠_G4_신민아_348ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] sudoku = new int[9][9];
		boolean[][] rowCheck = new boolean[9][10]; // 세로 체크
		boolean[][] colCheck = new boolean[9][10]; // 가로 체크
		boolean[][] squareCheck = new boolean[9][10]; // 사각형(3x3) 체크
		
		for(int i=0;i<9;i++) { // 값을 입력받으면서 가로, 세로, 사각형 체크
			String line = br.readLine();
			for(int j=0;j<9;j++) {
				sudoku[i][j] = line.charAt(j) - '0';
				setCheckArrays(i, j, sudoku[i][j], true, rowCheck, colCheck, squareCheck);
			}
		}
		
		// 백트래킹 dfs 메소드
		doSudoku(0, sudoku, rowCheck, colCheck, squareCheck);
		
	}
	
	// 사전순으로 출력하므로 최초에 완성된 경우에 출력하고 나가면 됨
	private static void doSudoku(int curCount, int[][] sudoku, boolean[][] rowCheck, boolean[][] colCheck, boolean[][] squareCheck) {
		// 81개 완성 시 바로 출력 후 빠져나가기
		if(curCount == 81) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					sb.append(sudoku[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb.toString());
			System.exit(0); // return 하면 답이 여러개인 경우 모두 출력하므로 종료해주는 것이 best
		}
		
		// r, c좌표 얻기
		int r = curCount / 9;
		int c = curCount % 9;
		
		if(sudoku[r][c] != 0) {
			doSudoku(curCount+1, sudoku, rowCheck, colCheck, squareCheck); // sudoku가 0이 아닐 때(이미 값이 들어가 있을 때) : 카운트만 증가시키고 나감
		} else { // 아닐 경우
			for(int i=1;i<10;i++) { // 스도쿠의 숫자 대입 
				if(!rowCheck[r][i] && !colCheck[c][i] && !squareCheck[((r/3)*3) + (c/3)][i]) { // 현재까지 모두 포함되지 않을 경우에
					setCheckArrays(r, c, i, true, rowCheck, colCheck, squareCheck); // 모든 체크 배열에 true해주고
					sudoku[r][c] = i; // 해당 부분에 스도쿠 값을 바꿔줌
					
					doSudoku(curCount+1, sudoku, rowCheck, colCheck, squareCheck); // 1개를 확인했으니 count값 증가
					
					sudoku[r][c] = 0; // 원상 복구
					setCheckArrays(r, c, i, false, rowCheck, colCheck, squareCheck); // 원상 복구하면서 숫자 체크 배열 false 처리
				}
			}
		}
	}
	
	private static void setCheckArrays(int r, int c, int num, boolean value, 
										boolean[][] rowCheck, boolean[][] colCheck, boolean[][] squareCheck) {
		rowCheck[r][num] = value;
		colCheck[c][num] = value;
		squareCheck[(r/3)*3 + (c/3)][num] = value;
	}

}
