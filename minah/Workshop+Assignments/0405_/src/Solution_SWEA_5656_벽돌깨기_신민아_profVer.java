import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_5656_벽돌깨기_신민아_profVer {
	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}		
		
	}
	
	static int N, W, H, min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine()) + 1;
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t = 1;t<TC;t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			go(0, map);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static boolean go(int count, int[][] map) { // 중복 순열을 이용하여 구슬을 던지기(벽돌이 다 부서졌다면 true, 아닐 시 false)
		int result = getRemain(map); 
		
		if(result == 0) {
			min  = 0;
			return true;
		}
		
		// 기저조건 : 모든 구슬을 다 던졌다면
		if(count == N) {
			min = Math.min(min, result);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		
		// 0열부터 W-1열까지 구슬 던져보기
		for(int c=0;c<W;c++) {
			// 구슬에 맞는 벽돌 찾기
			int r = 0;
			while(r < H && map[r][c] == 0) ++r; // 빈 공간이면 계속해서 아래로
			
			// 해당 열은 벽돌이 없음 -> 다음으로 넘기기
			if(r == H) continue;
			
			copy(map, newMap); // 배열의 상태를 백업
			
			boom(newMap, r, c); // 벽돌 깨트리기
			
			down(newMap); // 깨진 벽돌 정리
			
			if(go(count+1, newMap)) return true; // 다음 구슬을 던지러 go --> 벽돌이 다 깨졌다면 재귀를 멈춤
			
		}
		
		return false;
	}
	
	private static void boom(int[][] map, int r, int c) { // r, c 위치에서 주변의 가능한 모든 벽돌도 함께 부수는 처리 by BFS
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		Queue<Point> queue = new ArrayDeque<Point>();
		if(map[r][c] > 1) {
			queue.offer(new Point(r, c, map[r][c]));
		}
		map[r][c] = 0; // 자신은 제거처리 : visit check
		
		while(queue.size() > 0) {
			Point cur = queue.poll();
			
			for(int d=0;d<4;d++) { // 4방 탐색
				int nr = cur.r;
				int nc = cur.c;
				
				for(int k=1;k<cur.cnt;k++) {
					nr += dr[d];
					nc += dc[d];
					
					if(nr >= 0 && nr < H && nc >= 0 && nc < W  && map[nr][nc] > 0) { // 범위를 안벗어나고 벽돌이면
						if(map[nr][nc] > 1) { // 주변 벽돌에 영향을 주는 벽돌이면
							queue.offer(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0; // 벽돌이면 제거 처리(visit 체크)
					}
				}
			}
		}
	}
	
	private static void down(int[][] map) { // 부서진 벽돌 정리, 남은 벽돌 정리(공중에 떠있는 벽돌을 아래로 내리기)
		for(int c=0;c<W;c++) {
			int r = H-1;
			while(r>0) {
				if(map[r][c] == 0) { // 빈칸이면 내릴 벽돌이 있는지 찾기
					int nr = r-1;
					while(nr > 0 && map[nr][c] == 0) nr--;
					
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				r--;
			} // 부서지지 않고 남아있는 벽돌 리스트에 다 담기, 벽돌이 있던 자리는 빈 공간으로 처리가 됨
		}
		
	}
	
	private static int getRemain(int[][] map) { // 남은 벽돌 수 리턴
		int count = 0;
		for(int r=0;r<H;r++) {
			for(int c=0;c<W;c++) {
				if(map[r][c] > 0) count++;
			}
		}
		return count;
	}
	
	static void copy(int[][] map, int[][] newMap) {
		for(int r=0;r<H;r++) {
			for(int c=0;c<W;c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}
}
