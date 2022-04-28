import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1. 구슬을 떨어트리기(중복 순열)
 * 2. 구슬에 맞는 벽돌 찾기 : 벽돌크기 - 1을 4방탐색하여 벽돌을 제거(DFS, BFS)
 * 3. 깨진 벽돌 치우기(아래로 내리기)
 *
 */
public class Solution_SWEA_5656_벽돌깨기_신민아 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine()) + 1;
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[][] map;
		int[] nextR;
		
		for(int t=1;t<TC;t++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int ballCount = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			map = new int[r][c];
			
			
			int totalBricks = c * r;
			// map initialization
			for(int i=0;i<r;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<c;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 0) // 0인 킨을 찾아 블록 빼주기
						totalBricks--;
				}
			}
			
			int bricks = 0;
			bricks = Math.max(bricks, permutation(0, ballCount, c, new int[ballCount], map));
			sb.append("#").append(t).append(" ").append(totalBricks - bricks).append("\n");
			
		}
		
		System.out.print(sb.toString());
	}
	
	public static int permutation(int curCount, int ballCount, int width, int[] numbers, int[][] map) {
		// 기본 파트
		if(curCount == ballCount) {
			
		}
		
		// 입력 받은 모든 수를 현재 자리에 넣어 보기
		for(int i=0;i<width;i++) {			
			numbers[curCount] = i;
			
			// 다음 수 뽑으러 가기
			permutation(curCount+1, ballCount, width, numbers, map);
		}
		
		return 0;
	}

}
