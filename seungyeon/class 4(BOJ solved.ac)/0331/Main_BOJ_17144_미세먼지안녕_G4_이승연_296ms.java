import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지안녕_G4_이승연_296ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); // 세로 (6<=R<=50)
		int C = Integer.parseInt(st.nextToken()); // 가로 (6<=C<=50)
		int T = Integer.parseInt(st.nextToken()); // 시간 (1<=T<=1000)
		
		int[][] grid = new int[R][C];
		int clock_r = 0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == -1) {
					clock_r = i;
				}
			}
		}
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int[][] temp_grid = new int[R][C]; // 계속 안 만들고 재사용
		
		for(int i=0; i<T; i++) {
			spread(temp_grid, grid, dr, dc, R, C, clock_r);
			rotate(grid, clock_r, R-1, C-1);
		}
		
		int result = 0 ;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(grid[i][j] == 0 || grid[i][j] == -1) continue;
				result += grid[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	public static void rotate(int[][] grid, int clock_r, int r, int c) {
		int counter_r = clock_r-1; 
		
		// 위쪽 반시계 방향으로 회전 
		for(int i=counter_r-1; i>0; i--) {
			grid[i][0] = grid[i-1][0];
		}
		
		for(int i=0; i<c; i++) {
			grid[0][i] = grid[0][i+1];
		}
	
		for(int i=0; i<counter_r; i++) {
			grid[i][c] = grid[i+1][c];
		}
		
		for(int i=c; i>1; i--) {
			grid[counter_r][i] = grid[counter_r][i-1];
		}
		
		grid[counter_r][1] = 0; // 공기청정기로 들어간 미세먼지는 모두 정화됨. 
		
		// 아래쪽 시계 방향으로 회전 
		for(int i=clock_r+1; i<r; i++) {
			grid[i][0] = grid[i+1][0];
		}
		
		for(int i=0; i<c; i++) {
			grid[r][i] = grid[r][i+1];
		}
		
		for(int i=r; i>clock_r; i--) {
			grid[i][c] = grid[i-1][c];
		}
		
		for(int i=c; i>1; i--) {
			grid[clock_r][i] = grid[clock_r][i-1];
		}
		
		grid[clock_r][1] = 0; // 공기청정기로 들어간 미세먼지는 모두 정화됨. 
	}
	
	public static void spread(int[][] temp_grid, int[][] grid, int[] dr, int[] dc, int r, int c, int clock_r) {		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				temp_grid[i][j] = grid[i][j];
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				int n = grid[i][j]/5;
				int cnt = 0;
				
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(nr<0 || nr>=r || nc<0 || nc>=c || ((nr==clock_r-1 || nr==clock_r) && nc==0)) continue; 
					
					cnt++; 
					temp_grid[nr][nc] += n;
				}
				
				temp_grid[i][j] -= n*cnt;
			}
		}

		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				grid[i][j] = temp_grid[i][j];
			}
		}
	}
}	
